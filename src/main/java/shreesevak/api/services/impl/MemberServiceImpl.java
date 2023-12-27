package shreesevak.api.services.impl;

import java.util.Date;
import java.util.List;
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
import shreesevak.api.payloads.MemberDto;
import shreesevak.api.repository.BaithakRepo;
import shreesevak.api.repository.MemberRepo;
import shreesevak.api.services.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MemberRepo memberRepo;
	
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
	public MemberDto updateMember(MemberDto memberDto,Integer memberId) {
	Member member	=this.memberRepo.findById(memberId).orElseThrow(()-> new ResourceNotFoundException("Memeber", "id", memberId));
		member.setFirstName(memberDto.getFirstName());
		member.setAdd1(memberDto.getAdd1());
		member.setAdd2(memberDto.getAdd2());
		member.setAdd3(memberDto.getAdd3());
		member.setAdd4(memberDto.getAdd4());
		member.setFirstName(memberDto.getFirstName());
		member.setAddharNumber(memberDto.getAddharNumber());
		member.setAdditionalInfo(memberDto.getAdditionalInfo());
		member.setCity(memberDto.getCity());
		member.setCountry(memberDto.getCountry());
		member.setDivision(memberDto.getDivision());
		member.setDOB(memberDto.getDOB());
		member.setEducation(memberDto.getEducation());
		member.setEmail(memberDto.getEmail());
		member.setRole(memberDto.getRole());
		member.setGender(memberDto.getGender());
		member.setGoogleMapLink(memberDto.getGoogleMapLink());
		member.setHajeriNo(memberDto.getHajeriNo());
		member.setInitial(memberDto.getInitial());
		member.setEligibleForChild(memberDto.isEligibleForChild());
		member.setEligibleForGents(memberDto.isEligibleForGents());
		member.setEligibleForLadies(memberDto.isEligibleForLadies());
		member.setNone(memberDto.isNone());
		member.setMarathiRead(memberDto.isMarathiRead());
		member.setMarathiSpeak(memberDto.isMarathiSpeak());
		member.setMarathiWrite(memberDto.isMarathiWrite());
		member.setHindiRead(memberDto.isHindiRead());
		member.setHindiSpeak(memberDto.isHindiSpeak());
		member.setHindiWrite(memberDto.isHindiWrite());
		member.setEnglishRead(memberDto.isEnglishRead());
		member.setEnglishSpeak(memberDto.isEnglishSpeak());
		member.setEnglishWrite(memberDto.isEnglishWrite());
		member.setLastName(memberDto.getLastName());
		member.setMiddleName(memberDto.getMiddleName());
		member.setMobile(memberDto.getMobile());
		member.setOccupation(memberDto.getOccupation());
		member.setOwnBaithakDay(memberDto.getOwnBaithakDay());
		member.setPanNo(memberDto.getPanNo());
		member.setPhone(memberDto.getPhone());
		member.setPhotoBase64(memberDto.getPhotoBase64());
		member.setPincode(memberDto.getPincode());
		member.setState(memberDto.getState());
		member.setStatus(memberDto.getStatus());
		member.setArea(memberDto.getArea());
		member.setVehicleDetails(memberDto.getVehicleDetails());
		member.setVehicleType(memberDto.getVehicleType());
		int baithak=(memberDto.getMemberId());
		Baithak baithak2=this.baithakRepo.findByBithakId(baithak);
		member.setBaithak(baithak2);
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
	List<Member>allMembers	=this.memberRepo.findAll();
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
