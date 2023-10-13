package shreesevak.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Location;
import shreesevak.api.model.Member;

import shreesevak.api.model.Scheduler;
import shreesevak.api.model.User;
import shreesevak.api.payloads.MemberDto;
import shreesevak.api.payloads.SchedularDto;
import shreesevak.api.payloads.SchedularFrontendDto;
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
	public SchedularDto createSchedule(SchedularFrontendDto schedularFrontendDto) {
		Scheduler schedule = new Scheduler();

		Location location = locationRepo.findByLocationId(schedularFrontendDto.getLocationId());
		schedule.setLocation(location);

		Baithak baithak = baithakRepo.findByBithakId(schedularFrontendDto.getBaithakId());
		schedule.setBaithak(baithak);

		List<Member> members = this.memberRepo.findAllById(schedularFrontendDto.getMembersId());
		schedule.setMembers(members);
        schedule.setDate(schedularFrontendDto.getDate());
        schedule.setStatus(schedularFrontendDto.getStatus());
		Scheduler updatedMemeber = this.scheduleRepo.save(schedule);
		return this.scheduleToDto(schedule);

	}

	@Override
	public SchedularDto getSingleSchedule(Integer scheduleId) {
		Scheduler schedule = this.scheduleRepo.findById(scheduleId)
				.orElseThrow(() -> new ResourceNotFoundException("Schedule", "Id", scheduleId));

		return this.scheduleToDto(schedule);
	}

	@Override
	public List<SchedularDto> getAllSchedule() {
		List<Scheduler> schedules = this.scheduleRepo.findAll();
		List<SchedularDto> schedularList = schedules.stream().map((schedule) -> this.scheduleToDto(schedule))
				.collect(Collectors.toList());

		return schedularList;
	}

	@Override
	public SchedularDto updateSchedule(SchedularFrontendDto schedularFrontendDto, Integer scheduleId) {
		Scheduler schedular = this.scheduleRepo.findById(scheduleId)
				.orElseThrow(() -> new ResourceNotFoundException("Schedule", "Id", scheduleId));
	
		Location location = this.locationRepo.findById(schedularFrontendDto.getLocationId()).orElseThrow(
				() -> new ResourceNotFoundException("location", "Id", schedularFrontendDto.getLocationId()));
		Baithak baithak = this.baithakRepo.findById(schedularFrontendDto.getBaithakId()).orElseThrow(
				() -> new ResourceNotFoundException("location", "Id", schedularFrontendDto.getLocationId()));
		List<Member> members = this.memberRepo.findAllById(schedularFrontendDto.getMembersId());
		schedular.setLocation(location);
		schedular.setBaithak(baithak);
		schedular.setMembers(members);
		schedular.setStatus(schedularFrontendDto.getStatus());
		schedular.setDate(schedularFrontendDto.getDate());
    return this.scheduleToDto(schedular);
	}

	// converting dto to user
	public Scheduler dtoToUser(SchedularDto schedularDto) {
		Scheduler schedule = this.modelMapper.map(schedularDto, Scheduler.class);

		return schedule;

	}

	public SchedularDto scheduleToDto(Scheduler schedular) {
		SchedularDto schedularDto = this.modelMapper.map(schedular, SchedularDto.class);

		return schedularDto;
	}

}
