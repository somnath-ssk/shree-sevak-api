package shreesevak.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.Area;
import shreesevak.api.model.Member;
import shreesevak.api.payloads.AreaDto;
import shreesevak.api.payloads.BaithakDto;
import shreesevak.api.payloads.MemberDto;
import shreesevak.api.repository.AreaRepo;
import shreesevak.api.repository.MemberRepo;
import shreesevak.api.services.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AreaRepo areaRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	

	@Override
	public AreaDto createArea(Area area) {
		Area saveArea = this.areaRepo.save(area);
		return this.areaToDto(area);
	}

	@Override
	public AreaDto updateArea(AreaDto areaDto, int areaId) {
		Area area = this.areaRepo.findById(areaId)
				.orElseThrow(() -> new ResourceNotFoundException("Area not found with this id", "=", areaId));
		area.setAreaName(areaDto.getAreaName());
		area.setCity(areaDto.getCity());
		area.setContactInitial(areaDto.getContactInitial());
		area.setContactName(areaDto.getContactName());
		area.setContactOccupation(areaDto.getContactOccupation());
		area.setContactPhone1(areaDto.getContactPhone1());
		area.setContactPhone2(areaDto.getContactPhone2());
		area.setCountry(areaDto.getCountry());
		area.setDivision(areaDto.getDivision());
		area.setState(areaDto.getState());
		area.setStatus(areaDto.getStatus());
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
		   List<Area> listAreas = this.areaRepo.findAll();
			List<Member>allMembers	=this.memberRepo.findAll();
        System.out.println(allMembers);
		    List<AreaDto> allAreas = listAreas.stream()
		            .map(area -> {
		                long maleCount = allMembers.stream()
		                        .filter(member -> member.getArea().equalsIgnoreCase(area.getAreaName()))
		                        .filter(member -> member.getGender().trim().equalsIgnoreCase("Male"))
		                        .count();

		                long femaleCount = allMembers.stream()
		                        .filter(member -> member.getArea().equals(area.getAreaName()))
		                        .filter(member -> member.getGender().trim().equalsIgnoreCase("Female"))
		                        .count();

		                AreaDto areaDto = areaToDto(area);
		                areaDto.setMaleCount((int) maleCount);
		                areaDto.setFemaleCount((int) femaleCount);

		                // Set members based on the area
//		                List<MemberDto> areaMembers = members.stream()
//		                        .filter(member -> member.getArea().equals(area))
//		                        .map(this::memberToDto)
//		                        .collect(Collectors.toList());
//		                areaDto.setMembers(areaMembers);

		                return areaDto;
		            })
		            .collect(Collectors.toList());

		    return allAreas;
		}
	

	@Override
	public List<AreaDto> getAllAreasByStatus(String status) {
	List<Area>foundAreas=this.areaRepo.findByStatus(status);
	  List<AreaDto>areasDto=foundAreas.stream().map((ar)->this.areaToDto(ar)).collect(Collectors.toList());
		return areasDto;
	}
	
	@Override
	public AreaDto getSingleAreaByNames(String areaName) {
		Area foundArea=this.areaRepo.findByAreaName(areaName);
		return this.areaToDto(foundArea);
	}

	public AreaDto areaToDto(Area area) {

		AreaDto areaDto = this.modelMapper.map(area, AreaDto.class);
		return areaDto;

	}

	public Area dtoToArea(AreaDto areadto) {

		Area area = this.modelMapper.map(areadto, Area.class);
		return area;

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
