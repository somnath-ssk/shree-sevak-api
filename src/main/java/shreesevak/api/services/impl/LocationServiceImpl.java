package shreesevak.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import shreesevak.api.exceptions.ResourceAllReadyExist;
import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.helperclass.LocationFrontEnd;
import shreesevak.api.model.Area;
import shreesevak.api.model.City;
import shreesevak.api.model.Country;
import shreesevak.api.model.Division;
import shreesevak.api.model.Location;
import shreesevak.api.model.State;
import shreesevak.api.model.User;
import shreesevak.api.payloads.LocationDto;
import shreesevak.api.payloads.PaginationResponse;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.repository.AreaRepo;
import shreesevak.api.repository.CityRepo;
import shreesevak.api.repository.CountryRepo;
import shreesevak.api.repository.DivisionRepo;
import shreesevak.api.repository.LocationRepo;
import shreesevak.api.repository.StateRepo;
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
	
	@Autowired
   private AreaRepo areaRepo;
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private CountryRepo countryRepo;
	@Autowired
	private DivisionRepo divisionRepo;

	//Create Location Details

	@Override
	public LocationDto createLocation(LocationFrontEnd locDto) {
		
		Optional<City> city	=this.cityRepo.findById(locDto.getCity());
		Optional<State> state	=this.stateRepo.findById(locDto.getState());
		Optional<Division> div	=this.divisionRepo.findById(locDto.getDivision());
		Optional<Country> country	=this.countryRepo.findById(locDto.getCountry());
		Location location=new Location();
		location.setState(state.get());
		    location.setCity(city.get());
		    location.setCountry(country.get());
		    location.setLocationName(locDto.getLocationName());
		    location.setAdd1(locDto.getAdd1());
		    location.setAdd2(locDto.getAdd2());
		    location.setPincode(locDto.getPincode());
		    location.setDivision(div.get());
		    location.setLongitude(locDto.getLongitude());
		    location.setLatitude(locDto.getLatitude());
	        location.setStatus(locDto.getStatus());
	        location.setGoogleMapLink(locDto.getGoogleMapLink());
	        location.setAdd3(locDto.getAdd3());
	        location.setAdd4(locDto.getAdd4());
	        location.setAdditionalInfo(locDto.getAdditionalInfo());
	        location.setContact1Email(locDto.getContact1Email());
	        location.setContact1Initial(locDto.getContact1Initial());
	        location.setContact1Name(locDto.getContact1Name());
	        location.setContact1Occupation(locDto.getContact1Occupation());
	        location.setContact1Phone1(locDto.getContact1Phone1());
	        location.setContact1Phone2(locDto.getContact1Phone2());
	        location.setContact2Email(locDto.getContact2Email());
	        location.setContact2Initial(locDto.getContact2Initial());
	        location.setContact2Name(locDto.getContact2Name());
	        location.setContact2Occupation(locDto.getContact2Occupation());
	        location.setContact2Phone1(locDto.getContact2Phone1());
	        location.setContact2Phone2(locDto.getContact2Phone2());
	     location.setMixedGenderAllow(locDto.isMixedGenderAllow());
	Optional<Area> area	=this.areaRepo.findById(locDto.getArea());
	location.setArea(area.get());
	
		  
	      
	 Location saveLoc=this.locationRepo.save(location);
		return this.locationToDto(saveLoc);
	}

	//Update Location 
	@Override
	public LocationDto updateLocation(LocationFrontEnd locDto, Integer locId) {
		
		Optional<City> city	=this.cityRepo.findById(locDto.getCity());
		Optional<State> state	=this.stateRepo.findById(locDto.getState());
		Optional<Division> div	=this.divisionRepo.findById(locDto.getDivision());
		Optional<Country> country	=this.countryRepo.findById(locDto.getCountry());
	   Location loc= this.locationRepo.findById(locId).orElseThrow(()-> new ResourceNotFoundException("Location","id",locId));
	
	    loc.setLocationName(locDto.getLocationName());
	    loc.setAdd1(locDto.getAdd1());
	    loc.setAdd2(locDto.getAdd2());
	    loc.setPincode(locDto.getPincode());
	    loc.setDivision(div.get());
	    loc.setState(state.get());
	    loc.setCity(city.get());
	    loc.setCountry(country.get());
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
        loc.setContact2Occupation(locDto.getContact2Occupation());
        loc.setContact2Phone1(locDto.getContact2Phone1());
        loc.setContact2Phone2(locDto.getContact2Phone2());
     loc.setMixedGenderAllow(locDto.isMixedGenderAllow());
     Optional<Area> area	=this.areaRepo.findById(locDto.getArea());
     loc.setArea(area.get());
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
	@Override
	public List<LocationDto> getAllLocationByAreaId(Integer areaId) {
		try {
			List<Location> locationList=this.locationRepo.findByLocationAreaId(areaId);
			List<LocationDto>locDtos =  locationList.stream().map(loc->this.locationToDto(loc)).collect(Collectors.toList());
			return locDtos;
		}catch(RuntimeException ex) {
			throw new ResourceNotFoundException("No area present with this area in location", "=", areaId);
			
		}
		
	}
	//search location
	@Override
//	public List<Location> searchLocations(String keyword) {
////	List<Location>locatinoKeyword	=locationRepo.searchLocation(keyword);
//	
//            return locatinoKeyword ;
//	}
	//get all active locations
	
	public List<LocationDto> getAllActiveLocation(String status) {
		List<Location> activeLoc= this.locationRepo.findAllByStatus(status);
		List<LocationDto>allActiveLoc=activeLoc.stream().map(location ->this.locationToDto(location)).collect(Collectors.toList());
		return allActiveLoc;
	}
	
	@Override
	public PaginationResponse getAllLocations(Integer pageNumber, Integer pageSize) {
		Pageable p = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "locationId"));
		Page<Location> page=this.locationRepo.findAll(p);
		List<Location>locationList=page.getContent();
		List<LocationDto>locationDtoList=locationList.stream().map(loc->this.locationToDto(loc)).collect(Collectors.toList());
	PaginationResponse paginationResponse=new PaginationResponse();
	paginationResponse.setContent(locationDtoList);
	paginationResponse.setLastPage(page.isLast());
	paginationResponse.setPageNumber(page.getNumber());
	paginationResponse.setPageSize(page.getSize());
	paginationResponse.setTotalPages(page.getTotalPages());
	paginationResponse.setTotoalElement(page.getTotalElements());
		return paginationResponse;
	}

	@Override
	public PaginationResponse getAllActiveLocation(Integer pageNumber, Integer pageSize, String status) {
		Pageable p = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "locationId"));
		Page<Location> page=this.locationRepo.findAllByStatus(status, p);
		List<Location>locationList=page.getContent();
		List<LocationDto>locationDtoList=locationList.stream().map(loc->this.locationToDto(loc)).collect(Collectors.toList());
	PaginationResponse paginationResponse=new PaginationResponse();
	paginationResponse.setContent(locationDtoList);
	paginationResponse.setLastPage(page.isLast());
	paginationResponse.setPageNumber(page.getNumber());
	paginationResponse.setPageSize(page.getSize());
	paginationResponse.setTotalPages(page.getTotalPages());
	paginationResponse.setTotoalElement(page.getTotalElements());
		return paginationResponse;
		
	}
	@Override
	public PaginationResponse searchLocations(String keyword,String status, Integer pageNumber, Integer pageSize) {
		if(status.equals("null")) {
			Pageable p = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "locationId"));
			Page<Location> page=this.locationRepo.searchLocation(keyword, p);
			List<Location>locationList=page.getContent();
			List<LocationDto>locationDtoList=locationList.stream().map(loc->this.locationToDto(loc)).collect(Collectors.toList());
		PaginationResponse paginationResponse=new PaginationResponse();
		paginationResponse.setContent(locationDtoList);
		paginationResponse.setLastPage(page.isLast());
		paginationResponse.setPageNumber(page.getNumber());
		paginationResponse.setPageSize(page.getSize());
		paginationResponse.setTotalPages(page.getTotalPages());
		paginationResponse.setTotoalElement(page.getTotalElements());
			return paginationResponse;
		}else {
			Pageable p = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "locationId"));
			Page<Location> page=this.locationRepo.searchLocation(keyword,status, p);
			List<Location>locationList=page.getContent();
			List<LocationDto>locationDtoList=locationList.stream().map(loc->this.locationToDto(loc)).collect(Collectors.toList());
		PaginationResponse paginationResponse=new PaginationResponse();
		paginationResponse.setContent(locationDtoList);
		paginationResponse.setLastPage(page.isLast());
		paginationResponse.setPageNumber(page.getNumber());
		paginationResponse.setPageSize(page.getSize());
		paginationResponse.setTotalPages(page.getTotalPages());
		paginationResponse.setTotoalElement(page.getTotalElements());
			return paginationResponse;
		}
	
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
