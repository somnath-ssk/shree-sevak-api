package shreesevak.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Location;
import shreesevak.api.model.Member;

import shreesevak.api.model.Scheduler;
import shreesevak.api.model.User;
import shreesevak.api.payloads.MemberDto;
import shreesevak.api.payloads.PaginationResponse;
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

		Member members1 = this.memberRepo.findByMemberId(schedularFrontendDto.getVachanGhenara());
		Member members2 = this.memberRepo.findByMemberId(schedularFrontendDto.getHajeriGhenara());

		List<Member> members = new ArrayList<Member>();
		members.add(members1);
		members.add(members2);
		schedule.setMembers(members);
		schedule.setDate(schedularFrontendDto.getDate());
		schedule.setStatus(schedularFrontendDto.getStatus());
		if (!this.scheduleRepo.existsByLocationAndDateAndBaithak(schedule.getLocation(), schedule.getDate(),
				schedule.getBaithak())) {
			this.scheduleRepo.save(schedule);
			return this.scheduleToDto(schedule);
		}
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
		List<Scheduler> schedules = this.scheduleRepo.findAllorderByDesc();
		List<SchedularDto> schedularList = schedules.stream().map((schedule) -> this.scheduleToDto(schedule))
				.collect(Collectors.toList());

		return schedularList;
	}

	@Override
	public PaginationResponse getAllSchedule(Integer pageNumber, Integer pageSize) {
		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "scheduleId"));
		Page<Scheduler> page = this.scheduleRepo.findAll(p);
		List<Scheduler> scheduleList = page.getContent();
		List<SchedularDto> scheduleDtoList = scheduleList.stream().map((schedule) -> this.scheduleToDto(schedule))
				.collect(Collectors.toList());
		
		PaginationResponse paginationResponse=new PaginationResponse();
		paginationResponse.setContent(scheduleDtoList);
		paginationResponse.setLastPage(page.isLast());
		paginationResponse.setPageNumber(page.getNumber());
		paginationResponse.setPageSize(page.getSize());
		paginationResponse.setTotalPages(page.getTotalPages());
		paginationResponse.setTotoalElement(page.getTotalElements());
		return paginationResponse;
	}

	@Override
	public SchedularDto updateSchedule(SchedularFrontendDto schedularFrontendDto) {
		Scheduler schedular = this.scheduleRepo.findById(schedularFrontendDto.getScheduleId()).orElseThrow(
				() -> new ResourceNotFoundException("Schedule", "Id", schedularFrontendDto.getScheduleId()));

		Location location = this.locationRepo.findById(schedularFrontendDto.getLocationId()).orElseThrow(
				() -> new ResourceNotFoundException("location", "Id", schedularFrontendDto.getLocationId()));
		Baithak baithak = this.baithakRepo.findById(schedularFrontendDto.getBaithakId())
				.orElseThrow(() -> new ResourceNotFoundException("baithak", "Id", schedularFrontendDto.getBaithakId()));
		Member members1 = this.memberRepo.findByMemberId(schedularFrontendDto.getVachanGhenara());
		Member members2 = this.memberRepo.findByMemberId(schedularFrontendDto.getHajeriGhenara());

		List<Member> members = new ArrayList<Member>();
		members.add(members1);
		members.add(members2);
		schedular.setMembers(members);
		schedular.setLocation(location);
		schedular.setBaithak(baithak);
		schedular.setMembers(members);
		schedular.setStatus(schedularFrontendDto.getStatus());
		schedular.setDate(schedularFrontendDto.getDate());
		this.scheduleRepo.save(schedular);
		return this.scheduleToDto(schedular);
	}

	@Override
	public Scheduler getScheduleByDateLocationBaithak(String date, Integer locId, Integer baithakId) {

		Scheduler schedular = this.scheduleRepo.findByDateAndLocationIdBaithak(date, locId, baithakId);
		System.out.println(schedular);
		return schedular;
//		if(schedular==null) {
////			throw new RuntimeException("Schedule not found with"+date+"location Id"+locId);
//				return schedular;
//			
//		}else {
////			System.out.println(schedular);
//			System.out.println("inside getScheduleByDateLocBaithak" );
//			return null;
//		}

	}

	@Override
	public List<Scheduler> getScheduleByMonthAndYearAndBaithak(String month, String year, Integer baithakId) {
		List<Scheduler> schedules = this.scheduleRepo.findByMonthAndYearAndBaithak(month, year, baithakId);
		if (schedules == null) {
			throw new RuntimeException("Schedule not found with month " + month + "And year " + year);
		} else {
//			System.out.println(schedules);
			System.out.println("inside getScheduleByDateLocBaithak");
			List<Scheduler> filteredSchedules = schedules.stream()
					.filter(schedule -> "1".equals(schedule.getLocation().getStatus())).collect(Collectors.toList());
			return filteredSchedules;
		}

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

	@Override
	public PaginationResponse searchSchedule(String keyword,  int pageNumber, int pageSize) {
	
			Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "scheduleId"));
			Page<Scheduler> page = this.scheduleRepo.searchSchedule(keyword, p);
			List<Scheduler> scheduleList = page.getContent();
			List<SchedularDto> scheduleDtoList = scheduleList.stream().map((schedule) -> this.scheduleToDto(schedule))
					.collect(Collectors.toList());
			
			PaginationResponse paginationResponse=new PaginationResponse();
			paginationResponse.setContent(scheduleDtoList);
			paginationResponse.setLastPage(page.isLast());
			paginationResponse.setPageNumber(page.getNumber());
			paginationResponse.setPageSize(page.getSize());
			paginationResponse.setTotalPages(page.getTotalPages());
			paginationResponse.setTotoalElement(page.getTotalElements());
			return paginationResponse;
		
	}

}
