package shreesevak.api.services;

import java.util.List;

import shreesevak.api.payloads.StateDto;

public interface StateService {
	List<StateDto> getAllStates(long countryId);

}
