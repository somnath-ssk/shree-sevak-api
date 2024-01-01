package shreesevak.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.State;
import shreesevak.api.payloads.StateDto;
import shreesevak.api.repository.StateRepo;
import shreesevak.api.services.StateService;

@Service
public class StateServiceImpl implements StateService{

	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<StateDto> getAllStates(long countryId) {
	try {
		List<State> states=this.stateRepo.findAllStateByCountryId(countryId);
		List<StateDto> stateDtos=states.stream().map((st)-> this.StateDto(st)).collect(Collectors.toList());
		
		return stateDtos;
	}catch(RuntimeException exception) {
		throw new ResourceNotFoundException("Resource not found", "=", countryId);
	}
	}


	public StateDto StateDto(State state) {
		StateDto stateDto=new StateDto();
		stateDto.setId(state.getId());
		stateDto.setStateName(state.getName());
		return stateDto;
//		return this.modelMapper.map(state,StateDto.class);
		
	}
	public State DtoState(StateDto stateDto) {
//		return this.modelMapper.map(stateDto,State.class);
		State state=new State();
		state.setId(stateDto.getId());
		state.setName(state.getName());
		return state;
		
	}
}
