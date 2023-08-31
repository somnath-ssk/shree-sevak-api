package shreesevak.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import shreesevak.api.exceptions.ResourceAllReadyExist;
import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.Location;
import shreesevak.api.model.Role;
import shreesevak.api.model.User;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.repository.LocationRepo;
import shreesevak.api.repository.RoleRepo;
import shreesevak.api.repository.UserRepo;

import shreesevak.api.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private LocationRepo locationRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepo roleRepo;

	
	@Override
	public UserDto createUser(UserDto userDto) {
		 User user=this.dtoToUser(userDto);
		
	
		 
		  if(userRepo.findByName(user.getName()) !=null) {
			  throw  new ResourceAllReadyExist(user.getName());
	        
		  }
		  if (userRepo.findByEmailId(user.getEmailId() ) !=null) {
	            throw new ResourceAllReadyExist(user.getEmailId());
	        }

	        if (userRepo.findByPhoneNumber(user.getPhoneNumber())!= null) {
	        	 throw new ResourceAllReadyExist(user.getPhoneNumber());
	        }
//	        user.setRoles(1);
		User saveUser= this.userRepo.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
	
		user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setEmailId(userDto.getEmailId());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setPhotoUrl(userDto.getPhotoUrl());
		user.setProviderId(userDto.getProviderId());
		user.setStatus(userDto.getStatus());
		
		
		User updatedUser=this.userRepo.save(user);
		UserDto userDto1=this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
	
	
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
	List<User> users   = this.userRepo.findAll();
	List<UserDto>userDto=users.stream().map(user ->this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	// delete user 
	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
		System.out.println(user);
		this.userRepo.delete(user);
		
	}
  // get all active user by status 1 & o 
	
	@Override
	public List<UserDto> getAllActiveUsers(String status) {
	List<User> users= this.userRepo.findAllByStatus(status);
	List<UserDto>userDto=users.stream().map(user ->this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	
	public User dtoToUser(UserDto userDto) {
		User user= this.modelMapper.map(userDto,User.class);
	
	    return user;

	}

	public UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
//		userDto.setUserId(user.getUserId());
//		userDto.setName(user.getName());
//		userDto.setEmailId(user.getEmailId());
//		userDto.setPhoneNumber(user.getPhoneNumber());
//		userDto.setPhotoUrl(user.getPhotoUrl());
//		userDto.setProviderId(user.getProviderId());
//		userDto.setStatus(user.getStatus());
		return userDto;
	}


	@Override
	public List<User> searchUsers(String keyword) {
	List<User>users1= userRepo.searchUser(keyword);
	
	return  users1;
		
	}
	
	//saving the role into user
	
	
	@Override
	public User assignUserRole(Integer userId, List<Integer> roleId) {
//	  User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user ID","id", userId));
	  User user=userRepo.findById(userId).get();
//	  Role role=roleRepo.findById(roleId).orElseThrow(()->new ResourceNotFoundException("Role","id", roleId));;
	  List<Role> roles= roleRepo.findAllById(roleId);
	   user.setRoles(roles);
    User updatedUser  = this.userRepo.save(user);
          return updatedUser;
	}
	
	

	
	

	
	



}
