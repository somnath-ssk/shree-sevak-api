package shreesevak.api.services;

import java.util.List;

import shreesevak.api.payloads.DivisionDto;

public interface DivisionService {
	
	List<DivisionDto> getAllDivision(Long countryId, Long stateId, Long cityId);
}
