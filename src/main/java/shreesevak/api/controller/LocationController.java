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
import org.springframework.web.bind.annotation.RestController;

import shreesevak.api.payloads.LocationDto;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.services.LocationService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/location")
public class LocationController {



	@Autowired
    private LocationService locationService;
	
	
	// create Location Details
	@PostMapping("/")
	public  ResponseEntity<LocationDto> createLocations(@RequestBody LocationDto locDto) {
	LocationDto locDto1= this.locationService.createLocation(locDto);
	return new ResponseEntity<>(locDto1,HttpStatus.OK);
		
	}
	
	// get Location Details by Id
	@GetMapping("/{locId}")
	public ResponseEntity<LocationDto> getLocationById(@PathVariable Integer  locId){
		LocationDto locDto= this.locationService.getLocationById(locId);
	   
		
		return ResponseEntity.ok(locDto); 
	}
	
	//get all location
	
	@GetMapping("/")
	public ResponseEntity<List<LocationDto>> getAllLocations(){
		return ResponseEntity.ok(this.locationService.getAllLocations());
	}
	 
	@PutMapping("/{locId}")
	public ResponseEntity<LocationDto> updateLocation(@RequestBody LocationDto locDto,@PathVariable  Integer locId){
		  LocationDto newLocDto= locationService.updateLocation(locDto, locId);
		return ResponseEntity.ok(newLocDto);
	}
	
	// All active locations
	@GetMapping("/status/{statustype}")
	public ResponseEntity<List<LocationDto>> getAllActiveLocations(@PathVariable String statustype){
		return ResponseEntity.ok(this.locationService. getAllActiveLocation(statustype));
	}
   
	// assigning the bithak and member to the location
	
}
