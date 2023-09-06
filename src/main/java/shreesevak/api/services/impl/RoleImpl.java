package shreesevak.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceAllReadyExist;
import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.Role;
import shreesevak.api.payloads.RoleDto;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.model.User;
import shreesevak.api.repository.RoleRepo;
import shreesevak.api.repository.UserRepo;
import shreesevak.api.services.RoleService;
import shreesevak.api.services.UserService;


@Service
public class RoleImpl implements RoleService {

	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;
	
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public RoleDto createRole(RoleDto roleDto) {
		Role role=this.roleDtoToRole(roleDto);
		if(roleRepo.findByRoleName(role.getRoleName()) !=null) {
			 throw new ResourceAllReadyExist(role.getRoleName());
		}
   
	  Role saveRole = this.roleRepo.save(role);
	  
		return this.roleToDto(saveRole);
	}
	
	
	

	@Override
	public RoleDto updateRole(RoleDto roleDto, Integer roleId) {
	Role role=this.roleRepo.findById(roleId).orElseThrow(()->new ResourceNotFoundException("Role","id", roleId));
	 role.setRoleCode(roleDto.getRoleCode());
	 role.setRoleName(roleDto.getRoleName());
	 role.setStatus(roleDto.getStatus());
	 Role updateRole=this.roleRepo.save(role);
	RoleDto userDto= this.roleToDto(updateRole);
		return userDto;
	}

	@Override
	public RoleDto getRoleById(Integer roleId) {
        Role role =  this.roleRepo.findById(roleId).orElseThrow(()->new ResourceNotFoundException("Role","id",roleId));
        return this.roleToDto(role);
	}

//	@Override
//	public void deleteRole(Integer roleid) {
//		
//	}

	@Override
	public List<RoleDto> getAllRoles() {
		List<Role> roles=this.roleRepo.findAll();
		List<RoleDto> roleDtos=roles.stream().map(role ->this.roleToDto(role)).collect(Collectors.toList());
		return roleDtos;
	}

	@Override
	public List<RoleDto> getAllActiveRole(String status) {
	List<Role> activeRoles  = this.roleRepo.findAllByStatus(status);
	List<RoleDto> roleDtos=activeRoles.stream().map(role ->this.roleToDto(role)).collect(Collectors.toList());
		return roleDtos ;
	}
	
	public RoleDto roleToDto(Role role) {
		
		RoleDto roleDto=this.modelMapper.map(role,RoleDto.class);
		return roleDto;
	}
	public Role roleDtoToRole(RoleDto roleDto) {
		
		Role role=this.modelMapper.map(roleDto,Role.class);
		return role;
	}
    



	@Override
	public List<Role> searchRole(String keyword) {
      List<Role>roles= roleRepo.searchRole(keyword);
		return roles;
	}




	

}
