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

import jakarta.persistence.PostUpdate;
import shreesevak.api.model.Baithak;
import shreesevak.api.payloads.BaithakDto;
import shreesevak.api.services.BaithakService;

@CrossOrigin
@RestController
@RequestMapping("/api/baithak")
public class BaithakController {
	
	@Autowired
	private BaithakService baithakService;
	
	@PostMapping("/createbaithak")
	public ResponseEntity<BaithakDto> createBaithaks(@RequestBody BaithakDto baithakDto){
		
	 BaithakDto saveBaithak =this.baithakService.createBaithak(baithakDto);
	 return new ResponseEntity<BaithakDto>(saveBaithak,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-baithak/{baithakId}")
	public ResponseEntity<BaithakDto> updateBaithak(@RequestBody BaithakDto baithakDto,@PathVariable Integer baithakId ){
		
	BaithakDto updatedBaithak=	this.baithakService.updateBaithak(baithakDto, baithakId);
	   return new ResponseEntity<BaithakDto>(updatedBaithak,HttpStatus.OK);
		
	}
	
	@GetMapping("/{baithakId}")
	public ResponseEntity<BaithakDto>getBaithakDetail(@PathVariable Integer baithakId){
		BaithakDto baithakDto  = this.baithakService.getSingleBiathakDetails(baithakId);
		return new ResponseEntity<BaithakDto>(baithakDto,HttpStatus.OK);
	}
	
	@GetMapping("/all-baithak")
	public ResponseEntity<List<BaithakDto>>getAllBaithakDetails(){
	List<BaithakDto>baithak=this.baithakService.getAllBaithak();
	return new  ResponseEntity<List<BaithakDto>>(baithak,HttpStatus.OK);
	}
	
	@GetMapping("/status/{statustype}")
	public ResponseEntity<List<BaithakDto>>getAllByStatus(@PathVariable String statustype){
	List<BaithakDto>activeInactive	=this.baithakService.getAllActiveStatus(statustype);
	return ResponseEntity.ok(activeInactive);
	
	}
// stroring the all baithak location and memeberDetails
	@PostMapping("/createbaithak/{locationId}/{memberId}")
	public ResponseEntity<Baithak> createBaithaks(@RequestBody BaithakDto baithakDto,@PathVariable Integer locationId,@PathVariable List<Integer> memberId){
		System.out.println(baithakDto);
		System.out.println(locationId);
		System.out.println(memberId);
		Baithak saveBaithak =this.baithakService.createBaithak2(baithakDto,locationId,memberId);
	 return new ResponseEntity<Baithak>(saveBaithak,HttpStatus.CREATED);
	}
	
}
