package shreesevak.api.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceAllReadyExist;
import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.helperclass.BaithakFrontEnd;
import shreesevak.api.model.Area;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Baithak2;
import shreesevak.api.model.Location;
import shreesevak.api.model.Member;
import shreesevak.api.model.WeeklyOff;
import shreesevak.api.payloads.BaithakDto;
import shreesevak.api.repository.AreaRepo;
import shreesevak.api.repository.BaithakRepo;
import shreesevak.api.repository.LocationRepo;
import shreesevak.api.repository.MemberRepo;
import shreesevak.api.repository.WeekRepo;
import shreesevak.api.services.BaithakService;

@Service
public class BaithakServiceImpl implements BaithakService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private WeekRepo weekRepo;
	@Autowired
	private BaithakRepo baithakRepo;

	@Autowired
	private AreaRepo areaRepo;
	@Autowired
	private MemberRepo memberRepo;
	@Autowired
	private LocationRepo locationRepo;

	@Override
	public Baithak createBaithak(BaithakFrontEnd baithak) {
		Baithak baithak2 = new Baithak();

		Location location = this.locationRepo.findByLocationId(baithak.getLocationId());

//    Baithak baithak =this.dtoToBaithak(baithakDto);
//    Location location=locationRepo.findByLocationId(baithakDto.getLocation());
//    
//    baithak.setLocation(location);

//    System.out.println(members);
//    System.out.println("member detail");
		baithak2.setBaithakType(baithak.getBaithakType());
		baithak2.setDayOfWeek(baithak.getDayOfWeek());
		baithak2.setFromTime(baithak.getFromTime());
		baithak2.setToTime(baithak.getToTime());
		baithak2.setStatus(baithak.getStatus());
		baithak2.setLocation(location);
		Baithak saveBaithak = this.baithakRepo.save(baithak2);
		System.out.println(saveBaithak);
		System.out.println("baithakserviceImpl");
		return saveBaithak;
	}

//	@Override
//	public Baithak createBaithakForGentsAndLadies(BaithakFrontEnd baithak) {
//		Baithak2 baithak2=new Baithak2();
//	    List<WeeklyOff> days=this.weekRepo.findAll();
//	    
//	      WeeklyOff day;
//	      for (WeeklyOff day1 : days) {
//			if(baithak.getDayOfWeek().equals(day1)) {
//				day=day1;
//				break;
//			}
//		}
//	    		 		 Location location=  this.locationRepo.findByLocationId(baithak.getLocationId());
//		int areaId =location.getArea().getAreaId();
//		Area area=this.areaRepo.findById(areaId).get();
//		String createId =area.getCountry().getCode()+""+area.getState().getAbbrivation()+""+area.getDivision().getName()+""+day.getId();	
//		        // Generate the baithakId
//		        String generatedId = generateBaithakId(createId);
//		        // Set the generated ID to the baithak object
//		        baithak2.setBithakId(generatedId);
//		        baithak2.getBaithakType();
//		        baithak2.getDayOfWeek();
//		        baithak2.getFromTime();
//		        baithak2.getStatus();
//		        baithak2.getLocation();
//		        baithak2.getToTime();
//	
//		        // Save the baithak object
//		        return this.baithakRepo.save(baithak2);
//		    }
//	}

	@Override
	public Baithak updateBaithak(BaithakFrontEnd baithakFE, Integer baithakId) {
		Location location = this.locationRepo.findByLocationId(baithakFE.getLocationId());
		try {
			Baithak baithak = this.baithakRepo.findByBithakId(baithakId);
			baithak.setBaithakType(baithakFE.getBaithakType());
			baithak.setDayOfWeek(baithakFE.getDayOfWeek());
			baithak.setFromTime(baithakFE.getFromTime());
			baithak.setToTime(baithakFE.getToTime());
			baithak.setLocation(location);
			baithak.setStatus(baithakFE.getStatus());
			
			Baithak updatedBaithak = this.baithakRepo.save(baithak);
			return updatedBaithak;
		} catch (Exception e) {
			throw new ResourceAllReadyExist(" or location ");
		}

	}

	@Override
	public void deleteBaithak(Integer baithakId) {
		Baithak baithak = this.baithakRepo.findByBithakId(baithakId);
		this.baithakRepo.delete(baithak);

	}

	@Override
	public Baithak getSingleBiathakDetails(Integer baithakId) {
		Baithak baithak = this.baithakRepo.findByBithakId(baithakId);

		return baithak;
	}

	@Override
	public List<BaithakDto> getAllBaithak() {
		List<Baithak> baiList = this.baithakRepo.findAll();
		List<BaithakDto> allBaithak = baiList.stream().map((baithak) -> this.baithakToDto(baithak))
				.collect(Collectors.toList());
		return allBaithak;
	}

	public List<BaithakDto> getAllActiveStatus(String status) {
		List<Baithak> baithaks = this.baithakRepo.findByStatus(status);
		List<BaithakDto> allBaithak = baithaks.stream().map((base) -> this.baithakToDto(base))
				.collect(Collectors.toList());
		return allBaithak;
	}

	@Override
	public Baithak createBaithak2(BaithakDto baithakDto) {
		Baithak baithak = this.dtoToBaithak(baithakDto);
//		    Location location=locationRepo.findByLocationId(baithakDto.getLocationId());

//		    baithak.setLocation(location);

		System.out.println("member detail");
//		    List<Member>members=baithakDto.getMemberIds().stream().map(memId->this.memberRepo.findByMemberId(memId)).collect(Collectors.toList());

//		    for (Member member : members) {
//					member.setBaithak(baithak);
//				}

//		    baithak.setMembers(members);
		Baithak saveBaithak = this.baithakRepo.save(baithak);
		System.out.println(saveBaithak.toString());
		System.out.println("baithakserviceImpl");
		return saveBaithak;

	}

	public BaithakDto baithakToDto(Baithak baithak) {
		BaithakDto baithakDto = this.modelMapper.map(baithak, BaithakDto.class);
		return baithakDto;
	}

	public Baithak dtoToBaithak(BaithakDto baithakDto) {
		Baithak baithak = this.modelMapper.map(baithakDto, Baithak.class);
		return baithak;
	}

}
