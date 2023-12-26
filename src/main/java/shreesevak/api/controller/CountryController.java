package shreesevak.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shreesevak.api.payloads.CountryDto;
import shreesevak.api.services.CountryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/country")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	  @GetMapping("/all")
	    public ResponseEntity<List<CountryDto>> getAllCountryData() {
		  List<CountryDto> countries    =  this.countryService.getAllCountryData();
	        return new ResponseEntity<List<CountryDto>>(countries,HttpStatus.OK);
	    }
//	  @GetMapping("/countryId")
//	  public List<CountryDto> getCountryData(@RequestParam Long countryId) {
//		  return this.countryService.getCountryData(countryId);
//	  }

}
