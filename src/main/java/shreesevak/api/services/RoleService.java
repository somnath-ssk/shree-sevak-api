package shreesevak.api.services;

import java.util.List;

import shreesevak.api.model.Role;
import shreesevak.api.model.User;
import shreesevak.api.payloads.RoleDto;
import shreesevak.api.payloads.UserDto;

public interface RoleService  {

	   RoleDto createRole(RoleDto roleDto);
	
	   RoleDto updateRole(RoleDto roleDto,Integer roleId);
	  RoleDto getRoleById(Integer roleId);
//	  Void deleteRole(Integer roleid);
	List<RoleDto>  getAllRoles();
	List<RoleDto> getAllActiveRole(String status);
	List<Role> searchRole(String keyword);

	  
	  
}
