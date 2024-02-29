package shreesevak.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shreesevak.api.model.WeeklyOff;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.services.WeekService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/week")
public class WeekController {
	
	@Autowired
	private WeekService weekService;
	
	@GetMapping("/all")
	public ResponseEntity<List<WeeklyOff>> getAllWeek() {
		List<WeeklyOff> daysinweek = this.weekService.getAllDays();
		return new ResponseEntity<List<WeeklyOff>>(daysinweek,HttpStatus.OK);
	}
	

}
