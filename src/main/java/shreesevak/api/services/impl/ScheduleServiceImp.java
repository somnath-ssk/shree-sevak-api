package shreesevak.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.model.Baithak;
import shreesevak.api.model.Location;
import shreesevak.api.model.Member;
import shreesevak.api.model.Schedular;
import shreesevak.api.model.User;
import shreesevak.api.payloads.MemberDto;
import shreesevak.api.payloads.SchedularDto;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.repository.BaithakRepo;
import shreesevak.api.repository.LocationRepo;
import shreesevak.api.repository.MemberRepo;
import shreesevak.api.repository.ScheduleRepo;
import shreesevak.api.services.ScheduleService;


@Service
public class ScheduleServiceImp implements ScheduleService {

	@Autowired
	private LocationRepo locationRepo;
	
	@Autowired
	private BaithakRepo baithakRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	@Autowired
	private ScheduleRepo scheduleRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public SchedularDto createSchedule(Integer locationId, Integer baithakId, List<Integer> memberIds) {
		Schedular schedule=new Schedular();
//	    Location location=locationRepo.findByLocationId(locationId);
	    Baithak baithak=baithakRepo.findByBithakId(baithakId);
//	    schedule.setLocation1(location);
	    schedule.setBaithak1(baithak);
	List<Member>members = this. memberRepo.findAllById(memberIds);
	     schedule.setMembers(members);
	    
	Schedular updatedMemeber =  this.scheduleRepo.save(schedule);
	return this.scheduleToDto(schedule);
	    
	
	    
	    
	}
	//converting dto to user
	public Schedular dtoToUser(SchedularDto schedularDto) {
		Schedular schedule= this.modelMapper.map(schedularDto,Schedular.class);
	
	    return schedule;

	}

	public SchedularDto scheduleToDto(Schedular schedular) {
		SchedularDto schedularDto=this.modelMapper.map(schedular, SchedularDto.class);

		return schedularDto;
	}
	
}
