package shreesevak.api.controller;

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
   public ResponseEntity<SchedularDto>createSchedules(@RequestBody SchedularFrontendDto schedularFrontendDto){
	   SchedularDto schedularDto = this.scheduleService.createSchedule(schedularFrontendDto);
	   return new ResponseEntity<SchedularDto>(schedularDto,HttpStatus.CREATED);
   }

   @GetMapping("/{scheduleId}")
   public ResponseEntity<SchedularDto>getScheduleById(@PathVariable Integer scheduleId){
	   
	 SchedularDto schedularDto = this.scheduleService.getSingleSchedule(scheduleId);
	 return new ResponseEntity<SchedularDto>(schedularDto,HttpStatus.OK);
   }
   @GetMapping("/all-schedules")
   public ResponseEntity<List<SchedularDto>>scheduleList(){
	   
	   List<SchedularDto> schedularDtos = this.scheduleService.getAllSchedule();
	   return new ResponseEntity<List<SchedularDto>>(schedularDtos,HttpStatus.OK);
   }
   
   @PutMapping("/{scheduleId}")
   public ResponseEntity<SchedularDto> updateSchedule(@RequestBody SchedularFrontendDto schedularFrontendDto,@PathVariable Integer scheduleId) {
	   
	   SchedularDto schedularDto   = this.scheduleService.updateSchedule(schedularFrontendDto,scheduleId);
	  return new ResponseEntity<SchedularDto>(schedularDto,HttpStatus.OK);
   }
   
   
   } 

