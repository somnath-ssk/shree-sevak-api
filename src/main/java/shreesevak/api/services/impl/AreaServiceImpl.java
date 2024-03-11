package shreesevak.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.OffsetMapping.Sort;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.helperclass.AreaFrontEnd;
import shreesevak.api.model.Area;
import shreesevak.api.model.City;
import shreesevak.api.model.Country;
import shreesevak.api.model.Division;
import shreesevak.api.model.Location;
import shreesevak.api.model.Member;
import shreesevak.api.model.State;
import shreesevak.api.model.User;
import shreesevak.api.payloads.AreaDto;
import shreesevak.api.payloads.BaithakDto;
import shreesevak.api.payloads.MemberDto;
import shreesevak.api.payloads.PaginationResponse;
import shreesevak.api.repository.AreaRepo;
import shreesevak.api.repository.CityRepo;
import shreesevak.api.repository.CountryRepo;
import shreesevak.api.repository.DivisionRepo;
import shreesevak.api.repository.MemberRepo;
import shreesevak.api.repository.StateRepo;
import shreesevak.api.repository.UserRepo;
import shreesevak.api.services.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AreaRepo areaRepo;

	@Autowired
	private MemberRepo memberRepo;

	@Autowired
	private UserRepo useRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private CountryRepo countryRepo;
	@Autowired
	private DivisionRepo divisionRepo;

	@Override
	public AreaDto createArea(AreaFrontEnd areabody) {
		Optional<City> city = this.cityRepo.findById(areabody.getCity());
		Optional<State> state = this.stateRepo.findById(areabody.getState());
		Optional<Division> div = this.divisionRepo.findById(areabody.getDivision());
		Optional<Country> country = this.countryRepo.findById(areabody.getCountry());
		Area area = new Area();
		area.setAreaName(areabody.getAreaName());
		area.setContactInitial(areabody.getContactInitial());
		area.setContactName(areabody.getContactName());
		area.setContactOccupation(areabody.getContactOccupation());
		area.setContactPhone1(areabody.getContactPhone1());
		area.setContactEmail(areabody.getContactEmail());
		area.setContactPhone2(areabody.getContactPhone2());
		area.setCity(city.get());
		area.setCountry(country.get());
		area.setDivision(div.get());
		area.setState(state.get());
		area.setStatus(areabody.getStatus());
		Area saveArea = this.areaRepo.save(area);
		return this.areaToDto(area);
	}

	@Override
	public AreaDto updateArea(AreaFrontEnd areabody, int areaId) {
		Optional<City> city = this.cityRepo.findById(areabody.getCity());
		Optional<State> state = this.stateRepo.findById(areabody.getState());
		Optional<Division> div = this.divisionRepo.findById(areabody.getDivision());
		Optional<Country> country = this.countryRepo.findById(areabody.getCountry());
		Area area = this.areaRepo.findById(areaId)
				.orElseThrow(() -> new ResourceNotFoundException("Area not found with this id", "=", areaId));
		area.setAreaName(areabody.getAreaName());
		area.setContactInitial(areabody.getContactInitial());
		area.setContactName(areabody.getContactName());
		area.setContactOccupation(areabody.getContactOccupation());
		area.setContactPhone1(areabody.getContactPhone1());
		area.setContactEmail(areabody.getContactEmail());
		area.setContactPhone2(areabody.getContactPhone2());
		area.setCity(city.get());
		area.setCountry(country.get());
		area.setDivision(div.get());
		area.setState(state.get());
		area.setStatus(areabody.getStatus());
		Area updatedArea = this.areaRepo.save(area);
		return this.areaToDto(updatedArea);
	}

	@Override
	public AreaDto getSingleAreas(Integer areaId) {
		
		Area area = this.areaRepo.findById(areaId)
				.orElseThrow(() -> new ResourceNotFoundException("Area not found with this id", "=", areaId));
		return this.areaToDto(area);
	}

	@Override
	public List<AreaDto> getallArea() {
		List<Area> listAreas = this.areaRepo.findAllAreas();
		List<Member> allMembers = this.memberRepo.findAll();
		System.out.println(allMembers);
		List<AreaDto> allAreas = listAreas.stream().map(area -> {
			List<Member> membersInArea = allMembers.stream().filter(member -> {
				Area memberArea = member.getArea();
				return memberArea != null
						&& memberArea.getAreaName().trim().equalsIgnoreCase(area.getAreaName().trim());
			}).collect(Collectors.toList());

			long maleCount = membersInArea.stream().filter(member -> {
				City city = area.getCity();
				return city != null && member.getCity().getId() == city.getId();
			}).filter(member -> member.getGender().trim().equalsIgnoreCase("Male")).count();

			long femaleCount = membersInArea.stream().filter(member -> {
				City city = area.getCity();
				return city != null && member.getCity().getId() == city.getId();
			}).filter(member -> member.getGender().trim().equalsIgnoreCase("Female")).count();

			AreaDto areaDto = areaToDto(area);
			areaDto.setMaleCount((int) maleCount);
			areaDto.setFemaleCount((int) femaleCount);

			return areaDto;
		}).collect(Collectors.toList());


		return allAreas;
	}

	@Override
	public PaginationResponse getallArea(Integer pageNumber, Integer pageSize) {
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Area> page = this.areaRepo.findAll(p);
		List<Area> listAreas = page.getContent();
		List<Member> allMembers = this.memberRepo.findAll();
		System.out.println(allMembers);
		List<AreaDto> allAreas = listAreas.stream().map(area -> {
			List<Member> membersInArea = allMembers.stream().filter(member -> {
				Area memberArea = member.getArea();
				return memberArea != null
						&& memberArea.getAreaName().trim().equalsIgnoreCase(area.getAreaName().trim());
			}).collect(Collectors.toList());

			long maleCount = membersInArea.stream().filter(member -> {
				City city = area.getCity();
				return city != null && member.getCity().getId() == city.getId();
			}).filter(member -> member.getGender().trim().equalsIgnoreCase("Male")).count();

			long femaleCount = membersInArea.stream().filter(member -> {
				City city = area.getCity();
				return city != null && member.getCity().getId() == city.getId();
			}).filter(member -> member.getGender().trim().equalsIgnoreCase("Female")).count();

			AreaDto areaDto = areaToDto(area);
			areaDto.setMaleCount((int) maleCount);
			areaDto.setFemaleCount((int) femaleCount);

			return areaDto;
		}).collect(Collectors.toList());

		PaginationResponse paginationResponse = new PaginationResponse();
		paginationResponse.setContent(allAreas);
		paginationResponse.setLastPage(page.isLast());
		paginationResponse.setPageNumber(page.getNumber());
		paginationResponse.setPageSize(page.getSize());
		paginationResponse.setTotalPages(page.getTotalPages());
		paginationResponse.setTotoalElement(page.getTotalElements());

		return paginationResponse;
	}

	@Override
	public PaginationResponse getAllAreasByStatus(Integer pageNumber, Integer pageSize,Integer status) {

		Pageable p = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "areaId"));
		Page<Area> page = this.areaRepo.findAllByStatus(status,p);
		List<Area> listAreas = page.getContent();
		List<Member> allMembers = this.memberRepo.findAllByStatus(status+"");
		System.out.println(allMembers);
		List<AreaDto> allAreas = listAreas.stream().map(area -> {
			List<Member> membersInArea = allMembers.stream().filter(member -> {
				Area memberArea = member.getArea();
				return memberArea != null
						&& memberArea.getAreaName().trim().equalsIgnoreCase(area.getAreaName().trim());
			}).collect(Collectors.toList());

			long maleCount = membersInArea.stream().filter(member -> {
				City city = area.getCity();
				return city != null && member.getCity().getId() == city.getId();
			}).filter(member -> member.getGender().trim().equalsIgnoreCase("Male")).count();

			long femaleCount = membersInArea.stream().filter(member -> {
				City city = area.getCity();
				return city != null && member.getCity().getId() == city.getId();
			}).filter(member -> member.getGender().trim().equalsIgnoreCase("Female")).count();

			AreaDto areaDto = areaToDto(area);
			areaDto.setMaleCount((int) maleCount);
			areaDto.setFemaleCount((int) femaleCount);

			return areaDto;
		}).collect(Collectors.toList());

		PaginationResponse paginationResponse = new PaginationResponse();
		paginationResponse.setContent(allAreas);
		paginationResponse.setLastPage(page.isLast());
		paginationResponse.setPageNumber(page.getNumber());
		paginationResponse.setPageSize(page.getSize());
		paginationResponse.setTotalPages(page.getTotalPages());
		paginationResponse.setTotoalElement(page.getTotalElements());

		return paginationResponse;
	}

	@Override
	public List<AreaDto> getAllAreasByStatus(String status) {
		List<Area> listAreas = this.areaRepo.findByStatus(status);
		List<Member> allMembers = this.memberRepo.findAllByStatus(status);

		List<AreaDto> allAreas = listAreas.stream().map(area -> {
			List<Member> membersInArea = allMembers.stream().filter(member -> {
				Area memberArea = member.getArea();

				return memberArea != null && memberArea.getAreaName().trim().equalsIgnoreCase(area.getAreaName().trim())
						&& memberArea.getCity() == area.getCity();
			}).collect(Collectors.toList());

			long maleCount = membersInArea.stream().filter(member -> {
				City city = area.getCity();
				return city != null && member.getCity().getId() == city.getId();
			}).filter(member -> member.getGender().trim().equalsIgnoreCase("Male")).count();

			long femaleCount = membersInArea.stream().filter(member -> {
				City city = area.getCity();
				return city != null && member.getCity().getId() == city.getId();
			}).filter(member -> member.getGender().trim().equalsIgnoreCase("Female")).count();

			AreaDto areaDto = areaToDto(area);
			areaDto.setMaleCount((int) maleCount);
			areaDto.setFemaleCount((int) femaleCount);

			return areaDto;
		}).collect(Collectors.toList());

		return allAreas;
	}

	@Override
	public List<AreaDto> getAllUnselectedAreas() {
		List<Area> areas = this.areaRepo.findAll();
		List<User> users = this.useRepo.findAll();
//		List<Long>userAreaIds=users.stream().flatMap(user->{
//			user.getSelectedAreas().stream().map(area->area.getAreaId()).collect(Collectors.toList());
//		)});
		List<Integer> userAreaIds = users.stream().flatMap(user -> user.getSelectedAreas().stream())
				.map(area -> area.getAreaId()).collect(Collectors.toList());
		List<Area> unselectedAreas = areas.stream().filter(area -> !userAreaIds.contains(area.getAreaId()))
				.collect(Collectors.toList());
		List<AreaDto> unselectedAreaDtos = unselectedAreas.stream().map(this::areaToDto).collect(Collectors.toList());

		return unselectedAreaDtos;
	}

	@Override
	public List<AreaDto> getUnselectedAndSingleUserAreas(Integer userId) {
		List<Area> areas = this.areaRepo.findAll();
		List<User> users = this.useRepo.findAll();

		// Extract area IDs associated with the specified user
		Set<Integer> userAreaIds = users.stream().flatMap(user -> user.getSelectedAreas().stream())
				.map(area -> area.getAreaId()).collect(Collectors.toSet());

		// Filter areas that are not associated with the specified user
		List<Area> unselectedAreas = areas.stream().filter(area -> !userAreaIds.contains(area.getAreaId()))
				.collect(Collectors.toList());
		Optional<User> user = this.useRepo.findById(userId);
		unselectedAreas.addAll(user.get().getSelectedAreas());
		// Map unselected areas to AreaDto
		List<AreaDto> unselectedAreaDtos = unselectedAreas.stream().map(this::areaToDto).collect(Collectors.toList());

		return unselectedAreaDtos;
	}

	@Override
	public AreaDto getSingleAreaByNames(String areaName) {
		Area foundArea = this.areaRepo.findByAreaName(areaName);
		return this.areaToDto(foundArea);
	}

	public AreaDto areaToDto(Area area) {

		AreaDto areaDto = this.modelMapper.map(area, AreaDto.class);
		return areaDto;

	}
	@Override
	public PaginationResponse searchArea(String keyword, String status, int pageNumber, int pageSize) {
		return this.getAllPaginatedRecordBaseOnStatus(keyword, status, pageNumber, pageSize);	
	}

	public Area dtoToArea(AreaDto areadto) {

		Area area = this.modelMapper.map(areadto, Area.class);
		return area;

	}

	public Member dtoToMember(MemberDto memberDto) {
		Member member = this.modelMapper.map(memberDto, Member.class);

		return member;
	}

	public MemberDto memberToDto(Member member) {
		MemberDto memberDto1 = this.modelMapper.map(member, MemberDto.class);

		return memberDto1;
	}
 public PaginationResponse getAllPaginatedRecordBaseOnStatus(String keyword,String status,int pageNumber,int pageSize) {
	 if((keyword.equals("null")|| keyword.equals("undefined")) && (status.equals("null")|| status.equals("undefined"))){
			Pageable p = PageRequest.of(pageNumber, pageSize);
			Page<Area> page = this.areaRepo.findAll(p);
			List<Area> listAreas = page.getContent();
			List<Member> allMembers = this.memberRepo.findAll();
			System.out.println(allMembers);
			List<AreaDto> allAreas = listAreas.stream().map(area -> {
				List<Member> membersInArea = allMembers.stream().filter(member -> {
					Area memberArea = member.getArea();
					return memberArea != null
							&& memberArea.getAreaName().trim().equalsIgnoreCase(area.getAreaName().trim());
				}).collect(Collectors.toList());

				long maleCount = membersInArea.stream().filter(member -> {
					City city = area.getCity();
					return city != null && member.getCity().getId() == city.getId();
				}).filter(member -> member.getGender().trim().equalsIgnoreCase("Male")).count();

				long femaleCount = membersInArea.stream().filter(member -> {
					City city = area.getCity();
					return city != null && member.getCity().getId() == city.getId();
				}).filter(member -> member.getGender().trim().equalsIgnoreCase("Female")).count();

				AreaDto areaDto = areaToDto(area);
				areaDto.setMaleCount((int) maleCount);
				areaDto.setFemaleCount((int) femaleCount);

				return areaDto;
			}).collect(Collectors.toList());

			PaginationResponse paginationResponse = new PaginationResponse();
			paginationResponse.setContent(allAreas);
			paginationResponse.setLastPage(page.isLast());
			paginationResponse.setPageNumber(page.getNumber());
			paginationResponse.setPageSize(page.getSize());
			paginationResponse.setTotalPages(page.getTotalPages());
			paginationResponse.setTotoalElement(page.getTotalElements());

			return paginationResponse;
			
		}else if((status.equals("null")|| status.equals("undefined")) && (!(keyword.equals("null")|| keyword.equals("undefined")))) {
			Pageable p = PageRequest.of(pageNumber, pageSize);
			Page<Area> page = this.areaRepo.searchLocation(keyword, p);
			List<Area> listAreas = page.getContent();
			List<Member> allMembers = this.memberRepo.findAll();
			System.out.println(allMembers);
			List<AreaDto> allAreas = listAreas.stream().map(area -> {
				List<Member> membersInArea = allMembers.stream().filter(member -> {
					Area memberArea = member.getArea();
					return memberArea != null
							&& memberArea.getAreaName().trim().equalsIgnoreCase(area.getAreaName().trim());
				}).collect(Collectors.toList());

				long maleCount = membersInArea.stream().filter(member -> {
					City city = area.getCity();
					return city != null && member.getCity().getId() == city.getId();
				}).filter(member -> member.getGender().trim().equalsIgnoreCase("Male")).count();

				long femaleCount = membersInArea.stream().filter(member -> {
					City city = area.getCity();
					return city != null && member.getCity().getId() == city.getId();
				}).filter(member -> member.getGender().trim().equalsIgnoreCase("Female")).count();

				AreaDto areaDto = areaToDto(area);
				areaDto.setMaleCount((int) maleCount);
				areaDto.setFemaleCount((int) femaleCount);

				return areaDto;
			}).collect(Collectors.toList());

			PaginationResponse paginationResponse = new PaginationResponse();
			paginationResponse.setContent(allAreas);
			paginationResponse.setLastPage(page.isLast());
			paginationResponse.setPageNumber(page.getNumber());
			paginationResponse.setPageSize(page.getSize());
			paginationResponse.setTotalPages(page.getTotalPages());
			paginationResponse.setTotoalElement(page.getTotalElements());

			return paginationResponse;
		}else if((keyword.equals("null")|| keyword.equals("undefined")) && (!(status.equals("null")|| status.equals("undefined")))){
			Pageable p = PageRequest.of(pageNumber, pageSize);
			Page<Area> page = this.areaRepo.findAllByStatus(Integer.parseInt(status), p);
			List<Area> listAreas = page.getContent();
			List<Member> allMembers = this.memberRepo.findAll();
			System.out.println(allMembers);
			List<AreaDto> allAreas = listAreas.stream().map(area -> {
				List<Member> membersInArea = allMembers.stream().filter(member -> {
					Area memberArea = member.getArea();
					return memberArea != null
							&& memberArea.getAreaName().trim().equalsIgnoreCase(area.getAreaName().trim());
				}).collect(Collectors.toList());

				long maleCount = membersInArea.stream().filter(member -> {
					City city = area.getCity();
					return city != null && member.getCity().getId() == city.getId();
				}).filter(member -> member.getGender().trim().equalsIgnoreCase("Male")).count();

				long femaleCount = membersInArea.stream().filter(member -> {
					City city = area.getCity();
					return city != null && member.getCity().getId() == city.getId();
				}).filter(member -> member.getGender().trim().equalsIgnoreCase("Female")).count();

				AreaDto areaDto = areaToDto(area);
				areaDto.setMaleCount((int) maleCount);
				areaDto.setFemaleCount((int) femaleCount);

				return areaDto;
			}).collect(Collectors.toList());

			PaginationResponse paginationResponse = new PaginationResponse();
			paginationResponse.setContent(allAreas);
			paginationResponse.setLastPage(page.isLast());
			paginationResponse.setPageNumber(page.getNumber());
			paginationResponse.setPageSize(page.getSize());
			paginationResponse.setTotalPages(page.getTotalPages());
			paginationResponse.setTotoalElement(page.getTotalElements());

			return paginationResponse;
		}
	 
	 else {
			Pageable p = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "areaId"));
			Page<Area> page = this.areaRepo.searchLocation(keyword, status, p);
			List<Area> listAreas = page.getContent();
			List<Member> allMembers = this.memberRepo.findAllByStatus(status+"");
			System.out.println(allMembers);
			List<AreaDto> allAreas = listAreas.stream().map(area -> {
				List<Member> membersInArea = allMembers.stream().filter(member -> {
					Area memberArea = member.getArea();
					return memberArea != null
							&& memberArea.getAreaName().trim().equalsIgnoreCase(area.getAreaName().trim());
				}).collect(Collectors.toList());

				long maleCount = membersInArea.stream().filter(member -> {
					City city = area.getCity();
					return city != null && member.getCity().getId() == city.getId();
				}).filter(member -> member.getGender().trim().equalsIgnoreCase("Male")).count();

				long femaleCount = membersInArea.stream().filter(member -> {
					City city = area.getCity();
					return city != null && member.getCity().getId() == city.getId();
				}).filter(member -> member.getGender().trim().equalsIgnoreCase("Female")).count();

				AreaDto areaDto = areaToDto(area);
				areaDto.setMaleCount((int) maleCount);
				areaDto.setFemaleCount((int) femaleCount);

				return areaDto;
			}).collect(Collectors.toList());

			PaginationResponse paginationResponse = new PaginationResponse();
			paginationResponse.setContent(allAreas);
			paginationResponse.setLastPage(page.isLast());
			paginationResponse.setPageNumber(page.getNumber());
			paginationResponse.setPageSize(page.getSize());
			paginationResponse.setTotalPages(page.getTotalPages());
			paginationResponse.setTotoalElement(page.getTotalElements());

			return paginationResponse;
		}
 }


}
