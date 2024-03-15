package shreesevak.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.helperclass.PeopleScheduleFrontEnd;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Baithak2;
import shreesevak.api.model.Location;
import shreesevak.api.model.Member;
import shreesevak.api.model.PeopleSchedule;
import shreesevak.api.payloads.PeopleScheduleDto;
import shreesevak.api.payloads.SchedularDto;
import shreesevak.api.repository.Baithak2Repo;
import shreesevak.api.repository.BaithakRepo;
import shreesevak.api.repository.LocationRepo;
import shreesevak.api.repository.MemberRepo;
import shreesevak.api.repository.PeopleScheduleRepo;
import shreesevak.api.repository.ScheduleRepo;
import shreesevak.api.services.PeopleScheduleService;


@Service
public class PeopleScheduleServiceImpl implements PeopleScheduleService  {


	@Autowired
	private Baithak2Repo baithak2Repo;

	@Autowired
	private MemberRepo memberRepo;

	@Autowired
	private PeopleScheduleRepo peopleScheduleRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PeopleScheduleDto createSchedule(PeopleScheduleFrontEnd schedule) {
	  PeopleSchedule peopleSchedule=new PeopleSchedule();
		Baithak2 baithak = baithak2Repo.findByBaithakId(schedule.getBaithak());
		peopleSchedule.setBaithak(baithak);
		Member member=this.memberRepo.findByMemberId(schedule.getMember());
		peopleSchedule.setMember(member);
		peopleSchedule.setDate(schedule.getDate());
		
		return this.ScheduleToDto(this.peopleScheduleRepo.save(peopleSchedule));
	}

	@Override
	public PeopleScheduleDto getSingleSchedule(Integer scheduleId) {
		// TODO Auto-generated method stub
	PeopleSchedule peopleSchedule=	this.peopleScheduleRepo.findById(scheduleId).orElseThrow(() -> new ResourceNotFoundException("Schedule", "Id", scheduleId));
		return this.ScheduleToDto(peopleSchedule);
	}

	@Override
	public List<PeopleScheduleDto> getAllSchedule() {
		// TODO Auto-generated method stub
	List<PeopleSchedule>peopleSchedules=this.peopleScheduleRepo.findAll();
	 List<PeopleScheduleDto> peopleSchedulesListDto =peopleSchedules.stream().map((schedule)->this.ScheduleToDto(schedule)).collect(Collectors.toList());
		return peopleSchedulesListDto;
	}

	@Override
	public PeopleScheduleDto updateSchedule(PeopleScheduleFrontEnd schedularFrontendDto) {
	PeopleSchedule peopleSchedule=  this.peopleScheduleRepo.findById(schedularFrontendDto.getScheduleId()).orElseThrow(() -> new ResourceNotFoundException("Schedule", "Id", schedularFrontendDto.getScheduleId()));
	   
	Member member= this.memberRepo.findById(schedularFrontendDto.getMember()).orElseThrow(() -> new ResourceNotFoundException("Memebr", "Id", schedularFrontendDto.getMember()));
	  
	Baithak2 baithak2 =this.baithak2Repo.findByBaithakId(schedularFrontendDto.getBaithak());
	
	peopleSchedule.setBaithak(baithak2);
	peopleSchedule.setMember(member);
	peopleSchedule.setScheduleId(schedularFrontendDto.getScheduleId());
	peopleSchedule.setDate(schedularFrontendDto.getDate());
	  
		return this.ScheduleToDto(this.peopleScheduleRepo.save(peopleSchedule));
	}

	@Override
	public PeopleScheduleDto getScheduleByDateLocation(String date, Integer locId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PeopleScheduleDto getScheduleByDateLocationBaithak(String date, Integer locId, Integer baithakId) {
		// TODO Auto-generated method stub
		
		PeopleSchedule peopleSchedule= this.peopleScheduleRepo.findByDateAndLocationbaithak(date, baithakId, locId);
		return this.ScheduleToDto(peopleSchedule);
	}

//	@Override
//	public List<PeopleScheduleDto> getScheduleByMonthAndYear(String month, String year, Integer baithakId) {
//		// TODO Auto-generated method stub
//		this.peopleScheduleRepo.findBy
//		return null;
//	}

	public  PeopleScheduleDto ScheduleToDto(PeopleSchedule peopleSchedule){
	PeopleScheduleDto peopleScheduleDto=this.modelMapper.map(peopleSchedule, PeopleScheduleDto.class);
	
	return peopleScheduleDto;
	}
	public  PeopleSchedule DtoToSchedule(PeopleScheduleDto peopleScheduleDto){
		PeopleSchedule peopleSchedule=this.modelMapper.map(peopleScheduleDto, PeopleSchedule.class);
		
		return peopleSchedule;
	}

}
