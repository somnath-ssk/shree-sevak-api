package shreesevak.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import shreesevak.api.exceptions.ResourceAllReadyExist;
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
		return this.locationToDto(saveLoc);
	}

	//Update Location 
	@Override
	public LocationDto updateLocation(LocationDto locDto, Integer locId) {
	   Location loc= this.locationRepo.findById(locId).orElseThrow(()-> new ResourceNotFoundException("Location","id",locId));
	    loc.setState(locDto.getState());
	    loc.setCity(locDto.getCity());
	    loc.setCountry(locDto.getCountry());
	    loc.setLocationName(locDto.getLocationName());
	    loc.setAdd1(locDto.getAdd1());
	    loc.setAdd2(locDto.getAdd2());
	    loc.setPincode(locDto.getPincode());
	    loc.setDivision(locDto.getDivision());
	  
	    loc.setLongitude(locDto.getLongitude());
	    loc.setLatitude(locDto.getLatitude());
        loc.setStatus(locDto.getStatus());
        loc.setGoogleMapLink(locDto.getGoogleMapLink());
        loc.setAdd3(locDto.getAdd3());
        loc.setAdd4(locDto.getAdd4());
        loc.setAdditionalInfo(locDto.getAdditionalInfo());
        loc.setContact1Email(locDto.getContact1Email());
        loc.setContact1Initial(locDto.getContact1Initial());
        loc.setContact1Name(locDto.getContact1Name());
        loc.setContact1Occupation(locDto.getContact1Occupation());
        loc.setContact1Phone1(locDto.getContact1Phone1());
        loc.setContact1Phone2(locDto.getContact1Phone2());
        loc.setContact2Email(locDto.getContact2Email());
        loc.setContact2Initial(locDto.getContact2Initial());
        loc.setContact2Name(locDto.getContact2Name());
        loc.setContact2Phone1(locDto.getContact2Phone1());
        loc.setContact2Phone2(locDto.getContact2Phone2());
        
      Location saveLoc=  this.locationRepo.save(loc);
       return locationToDto(saveLoc);
	}

	
	//Delete location
	@Override
	public void deleteLocation(Integer locId) {
		Location loc=this.locationRepo.findById(locId).orElseThrow(()-> new ResourceNotFoundException("Location", "id",locId));
		this.locationRepo.delete(loc);
		
	}

	
	//get loction by id
	@Override
	public LocationDto getLocationById(Integer locId) {
		Location loc=this.locationRepo.findById(locId).orElseThrow(()-> new ResourceNotFoundException("Location", "id",locId));
	     
		return this.locationToDto(loc);
	}
     
	
	//get all location
	@Override
	public List<LocationDto> getAllLocations() {
	List<Location> locList=	this.locationRepo.findAllByOrderDesc();
	List<LocationDto>locDtos =  locList.stream().map(loc->this.locationToDto(loc)).collect(Collectors.toList());
		return locDtos;
	}
	
	//search location
	@Override
	public List<Location> searchLocations(String keyword) {
	List<Location>locatinoKeyword	=locationRepo.searchLocation(keyword);
	
            return locatinoKeyword ;
	}
	//get all active locations
	
	public List<LocationDto> getAllActiveLocation(String status) {
		List<Location> activeLoc= this.locationRepo.findAllByStatus(status);
		List<LocationDto>allActiveLoc=activeLoc.stream().map(location ->this.locationToDto(location)).collect(Collectors.toList());
		return allActiveLoc;
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
