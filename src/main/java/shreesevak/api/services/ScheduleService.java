package shreesevak.api.services;

import java.util.List;

import shreesevak.api.model.Scheduler;
import shreesevak.api.payloads.PaginationResponse;
import shreesevak.api.payloads.SchedularDto;
import shreesevak.api.payloads.SchedularFrontendDto;

public interface ScheduleService {

	
	SchedularDto createSchedule(SchedularFrontendDto schedularFrontendDto);

	SchedularDto getSingleSchedule(Integer scheduleId);



	List<SchedularDto> getAllSchedule();
	PaginationResponse getAllSchedule(Integer pageNumber,Integer pageSize);

	SchedularDto updateSchedule(SchedularFrontendDto schedularFrontendDto);

//	SchedularDto getScheduleByDateLocBaithak(String date, Integer locId, Integer baithakId);

	Scheduler getScheduleByDateLocationBaithak(String date, Integer locId,Integer baithakId);

	List<Scheduler> getScheduleByMonthAndYearAndBaithak(String month, String year,Integer baithakId);

	PaginationResponse searchSchedule(String keyword,int pageNumber, int pageSize);

//	void createSchedule(SchedularFrontendDto schedularFrontendDto);
}
