package shreesevak.api.services;

import java.util.List;

import shreesevak.api.payloads.MemberDto;

public interface MemberService {

	MemberDto createMember(MemberDto memberData);
	MemberDto updateMember(MemberDto memberData,Integer memberId);
	void deleteMember(Integer memberId);
	MemberDto getSingleMember(Integer memberId);
	List<MemberDto> getAllMember();
	List<MemberDto> getAllAreaMember(String area);
	
	List<MemberDto> getAllActiveMemebers(String status);

	
	
}
