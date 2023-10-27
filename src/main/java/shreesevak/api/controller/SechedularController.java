package shreesevak.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
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

import shreesevak.api.model.Scheduler;
import shreesevak.api.payloads.DateLocationDto;
import shreesevak.api.payloads.RoleDto;
import shreesevak.api.payloads.SchedularDto;
import shreesevak.api.payloads.SchedularFrontendDto;
import shreesevak.api.services.ScheduleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/schedular")
public class SechedularController {

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping("/create-schedule")
	public ResponseEntity<List<SchedularDto>> createSchedules(
			@RequestBody List<SchedularFrontendDto> schedularFrontendDto) {
		System.out.println(schedularFrontendDto.toString());
		List<SchedularDto> schedularDtos = new ArrayList<>();
		for (SchedularFrontendDto frontendDto : schedularFrontendDto) {
			SchedularDto schedularDto = this.scheduleService.createSchedule(frontendDto);
			schedularDtos.add(schedularDto);
		}

		return new ResponseEntity<>(schedularDtos, HttpStatus.CREATED);

	}

	@GetMapping("/{scheduleId}")
	public ResponseEntity<SchedularDto> getScheduleById(@PathVariable Integer scheduleId) {

		SchedularDto schedularDto = this.scheduleService.getSingleSchedule(scheduleId);
		return new ResponseEntity<SchedularDto>(schedularDto, HttpStatus.OK);
	}

	@GetMapping("/all-schedules")
	public ResponseEntity<List<SchedularDto>> scheduleList() {

		List<SchedularDto> schedularDtos = this.scheduleService.getAllSchedule();
		return new ResponseEntity<List<SchedularDto>>(schedularDtos, HttpStatus.OK);
	}

	@PutMapping("/update-schedule")
	public ResponseEntity<List<SchedularDto>> updateSchedule(
			@RequestBody List<SchedularFrontendDto> schedularFrontendDto) {

		System.out.println(schedularFrontendDto.toString());
		List<SchedularDto> schedularDtos = new ArrayList<>();
		for (SchedularFrontendDto frontendDto : schedularFrontendDto) {
			System.out.println("schdule id  "+frontendDto.getScheduleId());
			if (frontendDto.getScheduleId() != 0) {
				SchedularDto schedularDto = this.scheduleService.updateSchedule(frontendDto);
				schedularDtos.add(schedularDto);

			}
		}

		return new ResponseEntity<>(schedularDtos, HttpStatus.CREATED);

	}

	@GetMapping("/getByDateLocationBaithak/{date}/{locationId}/{baithakId}")
	public ResponseEntity<Scheduler> getByDateLocBai(@PathVariable String date, @PathVariable Integer locationId, @PathVariable Integer baithakId) {
		
		System.out.println("inside getByDateLoc");
		Scheduler schedular = this.scheduleService.getScheduleByDateLocationBaithak(date, locationId,baithakId);
		return new ResponseEntity<Scheduler>(schedular, HttpStatus.OK);
	}
	
	@GetMapping("/getByMonthAndYear/{month}/{year}")
	public ResponseEntity<List<Scheduler>> getByDateLocBai(@PathVariable String month, @PathVariable String year) {
		
		System.out.println("inside getByDateLoc");
		List<Scheduler> scheduls = this.scheduleService.getScheduleByMonthAndYear(month,year);
		return new ResponseEntity<List<Scheduler>>(scheduls, HttpStatus.OK);
	}

}
