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
import org.springframework.web.bind.annotation.RestController;

import shreesevak.api.helperclass.LocationFrontEnd;
import shreesevak.api.payloads.LocationDto;
import shreesevak.api.payloads.PaginationResponse;
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
	public  ResponseEntity<LocationDto> createLocations(@RequestBody LocationFrontEnd locfrontDto) {
	LocationDto locDto1= this.locationService.createLocation(locfrontDto);
	return new ResponseEntity<>(locDto1,HttpStatus.OK);
		
	}
	
	// get Location Details by Id
	@GetMapping("/{locId}")
	public ResponseEntity<LocationDto> getLocationById(@PathVariable Integer  locId){
		LocationDto locDto= this.locationService.getLocationById(locId);
	   
		
		return ResponseEntity.ok(locDto); 
	}
	
	@GetMapping("/allLocations/areaId/{areaId}")
	public ResponseEntity<List<LocationDto>> getAllLocationByAreaId(@PathVariable Integer  areaId){
		List<LocationDto> locDto= this.locationService.getAllLocationByAreaId(areaId);
		
		
		return new ResponseEntity<>(locDto,HttpStatus.OK);
	}
	
	//get all location
	
	@GetMapping("/")
	public ResponseEntity<List<LocationDto>> getAllLocations(){
		return ResponseEntity.ok(this.locationService.getAllLocations());
	}
	
	
	@GetMapping("/pagination/all")
	public ResponseEntity<PaginationResponse> getAllActiveLocations(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
		return ResponseEntity.ok(this.locationService.getAllLocations(pageNumber, pageSize));
	}
	 
	@PutMapping("/{locId}")
	public ResponseEntity<LocationDto> updateLocation(@RequestBody LocationFrontEnd locDto,@PathVariable  Integer locId){
		  LocationDto newLocDto= locationService.updateLocation(locDto, locId);
		return ResponseEntity.ok(newLocDto);
	}	
	
	// All active locations
	@GetMapping("/status/{statustype}")
	public ResponseEntity<List<LocationDto>> getAllActiveLocations(@PathVariable String statustype){
		return ResponseEntity.ok(this.locationService. getAllActiveLocation(statustype));
	}
	@GetMapping("/status/pagination/{statustype}")
	public ResponseEntity<PaginationResponse> getAllActiveLocations(@PathVariable String statustype,Integer pageNumber, Integer pageSize){
		return new ResponseEntity<PaginationResponse>(this.locationService.getAllActiveLocation(pageNumber, pageSize, statustype),HttpStatus.OK);
	}
   
	  @GetMapping("/search")
	    public ResponseEntity<PaginationResponse> searchProducts(
	            @RequestParam(value = "keyword", required = false) String keyword,
	            @RequestParam(value = "status", required = false) String status,
	            @RequestParam("pageNumber") int pageNumber,
	            @RequestParam("pageSize") int pageSize) {
	        
	    
	        return ResponseEntity.ok(this.locationService.searchLocations(keyword,status, pageNumber, pageSize));
	    }
	// assigning the bithak and member to the location
	
}
