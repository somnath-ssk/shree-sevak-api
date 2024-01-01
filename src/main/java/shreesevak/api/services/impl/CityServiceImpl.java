package shreesevak.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.City;
import shreesevak.api.model.State;
import shreesevak.api.model.Country;
import shreesevak.api.payloads.CityDto;
import shreesevak.api.repository.CityRepo;
import shreesevak.api.repository.CountryRepo;
import shreesevak.api.services.CityService;
import shreesevak.api.services.CountryService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CountryRepo countryRepo;
	
	
	@Autowired 
	private CityRepo cityRepo;
	
	@Autowired ModelMapper mapper;
	
	
//	@Override
//	public List<CityDto> getAllCities(Long countryId, Long stateId) {
//	    Country country =  this.countryRepo.findById(countryId).orElseThrow(()-> new ResourceNotFoundException("not found exception", "=", countryId));
//	   State st = country.getStates().stream().filter(state-> state.getId()==stateId).findFirst();
//	     List<City> cities=st.getCities();
//	  List<CityDto> listOfCities=  cities.stream().map(city->this.cityDto(city)).collect(Collectors.toList());
//		return listOfCities;
//	}
	
	@Override
	public List<CityDto> getAllCities(Long countryId, Long stateId) {
	    Country country = this.countryRepo.findById(countryId)
	            .orElseThrow(() -> new ResourceNotFoundException("Country not found", "=", countryId));

	    Optional<State> optionalState = country.getStates().stream()
	            .filter(state -> state.getId().equals(stateId))
	            .findFirst();

	    if (optionalState.isPresent()) {
	        List<City> cities = optionalState.get().getCities();
	        List<CityDto> listOfCities = cities.stream().map(this::cityDto).collect(Collectors.toList());
	        return listOfCities;
	    } else {
	        throw new ResourceNotFoundException("State not found", "=", stateId);
	    }
	}

 public CityDto cityDto(City city) {
	 CityDto  cityDto=new CityDto();
	 cityDto.setId(city.getId());
	 cityDto.setCityName(city.getName());
	 return cityDto;
 }
 public City DtoToCity(CityDto cityDto) {
	 City  city=new City();
	 city.setId(cityDto.getId());
	 city.setName(cityDto.getCityName());
	 return city;
 }
	
}
