package shreesevak.api.services;

import java.util.List;

import shreesevak.api.helperclass.Schedule2FrontEnd;
import shreesevak.api.model.Scheduler;
import shreesevak.api.payloads.PaginationResponse;
import shreesevak.api.payloads.SchedularDto;
import shreesevak.api.payloads.SchedularFrontendDto;
import shreesevak.api.payloads.Schedule2Dto;

public interface Schedule2Service {
 
	Schedule2Dto createSchedule(Schedule2FrontEnd schedule);


	Schedule2Dto getSingleSchedule(Integer scheduleId);



	List<Schedule2Dto> getAllSchedule();
//	PaginationResponse getAllSchedule(Integer pageNumber,Integer pageSize);

	Schedule2Dto updateSchedule(Schedule2FrontEnd schedularFrontendDto);


}
