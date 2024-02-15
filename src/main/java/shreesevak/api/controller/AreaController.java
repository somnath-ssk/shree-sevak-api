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
import shreesevak.api.helperclass.AreaFrontEnd;
import shreesevak.api.model.Area;
import shreesevak.api.payloads.AreaDto;
import shreesevak.api.payloads.PaginationResponse;
import shreesevak.api.services.AreaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/area")
public class AreaController {

	@Autowired
	private AreaService areaService;
	
	
	@PostMapping("/create")
	private ResponseEntity<AreaDto> createArea(@RequestBody AreaFrontEnd area){
		AreaDto createdAreaDto=this.areaService.createArea(area);
		return new ResponseEntity<AreaDto>(createdAreaDto,HttpStatus.CREATED);
	}
	@PutMapping("/update-area/{areaId}")
	private ResponseEntity<AreaDto> updateArea(@RequestBody AreaFrontEnd area,@PathVariable Integer areaId){
		AreaDto createdAreaDto=this.areaService.updateArea(area,areaId);
		return new ResponseEntity<AreaDto>(createdAreaDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{areaId}")
	private ResponseEntity<AreaDto> getSingleArea(@PathVariable Integer areaId) {
		AreaDto area=this.areaService.getSingleAreas(areaId);
		return new ResponseEntity<AreaDto>(area,HttpStatus.OK);
		
	}
	//get by nme
//	@GetMapping("/areaName")
//	private ResponseEntity<AreaDto> getSingleAreaByName(@RequestParam("areaName") String areaName) {
//		AreaDto area=this.areaService.getSingleAreaByNames(areaName);
//		return new ResponseEntity<AreaDto>(area,HttpStatus.OK);
//		
//	}
	
	@GetMapping("/unselected-Areas")
	private ResponseEntity<List<AreaDto>> getAllUnselectedAreas() {
		List<AreaDto> areas=this.areaService. getAllUnselectedAreas();
		return new ResponseEntity<List<AreaDto>>(areas,HttpStatus.OK);
		
	}
	@GetMapping("/unselected-AreasforUser/{userId}")
	private ResponseEntity<List<AreaDto>> getAllUnselectedAreaExceptUserAreas(@PathVariable Integer userId) {
		List<AreaDto> areas=this.areaService. getUnselectedAndSingleUserAreas(userId);
		return new ResponseEntity<List<AreaDto>>(areas,HttpStatus.OK);
		
	}
	
	@GetMapping("/statusType/{status}")
	private ResponseEntity<List<AreaDto>> getAllAreasByStatus(@PathVariable String status) {
		List<AreaDto> areas=this.areaService.getAllAreasByStatus(status);
		return new ResponseEntity<List<AreaDto>>(areas,HttpStatus.OK);
		
	}
	@GetMapping("/all-areas")
	private ResponseEntity<List<AreaDto>> getAllAreas() {
		List<AreaDto> areas=this.areaService.getallArea();
		return new ResponseEntity<List<AreaDto>>(areas,HttpStatus.OK);
		
	}
	@GetMapping("/statusType/pagination/{status}")
	private ResponseEntity<PaginationResponse> getAllAreasByStatus(@RequestParam(value ="pageNumber",defaultValue = "0",required = false) Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "10", required = false)Integer pageSize,@PathVariable Integer status) {
		PaginationResponse areas=this.areaService.getAllAreasByStatus( pageNumber, pageSize,status);
		return new ResponseEntity<PaginationResponse>(areas,HttpStatus.OK);
		
	}
	@GetMapping("/pagination/all-areas")
	private ResponseEntity<PaginationResponse> getAllAreas(@RequestParam(value ="pageNumber",defaultValue = "0",required = false) Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "10", required = false)Integer pageSize) {
		PaginationResponse areas=this.areaService.getallArea(pageNumber, pageSize);
		return new ResponseEntity<PaginationResponse>(areas,HttpStatus.OK);
		
	}
	@GetMapping("/filter/search")
    public ResponseEntity<PaginationResponse> searchAreaBaseOnSearchField(
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize) {
        
    
        return ResponseEntity.ok(this.areaService.searchArea(keyword,status, pageNumber, pageSize));
    }

	
}
