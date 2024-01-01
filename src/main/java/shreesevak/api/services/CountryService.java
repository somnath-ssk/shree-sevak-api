package shreesevak.api.services;

import java.util.List;

import shreesevak.api.payloads.CountryDto;
import shreesevak.api.payloads.StateDto;

public interface CountryService {


	List<CountryDto> getAllCountryData();
	
	CountryDto getCountryData(long id) ;
		

	

}
