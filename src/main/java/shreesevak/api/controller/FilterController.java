package shreesevak.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import shreesevak.api.model.Location;
import shreesevak.api.model.Role;
import shreesevak.api.model.User;
import shreesevak.api.payloads.LocationDto;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.repository.UserRepo;
import shreesevak.api.services.LocationService;
import shreesevak.api.services.RoleService;
import shreesevak.api.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/filter")
public class FilterController {
	
	@Autowired
	UserRepo userRepo;
    
	@Autowired
	LocationService locationService;

	@Autowired
	UserService userService;
	
//	@Autowired
//	RoleService roleService;
	// To search user using keyword from User Entity properties
//	@GetMapping("/search/user")
//	 public ResponseEntity<List<User>> searchByKeyword(@RequestParam("keyword") String keyword) {
//	List<User> foundUsers	=userService.searchUsers(keyword);
//		return ResponseEntity.ok(foundUsers);
//    }
	
	// To search user using keyword from Location Entity properties
//	@GetMapping("/search/locations")
//	public ResponseEntity<List<Location>> searchLocationByKeyword(@RequestParam("keyword") String keyword) {
//		List<Location> foundLocation	=locationService.searchLocations(keyword);
//		return ResponseEntity.ok(foundLocation);
//	}
	
	// To search role using keyword from Role Entity properties
//	@GetMapping("/search/role")
//	public ResponseEntity<List<Role>> searchRoleByKeyword(@RequestParam("keyword") String keyword) {
//		List<Role> foundLocation	=roleService.searchRole(keyword);
//		return ResponseEntity.ok(foundLocation);
//	}

}
