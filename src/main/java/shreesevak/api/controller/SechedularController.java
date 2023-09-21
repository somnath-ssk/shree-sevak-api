package shreesevak.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shreesevak.api.model.Schedular;
import shreesevak.api.payloads.SchedularDto;
import shreesevak.api.services.ScheduleService;

@RestController
@RequestMapping("/api/schedular")
public class SechedularController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@PostMapping("/create-schedule")
	public ResponseEntity<SchedularDto>createSchedule(@RequestBody Integer locationId,@RequestBody Integer baithakId,@RequestBody List<Integer> memberIds){
		System.out.println(locationId.toString());
		System.out.println(baithakId.toString());
		System.out.println(memberIds.toString());
		SchedularDto createdScheduleDto =this.scheduleService.createSchedule(locationId, baithakId, memberIds);
		return new ResponseEntity<SchedularDto>(createdScheduleDto,HttpStatus.CREATED);
	}

}
