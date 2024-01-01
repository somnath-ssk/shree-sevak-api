package shreesevak.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.City;
import shreesevak.api.model.Country;
import shreesevak.api.model.Division;
import shreesevak.api.model.State;
import shreesevak.api.payloads.DivisionDto;
import shreesevak.api.repository.CityRepo;
import shreesevak.api.repository.CountryRepo;
import shreesevak.api.repository.StateRepo;
import shreesevak.api.services.DivisionService;


@Service
public class DivisionServiceImpl implements DivisionService {
	
	
     @Autowired
     private CountryRepo countryRepo;
     
     @Autowired 
     private StateRepo stateRepo;
     
     @Autowired
     private CityRepo cityRepo;
     
     @Autowired
     private ModelMapper modelMapper;
     
	
	@Override
	public List<DivisionDto> getAllDivision(Long countryId, Long stateId, Long cityId) {
		
         	Optional<Country> country= this.countryRepo.findById(countryId);
            Optional<State> fundState	=country.get().getStates().stream().filter(state-> state.getId().equals(stateId)).findFirst();
            Optional<City> foundCity	=fundState.get().getCities().stream().filter(city-> city.getId().equals(cityId)).findFirst();
    	
         List<Division> divisions= foundCity.get().getDivisions();
    	List<DivisionDto> disionsList= divisions.stream().map(this::divisionToDto).collect(Collectors.toList());
         
         
		return disionsList;
	}
	
	public DivisionDto divisionToDto(Division division) {
		DivisionDto dto=new DivisionDto();
		dto.setId(division.getId());
		dto.setDivisionName(division.getName());
		return dto;
	}
	public Division DtoToDivision(DivisionDto divisionDto) {
		Division div=new Division();
		div.setId(divisionDto.getId());
		div.setName(divisionDto.getDivisionName());
		return div;
	}
	
	

}
