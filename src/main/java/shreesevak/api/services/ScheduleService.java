package shreesevak.api.services;

import java.util.List;

import shreesevak.api.payloads.SchedularDto;

public interface ScheduleService {

	
	SchedularDto createSchedule(Integer locationId,Integer baithakId,List<Integer> memberIds);
}
