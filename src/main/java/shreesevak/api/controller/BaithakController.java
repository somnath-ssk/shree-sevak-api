package shreesevak.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shreesevak.api.payloads.BaithakDto;
import shreesevak.api.services.BaithakService;

@RestController
@RequestMapping("/api/baithak")
public class BaithakController {
	
	@Autowired
	private BaithakService baithakService;
	
	@PostMapping("/createbaithak")
	public ResponseEntity<BaithakDto> createBaithaks(@RequestBody BaithakDto baithakDto){
		System.out.println(baithakDto.toString());
		System.out.println("conreoler");
		System.out.println("create baithak");
	 BaithakDto saveBaithak =this.baithakService.createBaithak(baithakDto);
	 return new ResponseEntity<BaithakDto>(saveBaithak,HttpStatus.CREATED);
	}
}
