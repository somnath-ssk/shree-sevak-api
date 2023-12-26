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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.PostUpdate;
import shreesevak.api.model.Area;
import shreesevak.api.payloads.AreaDto;
import shreesevak.api.services.AreaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/area")
public class AreaController {

	@Autowired
	private AreaService areaService;
	
	
	@PostMapping("/create")
	private ResponseEntity<AreaDto> createArea(@RequestBody Area area){
		AreaDto createdAreaDto=this.areaService.createArea(area);
		return new ResponseEntity<AreaDto>(createdAreaDto,HttpStatus.CREATED);
	}
	@PutMapping("/update-area/{areaId}")
	private ResponseEntity<AreaDto> updateArea(@RequestBody AreaDto area,@PathVariable Integer areaId){
		AreaDto createdAreaDto=this.areaService.updateArea(area,areaId);
		return new ResponseEntity<AreaDto>(createdAreaDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{areaId}")
	private ResponseEntity<AreaDto> getSingleArea(@PathVariable Integer areaId) {
		AreaDto area=this.areaService.getSingleAreas(areaId);
		return new ResponseEntity<AreaDto>(area,HttpStatus.OK);
		
	}
	//get by nme
	@GetMapping("/areaName")
	private ResponseEntity<AreaDto> getSingleAreaByName(@RequestParam("areaName") String areaName) {
		AreaDto area=this.areaService.getSingleAreaByNames(areaName);
		return new ResponseEntity<AreaDto>(area,HttpStatus.OK);
		
	}
	@GetMapping("/all-areas")
	private ResponseEntity<List<AreaDto>> getAllAreas() {
		List<AreaDto> areas=this.areaService.getallArea();
		return new ResponseEntity<List<AreaDto>>(areas,HttpStatus.OK);
		
	}
	
	@GetMapping("/statusType/{status}")
	private ResponseEntity<List<AreaDto>> getAllAreasByStatus(@PathVariable String status) {
		List<AreaDto> areas=this.areaService.getAllAreasByStatus(status);
		return new ResponseEntity<List<AreaDto>>(areas,HttpStatus.OK);
		
	}
	
}
