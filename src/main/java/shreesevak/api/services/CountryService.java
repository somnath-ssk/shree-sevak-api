package shreesevak.api.services;

import java.util.List;

import shreesevak.api.payloads.CountryDto;

public interface CountryService {


	List<CountryDto> getAllCountryData();
	
	CountryDto getCountryData(long id) ;
		
	
	

}
