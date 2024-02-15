package shreesevak.api.services;

import java.util.List;

import shreesevak.api.helperclass.LocationFrontEnd;
import shreesevak.api.model.Location;
import shreesevak.api.model.User;
import shreesevak.api.payloads.LocationDto;
import shreesevak.api.payloads.PaginationResponse;

public interface LocationService {

	 LocationDto createLocation(LocationFrontEnd locfrontDto);
	 LocationDto updateLocation(LocationFrontEnd loc,Integer locId );
	 void deleteLocation(Integer locId);
	 LocationDto getLocationById(Integer locId);
	List<LocationDto> getAllLocations();
	PaginationResponse getAllLocations(Integer pageNumber, Integer pageSize);
	PaginationResponse searchLocations(String keyword,String status,Integer pageNumber, Integer pageSize);
	
	List<LocationDto> getAllActiveLocation(String status);
	PaginationResponse getAllActiveLocation(Integer pageNumber, Integer pageSize,String status);
	List<LocationDto> getAllLocationByAreaId(Integer areaId);
	
	
	
}
