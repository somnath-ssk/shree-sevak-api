package shreesevak.api.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.mapping.Array;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.DuplicateKeyException;
import shreesevak.api.exceptions.ResourceAllReadyExist;
import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.Area;
import shreesevak.api.model.Role;
import shreesevak.api.model.User;
import shreesevak.api.payloads.RoleDto;
import shreesevak.api.payloads.UserAreaFrontEnd;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.payloads.UserFrontEndData;
import shreesevak.api.repository.AreaRepo;
import shreesevak.api.repository.LocationRepo;
import shreesevak.api.repository.RoleRepo;
import shreesevak.api.repository.UserRepo;

import shreesevak.api.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AreaRepo areaRepo;
	@Autowired
	private LocationRepo locationRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	// sign up user
	@Override
	public UserDto createUser(UserFrontEndData frontEndData) {
		if (userRepo.findByName(frontEndData.getName()) != null) {
			throw new ResourceAllReadyExist(frontEndData.getName());

		}
		if (userRepo.findByEmailId(frontEndData.getEmailId()) != null) {
			throw new ResourceAllReadyExist(frontEndData.getEmailId());
		}

		if (userRepo.findByPhoneNumber(frontEndData.getPhoneNumber()) != null) {
			throw new ResourceAllReadyExist(frontEndData.getPhoneNumber());
		}

		User user = new User();

		user.setPassword(passwordEncoder.encode(frontEndData.getPassword()));
		user.setName(frontEndData.getName());

		user.setEmailId(frontEndData.getEmailId());
		user.setPhoneNumber(frontEndData.getPhoneNumber());

		user.setStatus(frontEndData.getStatus());

		List<Role> role = this.roleRepo.findByRoleName(frontEndData.getRole());
		user.setRoles(role);
		List<UserAreaFrontEnd> areas = frontEndData.getSelectedAreas();
		List<Optional<Area>> areaList = areas.stream().map(ar -> this.areaRepo.findById(ar.getId()))
				.collect(Collectors.toList());
		List<Area> newArea = new ArrayList<>();

		for (Optional<Area> optional : areaList) {
			newArea.add(optional.get());
		}
		user.setSelectedAreas(newArea);
		User saveUser = this.userRepo.save(user);

		System.out.println(saveUser.toString());
		return this.userToDto(saveUser);
	}

	// update user Details

	@Override
	public UserDto updateUser(UserFrontEndData userDto, Integer userId) {
		List<Area> areas = new ArrayList<>();
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		user.setSelectedAreas(areas);
		User updatedUser = this.userRepo.save(user);
//		user.setUserId(userDto.getUserId());
		updatedUser.setName(userDto.getName());
		updatedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		updatedUser.setEmailId(userDto.getEmailId());
		updatedUser.setPhoneNumber(userDto.getPhoneNumber());
		List<UserAreaFrontEnd> areas1 = userDto.getSelectedAreas();
		List<Optional<Area>> areaList = areas1.stream().map(ar -> this.areaRepo.findById(ar.getId()))
				.collect(Collectors.toList());
		List<Area> newArea = new ArrayList<>();

		for (Optional<Area> optional : areaList) {
			newArea.add(optional.get());
		}
		updatedUser.setSelectedAreas(newArea);
		List<Role> role = this.roleRepo.findByRoleName(userDto.getRole());

		updatedUser.setRoles(role);
		updatedUser.setStatus(userDto.getStatus());
  try {

		User updatedUser2 = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser2);
		return userDto1;
  }catch(DataIntegrityViolationException ex) {
	  throw new DuplicateKeyException("Duplicate area id key violation occurred.");
  }
		
	}

	// get user by Id
	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

		return this.userToDto(user);
	}

	@Override
	public UserDto getUserByUserName(String userName) {
		User user = this.userRepo.findByEmailId(userName);
		return this.userToDto(user);
	}

	// get all users
	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	// delete user
	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		System.out.println(user);
		this.userRepo.delete(user);

	}
	// get all active user by status 1 & 0

	@Override
	public List<UserDto> getAllActiveUsers(String status) {
		List<User> users = this.userRepo.findAllByStatus(status);
		List<UserDto> userDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	// converting dto to user
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);

		return user;

	}

	public UserDto userToDto(User user) {

		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setUserId(user.getUserId());
//		userDto.setName(user.getName());
//		userDto.setEmailId(user.getEmailId());
//		userDto.setPhoneNumber(user.getPhoneNumber());
//		userDto.setPhotoUrl(user.getPhotoUrl());
//		userDto.setProviderId(user.getProviderId());
//		userDto.setStatus(user.getStatus());
		return userDto;
	}

	// searching user
	@Override
	public List<User> searchUsers(String keyword) {
		List<User> users1 = userRepo.searchUser(keyword);

		return users1;

	}

	/// assigning user role base on all ready created user
	@Override
	public UserDto assignUserRole(Integer userId, List<Integer> roleId) {
//	  User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user ID","id", userId));
		User user = userRepo.findById(userId).get();
//	  Role role=roleRepo.findById(roleId).orElseThrow(()->new ResourceNotFoundException("Role","id", roleId));;
		List<Role> roles = roleRepo.findAllById(roleId);
		user.setRoles(roles);
		User updatedUser = this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

}
