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

@CrossOrigin("*")
@RestController
@RequestMapping("/api/baithak")
public class BaithakController {
	
	@Autowired
	private BaithakService baithakService;
//	
	@PostMapping("/createbaithak")
	public ResponseEntity<Baithak> createBaithaks(@RequestBody Baithak baithak){
		
	 Baithak saveBaithak =this.baithakService.createBaithak(baithak);
	 return new ResponseEntity<Baithak>(saveBaithak,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-baithak/{baithakId}")
	public ResponseEntity<Baithak>updateBaithak(@RequestBody BaithakDto baithakDto,@PathVariable Integer baithakId ){
		
	Baithak updatedBaithak=	this.baithakService.updateBaithak(baithakDto, baithakId);
	   return new ResponseEntity<Baithak>(updatedBaithak,HttpStatus.OK);
		
	}
	
	@GetMapping("/{baithakId}")
	public ResponseEntity<Baithak>getBaithakDetail(@PathVariable Integer baithakId){
		Baithak baithak  = this.baithakService.getSingleBiathakDetails(baithakId);
		return new ResponseEntity<Baithak>(baithak,HttpStatus.OK);
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
//	@PostMapping("/createbaithak/")
//	public ResponseEntity<Baithak> createBaithaks(@RequestBody BaithakDto baithakDto){
//		
//		Baithak saveBaithak =this.baithakService.createBaithak2(baithakDto);
//	 return new ResponseEntity<Baithak>(saveBaithak,HttpStatus.CREATED);
//	}
//	
}
