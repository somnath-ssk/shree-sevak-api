package shreesevak.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import shreesevak.api.helperclass.PeopleScheduleFrontEnd;
import shreesevak.api.model.Scheduler;
import shreesevak.api.payloads.PeopleScheduleDto;
import shreesevak.api.payloads.SchedularDto;
import shreesevak.api.payloads.SchedularFrontendDto;
import shreesevak.api.services.PeopleScheduleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/people")
public class PeopleScheduleController {
	
	@Autowired
	private PeopleScheduleService peopleScheduleService;
	
	
	@PostMapping ("/createschedule")
	public ResponseEntity<List<PeopleScheduleDto>> createSchedules(
			@RequestBody List<PeopleScheduleFrontEnd> schedularFrontendDto) {
//		System.out.println(schedularFrontendDto.toString());
		List<PeopleScheduleDto> schedularDtos = new ArrayList<>();
		for (PeopleScheduleFrontEnd frontendDto : schedularFrontendDto) {
 PeopleScheduleDto schedularDto = this.peopleScheduleService.createSchedule(frontendDto);
			schedularDtos.add(schedularDto);
		}

		return new ResponseEntity<>(schedularDtos, HttpStatus.CREATED);

	}
	
	@GetMapping("/{scheduleId}")
	public ResponseEntity<PeopleScheduleDto> getScheduleById(@PathVariable Integer scheduleId) {

		PeopleScheduleDto schedularDto = this.peopleScheduleService.getSingleSchedule(scheduleId);
		return new ResponseEntity<PeopleScheduleDto>(schedularDto, HttpStatus.OK);
	}
	
	@GetMapping("/allschedules")
	public ResponseEntity<List<PeopleScheduleDto>> scheduleList() {

		List<PeopleScheduleDto> schedularDtos = this.peopleScheduleService.getAllSchedule();
		return new ResponseEntity<List<PeopleScheduleDto>>(schedularDtos, HttpStatus.OK);
	}
	@PutMapping("/update-schedule")
	public ResponseEntity<List<PeopleScheduleDto>> updateSchedule(
			@RequestBody List<PeopleScheduleFrontEnd> schedularFrontendDto) {

		System.out.println(schedularFrontendDto.toString());
		List<PeopleScheduleDto> schedularDtos = new ArrayList<>();
		for (PeopleScheduleFrontEnd frontendDto : schedularFrontendDto) {
			System.out.println("schdule id  "+frontendDto.getScheduleId());
			if (frontendDto.getScheduleId() != 0) {
				PeopleScheduleDto schedularDto = this.peopleScheduleService.updateSchedule(frontendDto);
				schedularDtos.add(schedularDto);

			}else {
				PeopleScheduleDto schedularDto = this.peopleScheduleService.createSchedule(frontendDto);
				schedularDtos.add(schedularDto);
			}
		}

		return new ResponseEntity<>(schedularDtos, HttpStatus.CREATED);

	}
	@GetMapping("/getByDateLocationBaithak/{date}/{locationId}/{baithakId}")
	public ResponseEntity<PeopleScheduleDto> getByDateLocBai(@PathVariable String date, @PathVariable Integer locationId, @PathVariable Integer baithakId) {
		
		System.out.println("inside getByDateLoc");
		PeopleScheduleDto schedular = this.peopleScheduleService.getScheduleByDateLocationBaithak(date, locationId,baithakId);
		if(schedular==null) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "schedule not found ");
		}
		else {
			return new ResponseEntity<PeopleScheduleDto>(schedular, HttpStatus.OK);
		}
	}
	
	
	
}
