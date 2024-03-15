package shreesevak.api.services;

import java.util.List;

import shreesevak.api.helperclass.PeopleScheduleFrontEnd;

import shreesevak.api.model.Scheduler;
import shreesevak.api.payloads.PaginationResponse;
import shreesevak.api.payloads.PeopleScheduleDto;
import shreesevak.api.payloads.SchedularDto;
import shreesevak.api.payloads.SchedularFrontendDto;


public interface PeopleScheduleService{
 
	PeopleScheduleDto createSchedule(PeopleScheduleFrontEnd schedule);


	PeopleScheduleDto getSingleSchedule(Integer scheduleId);



	List<PeopleScheduleDto> getAllSchedule();
//	PaginationResponse getAllSchedule(Integer pageNumber,Integer pageSize);

	PeopleScheduleDto updateSchedule(PeopleScheduleFrontEnd schedularFrontendDto);
	PeopleScheduleDto getScheduleByDateLocation(String date, Integer locId);
	PeopleScheduleDto getScheduleByDateLocationBaithak(String date, Integer locId,Integer baithakId);
//	List<PeopleScheduleDto> getScheduleByMonthAndYearAndBaithak(String month, String year,Integer baithakId);
}
