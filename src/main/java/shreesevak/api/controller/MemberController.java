package shreesevak.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shreesevak.api.helperclass.MembeFrontendBody;
import shreesevak.api.payloads.ApiResponse;
import shreesevak.api.payloads.MemberDto;
import shreesevak.api.payloads.PaginationResponse;
import shreesevak.api.services.MemberService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	//create member
	@PostMapping("/createmember")
	public ResponseEntity<MemberDto> createMember(@RequestBody MembeFrontendBody membeFrontendBody ){
	  MemberDto saveMember	=this.memberService.createMember(membeFrontendBody);
		return new ResponseEntity<MemberDto>(saveMember,HttpStatus.CREATED);
	}
	
	
	//update member
	@PutMapping("/update-member/{memberId}")
	public ResponseEntity<MemberDto> updateMember(@RequestBody MembeFrontendBody memberBody,@PathVariable Integer memberId ){
		MemberDto saveMember	=this.memberService.updateMember(memberBody,memberId);
		return new ResponseEntity<MemberDto>(saveMember,HttpStatus.OK);
	}
	
	//delete member
	@DeleteMapping("/delete-member/{memberId}")
	public ResponseEntity<ApiResponse> deleteMember(@PathVariable Integer memberId ){
		this.memberService.deleteMember(memberId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("member deleted succesfully ",true),HttpStatus.OK);
	}
	
	//select single member
	
	@GetMapping("/{memberId}")
	public ResponseEntity<MemberDto> selectSingleMember(@PathVariable Integer memberId ){
	MemberDto memberDto	=this.memberService.getSingleMember(memberId);
		return ResponseEntity.ok(memberDto);
	}

	
	
	//get all user
	@GetMapping("/all-members")
	public ResponseEntity<List<MemberDto>> selectAllMembers() {
		List<MemberDto>members=this.memberService.getAllMember();
		return new ResponseEntity<List<MemberDto>>(members, HttpStatus.OK) ;
		
	}
	@GetMapping("/all-members/pagination")
	public ResponseEntity<PaginationResponse> selectAllMemberspagination(@RequestParam(value ="pageNumber",defaultValue = "0",required = false) Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "10", required = false)Integer pageSize) {
		PaginationResponse pagination=this.memberService.getAllMember(pageNumber,pageSize);
		return new ResponseEntity<PaginationResponse>(pagination, HttpStatus.OK) ;
		
	}
	
	
	//get memebr base on area
//	@GetMapping("/areaMembers/{area}")
//	public ResponseEntity<List<MemberDto>> selectAllAreaMemebrs(@PathVariable String area) {
//		List<MemberDto>areaMembers=this.memberService.getAllAreaMember(area);
//		return new ResponseEntity<List<MemberDto>>(areaMembers, HttpStatus.OK) ;
//		
//	}
	
	
	//get all active member
	
	@GetMapping("/status/{status}")
      public ResponseEntity<List<MemberDto>> selectAllactiveMembers(@PathVariable String status) {
		List<MemberDto>activemembers=this.memberService.getAllActiveMemebers(status);
		return new ResponseEntity<List<MemberDto>>(activemembers, HttpStatus.OK) ;
		
	}
	@GetMapping("/status/pagination/{status}")
	public ResponseEntity<PaginationResponse> selectAllactiveMembers(@RequestParam(value ="pageNumber",defaultValue = "0",required = false) Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "10", required = false)Integer pageSize,@PathVariable String status ) {
		PaginationResponse response =this.memberService.getAllActiveMemebers(pageNumber, pageSize, status);
		return new ResponseEntity<PaginationResponse>(response, HttpStatus.OK) ;
		 
	}
	@GetMapping("/pagination/search")
    public ResponseEntity<PaginationResponse> searchMemebersBaseOnSearchField(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize) {
        
    
        return ResponseEntity.ok(this.memberService.SearchMemebrs(keyword,status, pageNumber, pageSize));
    }

}
