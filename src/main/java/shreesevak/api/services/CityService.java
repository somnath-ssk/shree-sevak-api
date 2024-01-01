package shreesevak.api.services;

import java.util.List;

import shreesevak.api.payloads.CityDto;

public interface CityService {

	List<CityDto> getAllCities(Long countryId,Long stateId);
}
