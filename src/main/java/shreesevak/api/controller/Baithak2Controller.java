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

import shreesevak.api.helperclass.BaithakFrontEnd;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Baithak2;
import shreesevak.api.payloads.Baithak2Dto;
import shreesevak.api.payloads.BaithakDto;
import shreesevak.api.services.Baithak2service;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/baithak2")
public class Baithak2Controller {

	@Autowired
	Baithak2service baithak2service;

	@PostMapping("/createbaithak2/gents_ladies")
	public ResponseEntity<Baithak2Dto> createBaithaksforGentsAndLadies(@RequestBody BaithakFrontEnd baithak) {

		Baithak2Dto saveBaithak = this.baithak2service.createBaithakForGentsAndLadies(baithak);
		return new ResponseEntity<Baithak2Dto>(saveBaithak, HttpStatus.CREATED);
	}

	@PutMapping("/update-baithak2/{baithakId}")
	public ResponseEntity<Baithak2Dto> updateBaithak(@RequestBody BaithakFrontEnd baithak,
			@PathVariable Integer baithakId) {

		Baithak2Dto updatedBaithak = this.baithak2service.updateBaithak2(baithak, baithakId);
		return new ResponseEntity<Baithak2Dto>(updatedBaithak, HttpStatus.OK);

	}

	@GetMapping("/getbaithakByLocTypeDayTime/{locationId}/{baithakType}/{day}/{fromTime}")
	public ResponseEntity<Baithak2Dto> getBaithak2ByLocTypeDayTime(@PathVariable Integer locationId,
			@PathVariable String baithakType, @PathVariable String day, @PathVariable String fromTime) {
		Baithak2Dto baithak2Dtos = this.baithak2service.getbaithakByLocTypeDayTime(locationId, baithakType, day, fromTime);

		return new ResponseEntity<Baithak2Dto>(baithak2Dtos, HttpStatus.OK);
	}

//	@GetMapping("/{baithakId}")
//	public ResponseEntity<Baithak2>getBaithakDetail(@PathVariable Integer baithakId){
//		Baithak baithak  = this.baithak2service.getSingleBiathakDetails(baithakId);
//		return new ResponseEntity<Baithak>(baithak,HttpStatus.OK);
//	}

//	@GetMapping("/all-baithak")
//	public ResponseEntity<List<BaithakDto>>getAllBaithakDetails(){
//	List<BaithakDto>baithak=this.baithakService.getAllBaithak();
//	return new  ResponseEntity<List<BaithakDto>>(baithak,HttpStatus.OK);
//	}

//	@GetMapping("/status/{statustype}")
//	public ResponseEntity<List<BaithakDto>>getAllByStatus(@PathVariable String statustype){
//	List<BaithakDto>activeInactive	=this.baithakService.getAllActiveStatus(statustype);
//	return ResponseEntity.ok(activeInactive);
//	
//	}
}
