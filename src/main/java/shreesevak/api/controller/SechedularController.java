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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import shreesevak.api.model.Scheduler;
import shreesevak.api.payloads.DateLocationDto;
import shreesevak.api.payloads.PaginationResponse;
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
	
	@GetMapping("/pagination/all-schedules")
	public ResponseEntity<PaginationResponse> scheduleList(@RequestParam(value ="pageNumber",defaultValue = "0",required = false) Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "10", required = false)Integer pageSize) {
		
		PaginationResponse paginationResponse = this.scheduleService.getAllSchedule(pageNumber, pageSize);
		return new ResponseEntity<PaginationResponse>(paginationResponse, HttpStatus.OK);
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

			}else {
				SchedularDto schedularDto = this.scheduleService.createSchedule(frontendDto);
				schedularDtos.add(schedularDto);
			}
		}

		return new ResponseEntity<>(schedularDtos, HttpStatus.CREATED);

	}

	@GetMapping("/getByDateLocationBaithak/{date}/{locationId}/{baithakId}")
	public ResponseEntity<Scheduler> getByDateLocBai(@PathVariable String date, @PathVariable Integer locationId, @PathVariable Integer baithakId) {
		
		System.out.println("inside getByDateLoc");
		Scheduler schedular = this.scheduleService.getScheduleByDateLocationBaithak(date, locationId,baithakId);
		if(schedular==null) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "schedule not found ");
		}
		else {
			return new ResponseEntity<Scheduler>(schedular, HttpStatus.OK);
		}
	}
	
	@GetMapping("/getByMonthAndYearAndBaithak/{month}/{year}/{baithakId}")
	public ResponseEntity<List<Scheduler>> getByDateLocBai(@PathVariable String month, @PathVariable String year ,@PathVariable Integer baithakId) {
		
		System.out.println("inside getByDateLoc");
		List<Scheduler> scheduls = this.scheduleService.getScheduleByMonthAndYearAndBaithak(month, year, baithakId);
		
		if(scheduls.size()==0) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "schedule not found ");
		}else {
			
			return new ResponseEntity<List<Scheduler>>(scheduls, HttpStatus.OK);
		}
	}

	@GetMapping("/filter/search")
    public ResponseEntity<PaginationResponse> searchScheduleBaseOnSearchField(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize) {
        
    
        return ResponseEntity.ok(this.scheduleService.searchSchedule(keyword,pageNumber, pageSize));
    }
}
