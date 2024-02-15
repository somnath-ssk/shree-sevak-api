package shreesevak.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.helperclass.MembeFrontendBody;
import shreesevak.api.model.Area;
import shreesevak.api.model.City;
import shreesevak.api.model.Country;
import shreesevak.api.model.Division;
import shreesevak.api.model.Member;
import shreesevak.api.model.State;
import shreesevak.api.model.WeeklyOff;
import shreesevak.api.payloads.AreaDto;
import shreesevak.api.payloads.MemberDto;
import shreesevak.api.payloads.PaginationResponse;
import shreesevak.api.repository.AreaRepo;
import shreesevak.api.repository.BaithakRepo;
import shreesevak.api.repository.CityRepo;
import shreesevak.api.repository.CountryRepo;
import shreesevak.api.repository.DivisionRepo;
import shreesevak.api.repository.MemberRepo;
import shreesevak.api.repository.StateRepo;
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
	
	@Autowired
	private BaithakRepo baithakRepo;
	
	@Autowired
	private AreaRepo areaRepo;
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private CountryRepo countryRepo;
	@Autowired
	private DivisionRepo divisionRepo;

	
	//crate memeber
	@Override
	public MemberDto createMember(MembeFrontendBody memberFrontEndBody) {
		Optional<City> city	=this.cityRepo.findById(memberFrontEndBody.getCity());
		Optional<State> state	=this.stateRepo.findById(memberFrontEndBody.getState());
		Optional<Division> div	=this.divisionRepo.findById(memberFrontEndBody.getDivision());
		Optional<Country> country	=this.countryRepo.findById(memberFrontEndBody.getCountry());
		Member member1=new Member();
		member1.setBaithakLocation(memberFrontEndBody.getBaithakLocation());
		member1.setFirstName(memberFrontEndBody.getFirstName());
		member1.setAdd1(memberFrontEndBody.getAdd1());
		member1.setAdd2(memberFrontEndBody.getAdd2());
		member1.setAdd3(memberFrontEndBody.getAdd3());
		member1.setAdd4(memberFrontEndBody.getAdd4());
		member1.setFirstName(memberFrontEndBody.getFirstName());
		member1.setAddharNumber(memberFrontEndBody.getAddharNumber());
		member1.setAdditionalInfo(memberFrontEndBody.getAdditionalInfo());
		member1.setCity(city.get());
		member1.setCountry(country.get());
		member1.setDivision(div.get());
		member1.setDOB(memberFrontEndBody.getDOB());
		member1.setEducation(memberFrontEndBody.getEducation());
		member1.setEmail(memberFrontEndBody.getEmail());
		member1.setRole(memberFrontEndBody.getRole());
		member1.setGender(memberFrontEndBody.getGender());
		member1.setGoogleMapLink(memberFrontEndBody.getGoogleMapLink());
		member1.setHajeriNo(memberFrontEndBody.getHajeriNo());
		member1.setInitial(memberFrontEndBody.getInitial());
		member1.setEligibleForChild(memberFrontEndBody.isEligibleForChild());
		member1.setEligibleForGents(memberFrontEndBody.isEligibleForGents());
		member1.setEligibleForLadies(memberFrontEndBody.isEligibleForLadies());
		member1.setEligibleForNone(memberFrontEndBody.isEligibleForNone());
		member1.setMarathiRead(memberFrontEndBody.isMarathiRead());
		member1.setMarathiSpeak(memberFrontEndBody.isMarathiSpeak());
		member1.setMarathiWrite(memberFrontEndBody.isMarathiWrite());
		member1.setHindiRead(memberFrontEndBody.isHindiRead());
		member1.setHindiSpeak(memberFrontEndBody.isHindiSpeak());
		member1.setHindiWrite(memberFrontEndBody.isHindiWrite());
		member1.setEnglishRead(memberFrontEndBody.isEnglishRead());
		member1.setEnglishSpeak(memberFrontEndBody.isEnglishSpeak());
		member1.setEnglishWrite(memberFrontEndBody.isEnglishWrite());
		member1.setLastName(memberFrontEndBody.getLastName());
		member1.setMiddleName(memberFrontEndBody.getMiddleName());
		member1.setMobile(memberFrontEndBody.getMobile());
		member1.setOccupation(memberFrontEndBody.getOccupation());
		member1.setOwnBaithakDay(memberFrontEndBody.getOwnBaithakDay());
		member1.setPanNo(memberFrontEndBody.getPanNo());
		member1.setPhone(memberFrontEndBody.getPhone());
		member1.setPhotoBase64(memberFrontEndBody.getPhotoBase64());
		member1.setPincode(memberFrontEndBody.getPincode());
		member1.setState(state.get());
		member1.setStatus(memberFrontEndBody.getStatus());
		member1.setTwoWheelerDetail(memberFrontEndBody.getTwoWheelerDetail());
		member1.setFourWheelerDetail(memberFrontEndBody.getFourWheelerDetail());
		member1.setTwoWheeler(memberFrontEndBody.isTwoWheeler());
		member1.setFourWheeler(memberFrontEndBody.isFourWheeler());
member1.setNoVehical(memberFrontEndBody.isNoVehical());
Optional<Area> area =this.areaRepo.findById(memberFrontEndBody.getArea());


     member1.setArea(area.get());
  List<Integer>absentDays=memberFrontEndBody.getWeeklyOffs();
  List<Optional<WeeklyOff>> offWeeks=absentDays.stream().map(day->this.weekRepo.findById(day)).collect(Collectors.toList());
  List<WeeklyOff>newWeek=new ArrayList<>();
  for (Optional<WeeklyOff> weeklyOff : offWeeks) {
	  newWeek.add(weeklyOff.get());
	
}
  member1.setWeeklyOffs(newWeek);
//		int baithak=(memberDto.getMemberId());
//		Baithak baithak2=this.baithakRepo.findByBithakId(baithak);
//		member.setBaithak(baithak2);
  Member updatedMember= this.memberRepo.save(member1);
		

	

//	  if (memberRepo.findByEmail(member.getEmail() ) !=null) {
//            throw new ResourceAllReadyExist(member.getEmail());
//        }
//	  if (memberRepo.findByAddharNumber(member.getAddharNumber() ) !=null) {
//		  throw new ResourceAllReadyExist(member.getAddharNumber());
//	  }

//        if (memberRepo.findByPhone(member.getPhone())!= null) {
//        	 throw new ResourceAllReadyExist(member.getPhone());
//        }
	 
		return this.memberToDto(updatedMember);
	}

	//Update Memeber
	@Override
	public MemberDto updateMember(MembeFrontendBody memberFrontEndBody,Integer memberId) {
		Optional<City> city	=this.cityRepo.findById(memberFrontEndBody.getCity());
		Optional<State> state	=this.stateRepo.findById(memberFrontEndBody.getState());
		Optional<Division> div	=this.divisionRepo.findById(memberFrontEndBody.getDivision());
		Optional<Country> country	=this.countryRepo.findById(memberFrontEndBody.getCountry());
	Member member	=this.memberRepo.findById(memberId).orElseThrow(()-> new ResourceNotFoundException("Memeber", "id", memberId));
		member.setFirstName(memberFrontEndBody.getFirstName());
		member.setAdd1(memberFrontEndBody.getAdd1());
		member.setAdd2(memberFrontEndBody.getAdd2());
		member.setAdd3(memberFrontEndBody.getAdd3());
		member.setAdd4(memberFrontEndBody.getAdd4());
		member.setLongitude(memberFrontEndBody.getLongitude());
		member.setLatitude(memberFrontEndBody.getLatitude());
		member.setFirstName(memberFrontEndBody.getFirstName());
		member.setAddharNumber(memberFrontEndBody.getAddharNumber());
		member.setAdditionalInfo(memberFrontEndBody.getAdditionalInfo());
		member.setCity(city.get());
		member.setCountry(country.get());
		member.setDivision(div.get());
		member.setDOB(memberFrontEndBody.getDOB());
		member.setBaithakLocation(memberFrontEndBody.getBaithakLocation());
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
		member.setState(state.get());
		member.setStatus(memberFrontEndBody.getStatus());
		member.setTwoWheelerDetail(memberFrontEndBody.getTwoWheelerDetail());
		member.setFourWheelerDetail(memberFrontEndBody.getFourWheelerDetail());
		member.setTwoWheeler(memberFrontEndBody.isTwoWheeler());
		member.setFourWheeler(memberFrontEndBody.isFourWheeler());
member.setNoVehical(memberFrontEndBody.isNoVehical());
Optional<Area> area =this.areaRepo.findById(memberFrontEndBody.getArea());


     member.setArea(area.get());
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
	
//	get all memebrs base on area
//	@Override
//	public List<MemberDto> getAllAreaMember(String area) {
//		List<Member>allMembers	=this.memberRepo.findByArea(area);
//		List<MemberDto>members=allMembers.stream().map(mem-> this.memberToDto(mem)).collect(Collectors.toList());
//		return /members;
//	
//	}

	@Override
	public List<MemberDto> getAllActiveMemebers(String status) {
		List<Member>allMembers	=this.memberRepo.findAllByStatus(status);
		List<MemberDto>activemembers=allMembers.stream().map(mem-> this.memberToDto(mem)).collect(Collectors.toList());
		
		return activemembers;
	}

	
	@Override
	public PaginationResponse getAllActiveMemebers(Integer pageNumber, Integer pageSize, String status) {
		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "memberId"));
		Page<Member> page=this.memberRepo.findAllByStatus(status, p);
		List<Member> memberList=page.getContent();
		List<MemberDto>memberDtoList=memberList.stream().map(member->this.memberToDto2(member)).collect(Collectors.toList());
		PaginationResponse paginationResponse=new PaginationResponse();
		paginationResponse.setContent(memberDtoList);
		paginationResponse.setLastPage(page.isLast());
		paginationResponse.setPageNumber(page.getNumber());
		paginationResponse.setPageSize(page.getSize());
		paginationResponse.setTotalPages(page.getTotalPages());
		paginationResponse.setTotoalElement(page.getTotalElements());
		return paginationResponse;
	
	}

	
//This method return the data base on input parameter like pageNumber and pageSize for pagination
	@Override
	public PaginationResponse getAllMember(Integer pageNumber, Integer pageSize) {
		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "memberId"));
		Page<Member> page=this.memberRepo.findAll(p);
		List<Member> memberList=page.getContent();
		List<MemberDto>memberDtoList=memberList.stream().map(member->this.memberToDto2(member)).collect(Collectors.toList());
		PaginationResponse paginationResponse=new PaginationResponse();
		paginationResponse.setContent(memberDtoList);
		paginationResponse.setLastPage(page.isLast());
		paginationResponse.setPageNumber(page.getNumber());
		paginationResponse.setPageSize(page.getSize());
		paginationResponse.setTotalPages(page.getTotalPages());
		paginationResponse.setTotoalElement(page.getTotalElements());
		return paginationResponse;
	}
	
	
	//convert Dto to member
	public Member dtoToMember(MemberDto memberDto) {
	Member member=this.modelMapper.map(memberDto,Member.class);
	
	return member;
	}
	
	public MemberDto memberToDto(Member member) {
		MemberDto memberDto1=this.modelMapper.map(member,MemberDto.class);
		
		return memberDto1;
	}
	public MemberDto memberToDto2(Member member) {
		MemberDto meberDto=new MemberDto();
		
		meberDto.setFirstName(member.getFirstName());
		meberDto.setMemberId(member.getMemberId());
		meberDto.setAdd1(member.getAdd1());
		meberDto.setAdd2(member.getAdd2());
		meberDto.setAdd3(member.getAdd3());
		meberDto.setAdd4(member.getAdd4());
		meberDto.setLongitude(member.getLongitude());
		meberDto.setLatitude(member.getLatitude());
		meberDto.setFirstName(member.getFirstName());
		meberDto.setAddharNumber(member.getAddharNumber());
		meberDto.setAdditionalInfo(member.getAdditionalInfo());
	
		meberDto.setDOB(member.getDOB());
		meberDto.setBaithakLocation(member.getBaithakLocation());
		meberDto.setEducation(member.getEducation());
		meberDto.setEmail(member.getEmail());
		meberDto.setRole(member.getRole());
		meberDto.setGender(member.getGender());
		meberDto.setGoogleMapLink(member.getGoogleMapLink());
		meberDto.setHajeriNo(member.getHajeriNo());
		meberDto.setInitial(member.getInitial());
		meberDto.setEligibleForChild(member.isEligibleForChild());
		meberDto.setEligibleForGents(member.isEligibleForGents());
		meberDto.setEligibleForLadies(member.isEligibleForLadies());
		meberDto.setEligibleForNone(member.isEligibleForNone());
		meberDto.setMarathiRead(member.isMarathiRead());
		meberDto.setMarathiSpeak(member.isMarathiSpeak());
		meberDto.setMarathiWrite(member.isMarathiWrite());
		meberDto.setHindiRead(member.isHindiRead());
		meberDto.setHindiSpeak(member.isHindiSpeak());
		meberDto.setHindiWrite(member.isHindiWrite());
		meberDto.setEnglishRead(member.isEnglishRead());
		meberDto.setEnglishSpeak(member.isEnglishSpeak());
		meberDto.setEnglishWrite(member.isEnglishWrite());
		meberDto.setLastName(member.getLastName());
		meberDto.setMiddleName(member.getMiddleName());
		meberDto.setMobile(member.getMobile());
		meberDto.setOccupation(member.getOccupation());
		meberDto.setOwnBaithakDay(member.getOwnBaithakDay());
		meberDto.setPanNo(member.getPanNo());
		meberDto.setPhone(member.getPhone());
		meberDto.setPhotoBase64(member.getPhotoBase64());
		meberDto.setPincode(member.getPincode());
		meberDto.setStatus(member.getStatus());
		meberDto.setTwoWheelerDetail(member.getTwoWheelerDetail());
		meberDto.setFourWheelerDetail(member.getFourWheelerDetail());
		meberDto.setTwoWheeler(member.isTwoWheeler());
		meberDto.setFourWheeler(member.isFourWheeler());
meberDto.setNoVehical(member.isNoVehical());

meberDto.setArea(this.areaToDto(member.getArea()));
//Optional<Area> area =this.areaRepo.findById(member.getArea());

//		MemberDto memberDto1=this.modelMapper.map(member,MemberDto.class);
//		
		return meberDto;
	}

	@Override
	public PaginationResponse SearchMemebrs(String keyword, String status, int pageNumber, int pageSize) {
        if(status.equals("null")) {
        	Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "memberId"));
    		Page<Member> page=this.memberRepo.searchMember(keyword, p);
    		List<Member> memberList=page.getContent();
    		List<MemberDto>memberDtoList=memberList.stream().map(member->this.memberToDto2(member)).collect(Collectors.toList());
    		PaginationResponse paginationResponse=new PaginationResponse();
    		paginationResponse.setContent(memberDtoList);
    		paginationResponse.setLastPage(page.isLast());
    		paginationResponse.setPageNumber(page.getNumber());
    		paginationResponse.setPageSize(page.getSize());
    		paginationResponse.setTotalPages(page.getTotalPages());
    		paginationResponse.setTotoalElement(page.getTotalElements());
    		return paginationResponse;
        }
        else {
        	Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "memberId"));
    		Page<Member> page=this.memberRepo.searchMember(keyword,status, p);
    		List<Member> memberList=page.getContent();
    		List<MemberDto>memberDtoList=memberList.stream().map(member->this.memberToDto2(member)).collect(Collectors.toList());
    		PaginationResponse paginationResponse=new PaginationResponse();
    		paginationResponse.setContent(memberDtoList);
    		paginationResponse.setLastPage(page.isLast());
    		paginationResponse.setPageNumber(page.getNumber());
    		paginationResponse.setPageSize(page.getSize());
    		paginationResponse.setTotalPages(page.getTotalPages());
    		paginationResponse.setTotoalElement(page.getTotalElements());
    		return paginationResponse;

        }
	}


	public AreaDto areaToDto(Area area) {

		AreaDto areaDto = this.modelMapper.map(area, AreaDto.class);
		return areaDto;

	}



	

	

	

	
	
	

}
