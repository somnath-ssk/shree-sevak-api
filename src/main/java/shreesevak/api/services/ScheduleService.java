package shreesevak.api.services;

import java.util.List;

import shreesevak.api.payloads.SchedularDto;
import shreesevak.api.payloads.SchedularFrontendDto;

public interface ScheduleService {

	
	SchedularDto createSchedule(SchedularFrontendDto schedularFrontendDto);

	SchedularDto getSingleSchedule(Integer scheduleId);



	List<SchedularDto> getAllSchedule();

	SchedularDto updateSchedule(SchedularFrontendDto schedularFrontendDto, Integer schedularId);



//	void createSchedule(SchedularFrontendDto schedularFrontendDto);
}
