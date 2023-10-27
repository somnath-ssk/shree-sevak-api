package shreesevak.api.services;

import java.util.List;

import shreesevak.api.model.Scheduler;
import shreesevak.api.payloads.SchedularDto;
import shreesevak.api.payloads.SchedularFrontendDto;

public interface ScheduleService {

	
	SchedularDto createSchedule(SchedularFrontendDto schedularFrontendDto);

	SchedularDto getSingleSchedule(Integer scheduleId);



	List<SchedularDto> getAllSchedule();

	SchedularDto updateSchedule(SchedularFrontendDto schedularFrontendDto);

//	SchedularDto getScheduleByDateLocBaithak(String date, Integer locId, Integer baithakId);

	Scheduler getScheduleByDateLocationBaithak(String date, Integer locId,Integer baithakId);

	List<Scheduler> getScheduleByMonthAndYear(String month, String year);

//	void createSchedule(SchedularFrontendDto schedularFrontendDto);
}
