package shreesevak.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.model.Model;
import shreesevak.api.model.WeeklyOff;
import shreesevak.api.payloads.WeeklyOffDto;
import shreesevak.api.repository.WeekRepo;
import shreesevak.api.services.WeekService;

@Service
public class weekImpl implements WeekService {

	@Autowired
	private WeekRepo weekRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public List<WeeklyOff> getAllDays() {
		// TODO Auto-generated method stub
		List<WeeklyOff> weekdays = this.weekRepo.findAll();
		
		return weekdays;
	}

}
