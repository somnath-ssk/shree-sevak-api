package shreesevak.api.services;

import java.util.List;

import shreesevak.api.model.Location;
import shreesevak.api.model.User;
import shreesevak.api.payloads.LocationDto;

public interface LocationService {

	 LocationDto createLocation(LocationDto loc);
	 LocationDto updateLocation(LocationDto loc,Integer locId );
	 void deleteLocation(Integer locId);
	 LocationDto getLocationById(Integer locId);
	List<LocationDto> getAllLocations();
	List<Location> searchLocations(String keyword);
	
	
	
}
