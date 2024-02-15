package shreesevak.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import shreesevak.api.model.City;
import shreesevak.api.model.Country;
import shreesevak.api.model.Division;
import shreesevak.api.model.State;
import shreesevak.api.payloads.CityDto;
import shreesevak.api.payloads.CountryDto;
import shreesevak.api.payloads.DivisionDto;
import shreesevak.api.payloads.StateDto;
import shreesevak.api.repository.CountryRepo;
import shreesevak.api.services.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	  @Autowired
	    private CountryRepo countryRepository;
	  
	  
	  @Override
	  public List<CountryDto> getAllCountryData(){
		  List<Country> countries=  this.countryRepository.findAll();
		  return countries.stream()
	                .map(this::convertToDto)
	                .collect(Collectors.toList());
	  }

	  @Override
	    public CountryDto getCountryData(long countryId) {
	        Country country = countryRepository.findById(countryId)
	                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + countryId));

	        return convertToDto(country);
	    }

		
	  
	  
	    private CountryDto convertToDto(Country country) {
	        CountryDto locationDto = new CountryDto();
	        locationDto.setCountryName(country.getName());
	        locationDto.setId(country.getId());

	        List<StateDto> stateDtos = country.getStates().stream()
	                .map(this::convertToDto)
	                .collect(Collectors.toList());

	        locationDto.setStates(stateDtos);
	        return locationDto;
	    }

	    private StateDto convertToDto(State state) {
	        StateDto stateDto = new StateDto();
	        stateDto.setStateName(state.getName());
	        stateDto.setId(state.getId());

	        List<CityDto> cityDtos = state.getCities().stream()
	                .map(this::convertToDto)
	                .collect(Collectors.toList());

	        stateDto.setCities(cityDtos);
	        return stateDto;
	    }

	    private CityDto convertToDto(City city) {
	        CityDto cityDto = new CityDto();
	        cityDto.setCityName(city.getName());
	        cityDto.setId(city.getId());

	        List<DivisionDto> divisionDtos = city.getDivisions().stream()
	                .map(this::convertToDto)
	                .collect(Collectors.toList());

	        cityDto.setDivisions(divisionDtos);
	        return cityDto;
	    }
	    
	    private DivisionDto convertToDto(Division division) {
	        DivisionDto divisionDto = new DivisionDto();
	        divisionDto.setDivisionName(division.getName());
	        divisionDto.setId(division.getId());
	        return divisionDto;
	    }

	

		

	

}
