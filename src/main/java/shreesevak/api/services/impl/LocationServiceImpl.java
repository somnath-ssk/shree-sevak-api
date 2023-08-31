package shreesevak.api.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.Location;
import shreesevak.api.model.User;
import shreesevak.api.payloads.LocationDto;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.repository.LocationRepo;
import shreesevak.api.repository.UserRepo;
import shreesevak.api.services.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepo locationRepo;
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	
	//Create Location Details

	@Override
	public LocationDto createLocation(LocationDto locDto) {
		Location loc=this.dtoToLocation(locDto);
		Location saveLoc=this.locationRepo.save(loc);
		return locationToDto(saveLoc);
	}

	//Update Location 
	@Override
	public LocationDto updateLocation(LocationDto locDto, Integer locId) {
	   Location loc= this.locationRepo.findById(locId).orElseThrow(()-> new ResourceNotFoundException("Location","id",locId));
	    loc.setState(locDto.getAddress());
	    loc.setCity(locDto.getCity());
	    loc.setCountry(locDto.getCountry());
	    loc.setAddress(locDto.getAddress());
	    loc.setDivision(locDto.getDivision());
        loc.setStatus(locDto.getStatus());
      Location saveLoc=  this.locationRepo.save(loc);
       return locationToDto(saveLoc);
	}

	
	//Delete location
	@Override
	public void deleteLocation(Integer locId) {
		Location loc=this.locationRepo.findById(locId).orElseThrow(()-> new ResourceNotFoundException("Location", "id",locId));
		this.locationRepo.delete(loc);
		
	}

	
	@Override
	public LocationDto getLocationById(Integer locId) {
		Location loc=this.locationRepo.findById(locId).orElseThrow(()-> new ResourceNotFoundException("Location", "id",locId));
	     
		return this.locationToDto(loc);
	}
     
	@Override
	public List<LocationDto> getAllLocations() {
	List<Location> locList=	this.locationRepo.findAll();
	List<LocationDto>locDtos =  locList.stream().map(loc->this.locationToDto(loc)).collect(Collectors.toList());
		return locDtos;
	}
	
	//
	@Override
	public List<Location> searchLocations(String keyword) {
	
            return locationRepo.searchLocation(keyword);
	}
	
	
	
	public Location dtoToLocation(LocationDto locDto)
	{
		Location loc=this.modelMapper.map(locDto, Location.class);
		return loc;
	}
	
	public LocationDto locationToDto(Location loc) {
		LocationDto locDto=this.modelMapper.map(loc,LocationDto.class);
		return locDto;
	}


	
}
