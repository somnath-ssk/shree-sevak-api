package shreesevak.api.services;

import java.util.List;

import shreesevak.api.helperclass.MembeFrontendBody;
import shreesevak.api.payloads.MemberDto;
import shreesevak.api.payloads.PaginationResponse;

public interface MemberService {

	MemberDto createMember(MembeFrontendBody memberData);
	MemberDto updateMember(MembeFrontendBody memberBody,Integer memberId);
	void deleteMember(Integer memberId);
	MemberDto getSingleMember(Integer memberId);
	List<MemberDto> getAllMember();
//	List<MemberDto> getAllAreaMember(String area);
	
	List<MemberDto> getAllActiveMemebers(String status);
	PaginationResponse getAllActiveMemebers(Integer pageNumber, Integer pageSize,String status);
	PaginationResponse getAllMember(Integer pageNumber, Integer pageSize);
	PaginationResponse SearchMemebrs(String keyword, String status, int pageNumber, int pageSize);
    
	
	
}
