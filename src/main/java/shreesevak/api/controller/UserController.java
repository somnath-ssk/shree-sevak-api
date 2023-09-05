package shreesevak.api.controller;

import java.net.http.HttpClient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shreesevak.api.ApiApplication;
import shreesevak.api.model.User;
import shreesevak.api.payloads.ApiResponse;
import shreesevak.api.payloads.RoleDto;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.services.RoleService;
import shreesevak.api.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	 //POST -create User
	
	@PostMapping("/signup") 
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto ){
		
		UserDto createUserDto=this.userService.createUser(userDto);
		
		return  new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		
	}
	
	// Assigning role to all ready present user  
	
	@PostMapping("/{userId}/assignroles")
	public ResponseEntity<User> assignUserRole(@PathVariable Integer userId,@RequestBody List<Integer> roleId){
		User createdUser=this.userService.assignUserRole(userId,roleId);
		
		return new ResponseEntity<User>(createdUser,HttpStatus.CREATED);
		
	}
	
	//PUT -update user 
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto ,@PathVariable Integer userId){
		
		UserDto updateduserDto =userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updateduserDto);
		
	}
	
	//DELETE  delete user
@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		  System.out.println("deletUser method controler");
          userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted succesfully ",true),HttpStatus.OK) ;
		
	}
	
	
	//GET -user get
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		
		UserDto respuserDto=userService.getUserById(userId);
		return ResponseEntity.ok(respuserDto);
		
		
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/active/{status}")
	public ResponseEntity<List<UserDto>> getAllActiveUser(@PathVariable String status){
		return ResponseEntity.ok(this.userService.getAllActiveUsers(status));
	}
	
//	@PutMapping("/{userId}/location/{locId}")
//	public ResponseEntity<User> assignLocationToUser(@PathVariable Integer locId,@PathVariable Integer userId){
//		
//		User userLoc=userService.assignLocationToUser(locId, userId);
//		
//		
//		return ResponseEntity.ok(userLoc);
//		
//		
//	}

	
	
}
