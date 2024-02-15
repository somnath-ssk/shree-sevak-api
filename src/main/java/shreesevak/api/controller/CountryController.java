package shreesevak.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shreesevak.api.payloads.CityDto;
import shreesevak.api.payloads.CountryDto;
import shreesevak.api.payloads.DivisionDto;
import shreesevak.api.payloads.StateDto;
import shreesevak.api.services.CityService;
import shreesevak.api.services.CountryService;
import shreesevak.api.services.DivisionService;
import shreesevak.api.services.StateService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/country")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired 
	private CityService cityService;
	
	@Autowired
	private DivisionService divisionService;
	
	  @GetMapping("/all")
	    public ResponseEntity<List<CountryDto>> getAllCountryData() {
		  List<CountryDto> countries    =  this.countryService.getAllCountryData();
	        return new ResponseEntity<List<CountryDto>>(countries,HttpStatus.OK);
	    }
	  @GetMapping("/{countryId}")
	  public ResponseEntity<CountryDto> getCountryData(@PathVariable Long countryId) {
		 CountryDto country= this.countryService.getCountryData(countryId);
		 return new ResponseEntity<CountryDto>(country,HttpStatus.OK);
		  
	  }
	  
	  @GetMapping("/{countryId}/state/all")
	  public ResponseEntity<List<StateDto>> getStateAllData(@PathVariable Long countryId){
		  List<StateDto> states=this.stateService.getAllStates(countryId);
		  return new ResponseEntity<List<StateDto>>(states,HttpStatus.OK);
		  
	  }
	  @GetMapping("/{countryId}/state/{stateId}/allCities")
	  public ResponseEntity<List<CityDto>> getCityAllData(@PathVariable Long countryId,@PathVariable Long stateId){
		  List<CityDto> states=this.cityService.getAllCities(countryId,stateId);
		  return new ResponseEntity<List<CityDto>>(states,HttpStatus.OK);
		  
	  }
	  @GetMapping("/{countryId}/state/{stateId}/city/{cityId}/all-divisions")
	  public ResponseEntity<List<DivisionDto>> getAllDivisionData(@PathVariable Long countryId,@PathVariable Long stateId,@PathVariable Long cityId){
		  List<DivisionDto> divisions=this.divisionService.getAllDivision(countryId,stateId,cityId);
		  return new ResponseEntity<List<DivisionDto>>(divisions,HttpStatus.OK);
		  
	  }
	

}
