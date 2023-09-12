package shreesevak.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;import jakarta.persistence.criteria.CriteriaBuilder.In;
import shreesevak.api.payloads.RoleDto;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.services.RoleService;

@CrossOrigin
@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	
	@PostMapping("/")
	public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto){
		RoleDto createdUser=this.roleService.createRole(roleDto);
		
		return new ResponseEntity<RoleDto>(createdUser,HttpStatus.CREATED);
		
	}
	
	// create user along with role 
	

	
	@PutMapping("/{roleId}")
	public ResponseEntity<RoleDto>updateRole(@RequestBody RoleDto roleDto,@PathVariable Integer roleId)
	{
		RoleDto roleDto1=roleService.updateRole(roleDto, roleId);
		return ResponseEntity.ok(roleDto1);
	}
	
	@GetMapping("/{roleId}")
	public ResponseEntity<RoleDto> getSingleRole(@PathVariable Integer roleId){
		
		   RoleDto roleDto= this.roleService.getRoleById(roleId);
		   return new ResponseEntity<RoleDto>(roleDto,HttpStatus.OK );
	}
	@GetMapping("/all-roles")
	public ResponseEntity<List<RoleDto>> getAllRoles(){
		
		return ResponseEntity.ok(this.roleService.getAllRoles());
		
	}
	
	@GetMapping("/active/{status}")
	public ResponseEntity<List<RoleDto>> getAllActiveUser(@PathVariable String status){
		return ResponseEntity.ok(this.roleService.getAllActiveRole(status));
	}
	
}
