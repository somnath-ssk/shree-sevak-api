package shreesevak.api.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceAllReadyExist;
import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Member;
import shreesevak.api.model.Role;
import shreesevak.api.model.WeeklyOff;
import shreesevak.api.payloads.MembeFrontendBody;
import shreesevak.api.payloads.MemberDto;
import shreesevak.api.repository.BaithakRepo;
import shreesevak.api.repository.MemberRepo;
import shreesevak.api.repository.WeekRepo;
import shreesevak.api.services.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private WeekRepo weekRepo;
	
	private BaithakRepo baithakRepo;
	//crate memeber
	@Override
	public MemberDto createMember(MemberDto memberDto) {
	Member member	=this.dtoToMember(memberDto);
	

//	  if (memberRepo.findByEmail(member.getEmail() ) !=null) {
//            throw new ResourceAllReadyExist(member.getEmail());
//        }
//	  if (memberRepo.findByAddharNumber(member.getAddharNumber() ) !=null) {
//		  throw new ResourceAllReadyExist(member.getAddharNumber());
//	  }

//        if (memberRepo.findByPhone(member.getPhone())!= null) {
//        	 throw new ResourceAllReadyExist(member.getPhone());
//        }
	   Member updatedMember= this.memberRepo.save(member);
		return this.memberToDto(updatedMember);
	}

	//Update Memeber
	@Override
	public MemberDto updateMember(MembeFrontendBody memberFrontEndBody,Integer memberId) {
	Member member	=this.memberRepo.findById(memberId).orElseThrow(()-> new ResourceNotFoundException("Memeber", "id", memberId));
		member.setFirstName(memberFrontEndBody.getFirstName());
		member.setAdd1(memberFrontEndBody.getAdd1());
		member.setAdd2(memberFrontEndBody.getAdd2());
		member.setAdd3(memberFrontEndBody.getAdd3());
		member.setAdd4(memberFrontEndBody.getAdd4());
		member.setFirstName(memberFrontEndBody.getFirstName());
		member.setAddharNumber(memberFrontEndBody.getAddharNumber());
		member.setAdditionalInfo(memberFrontEndBody.getAdditionalInfo());
		member.setCity(memberFrontEndBody.getCity());
		member.setCountry(memberFrontEndBody.getCountry());
		member.setDivision(memberFrontEndBody.getDivision());
		member.setDOB(memberFrontEndBody.getDOB());
		member.setEducation(memberFrontEndBody.getEducation());
		member.setEmail(memberFrontEndBody.getEmail());
		member.setRole(memberFrontEndBody.getRole());
		member.setGender(memberFrontEndBody.getGender());
		member.setGoogleMapLink(memberFrontEndBody.getGoogleMapLink());
		member.setHajeriNo(memberFrontEndBody.getHajeriNo());
		member.setInitial(memberFrontEndBody.getInitial());
		member.setEligibleForChild(memberFrontEndBody.isEligibleForChild());
		member.setEligibleForGents(memberFrontEndBody.isEligibleForGents());
		member.setEligibleForLadies(memberFrontEndBody.isEligibleForLadies());
		member.setEligibleForNone(memberFrontEndBody.isEligibleForNone());
		member.setMarathiRead(memberFrontEndBody.isMarathiRead());
		member.setMarathiSpeak(memberFrontEndBody.isMarathiSpeak());
		member.setMarathiWrite(memberFrontEndBody.isMarathiWrite());
		member.setHindiRead(memberFrontEndBody.isHindiRead());
		member.setHindiSpeak(memberFrontEndBody.isHindiSpeak());
		member.setHindiWrite(memberFrontEndBody.isHindiWrite());
		member.setEnglishRead(memberFrontEndBody.isEnglishRead());
		member.setEnglishSpeak(memberFrontEndBody.isEnglishSpeak());
		member.setEnglishWrite(memberFrontEndBody.isEnglishWrite());
		member.setLastName(memberFrontEndBody.getLastName());
		member.setMiddleName(memberFrontEndBody.getMiddleName());
		member.setMobile(memberFrontEndBody.getMobile());
		member.setOccupation(memberFrontEndBody.getOccupation());
		member.setOwnBaithakDay(memberFrontEndBody.getOwnBaithakDay());
		member.setPanNo(memberFrontEndBody.getPanNo());
		member.setPhone(memberFrontEndBody.getPhone());
		member.setPhotoBase64(memberFrontEndBody.getPhotoBase64());
		member.setPincode(memberFrontEndBody.getPincode());
		member.setState(memberFrontEndBody.getState());
		member.setStatus(memberFrontEndBody.getStatus());
		member.setArea(memberFrontEndBody.getArea());
		member.setTwoWheelerDetail(memberFrontEndBody.getTwoWheelerDetail());
		member.setFourWheelerDetail(memberFrontEndBody.getFourWheelerDetail());
		member.setTwoWheeler(memberFrontEndBody.isTwoWheeler());
		member.setFourWheeler(memberFrontEndBody.isFourWheeler());
member.setNoVehical(memberFrontEndBody.isNoVehical());
  List<Integer>absentDays=memberFrontEndBody.getWeeklyOffs();
  List<Optional<WeeklyOff>> offWeeks=absentDays.stream().map(day->this.weekRepo.findById(day)).collect(Collectors.toList());
  List<WeeklyOff>newWeek=new ArrayList<>();
  for (Optional<WeeklyOff> weeklyOff : offWeeks) {
	  newWeek.add(weeklyOff.get());
	
}
member.setWeeklyOffs(newWeek);
//		int baithak=(memberDto.getMemberId());
//		Baithak baithak2=this.baithakRepo.findByBithakId(baithak);
//		member.setBaithak(baithak2);
		Member memberUpdated=this.memberRepo.save(member);
		
	return this.memberToDto(memberUpdated) ;
	}

	
	@Override
	public void deleteMember(Integer memberId) {
		Member member=this.memberRepo.findById(memberId).orElseThrow(()->new ResourceNotFoundException("Member", "ID", memberId));
		memberRepo.delete(member);
	
	}
	
	@Override
	public MemberDto getSingleMember(Integer memberId) {
	Member member	=this.memberRepo.findById(memberId).orElseThrow(()-> new ResourceNotFoundException("Member","Id", memberId));
	
		return 	this.memberToDto(member);
	}

	@Override
	public List<MemberDto> getAllMember() {
	List<Member>allMembers	=this.memberRepo.findAllorderByDesc();
	List<MemberDto>members=allMembers.stream().map(mem-> this.memberToDto(mem)).collect(Collectors.toList());
		return members;
	}
	
	//get all memebrs base on area
	@Override
	public List<MemberDto> getAllAreaMember(String area) {
		List<Member>allMembers	=this.memberRepo.findByArea(area);
		List<MemberDto>members=allMembers.stream().map(mem-> this.memberToDto(mem)).collect(Collectors.toList());
		return members;
	
	}

	@Override
	public List<MemberDto> getAllActiveMemebers(String status) {
		List<Member>allMembers	=this.memberRepo.findAllByStatus(status);
		List<MemberDto>activemembers=allMembers.stream().map(mem-> this.memberToDto(mem)).collect(Collectors.toList());
		
		return activemembers;
	}
	
	public Member dtoToMember(MemberDto memberDto) {
	Member member=this.modelMapper.map(memberDto,Member.class);
	
	return member;
	}
	
	public MemberDto memberToDto(Member member) {
		MemberDto memberDto1=this.modelMapper.map(member,MemberDto.class);
		
		return memberDto1;
	}

	

	

	
	
	

}
