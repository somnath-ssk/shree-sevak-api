package shreesevak.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceAllReadyExist;
import shreesevak.api.helperclass.BaithakFrontEnd;
import shreesevak.api.model.Area;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Baithak2;
import shreesevak.api.model.City;
import shreesevak.api.model.Country;
import shreesevak.api.model.Location;
import shreesevak.api.model.State;
import shreesevak.api.model.WeeklyOff;
import shreesevak.api.payloads.Baithak2Dto;
import shreesevak.api.payloads.LocationDto;
import shreesevak.api.repository.AreaRepo;
import shreesevak.api.repository.Baithak2Repo;
import shreesevak.api.repository.BaithakRepo;
import shreesevak.api.repository.LocationRepo;
import shreesevak.api.repository.MemberRepo;
import shreesevak.api.repository.WeekRepo;
import shreesevak.api.services.Baithak2service;

@Service
public class Bathak2ServiceImpl implements Baithak2service {

	@Autowired
	private ModelMapper modelMapper;
      
	@Autowired
	private LocationServiceImpl locationServiceImpl;
	
	@Autowired
	private WeekRepo weekRepo;

	@Autowired
	private Baithak2Repo baithak2Repo;

	@Autowired
	private AreaRepo areaRepo;
	@Autowired
	private MemberRepo memberRepo;
	@Autowired
	private LocationRepo locationRepo;

	public Baithak2Dto createBaithakForGentsAndLadies(BaithakFrontEnd baithak) {
		Baithak2 baithak2 = new Baithak2();
		List<WeeklyOff> days = this.weekRepo.findAll();
		WeeklyOff day=null;
		for (WeeklyOff day1 : days) {
			if (baithak.getDayOfWeek() == day1.getId()) {
				day = day1;
				break;
			}
		}

		Location location = this.locationRepo.findByLocationId(baithak.getLocation());
//		int areaId = location.getArea().getAreaId();
//		Area area=this.areaRepo.findById(areaId).get();
//		String createId =area.getCountry().getCode()+""+area.getCity().getNumber()+""+area.getDivision().getName()+""+day.getId();	
//		        // Generate the baithakId
//		        String generatedId = generateBaithakId(createId);
		// Set the generated ID to the baithak object
//		        baithak2.setBithakId(generatedId);
		baithak2.setBaithakType(baithak.getBaithakType());

		baithak2.setDayOfWeek(day);
   baithak2.setStatus(true);
		baithak2.setFromTime(baithak.getFromTime());

		baithak2.setStatus(true);
		baithak2.setLocation(location);

		baithak2.setToTime(baithak.getToTime());

		Baithak2 baithakFound = this.baithak2Repo.findByBaithak2ByLocTypeDayTime(baithak.getLocation(),
				baithak.getBaithakType(), baithak2.getDayOfWeek().getId(), baithak2.getFromTime());
		if (baithakFound != null) {
//			throw new ResourceAllReadyExist(baithak2.getDayOfWeek() + " from Time : " + baithak2.getFromTime() + " for "
//					+ baithak2.getBaithakType());
			throw new ResourceAllReadyExist(baithakFound.getDayOfWeek().getDay() + " from Time : " + baithak2.getFromTime()
					+ " for " + baithak2.getBaithakType());
		} else {

			Baithak2 saveBaithak = this.baithak2Repo.save(baithak2);
			saveBaithak.setLocation(this.modifyLocation(location));

			return this.baithak2ToDto(saveBaithak);
		}

	}

//	public String generateBaithakId(String idString) {
//	        // Retrieve the latest baithakId from the database
//	        Baithak2 latestBaithak = this.baithak2Repo.findFirstByBithakIdStartingWithOrderByBithakIdDesc(idString+"_");
//	        
//	        // Extract the numeric part and increment it
//	        int nextId = 1; // Default to 1 if no baithak exists yet
//	        String prefix = idString+"_"; // Default prefix
//	        
//	        if (latestBaithak != null) {
//	            String[] parts = latestBaithak.getBithakId().split("_");
//	            nextId = Integer.parseInt(parts[1]) + 1;
//	            // Check if the prefix needs to change
//	         
//	                prefix = idString+"_";
//	            
//	        }else {
//	        	return idString+"_001";
//	        }
//	        
//	        // Format the new baithakId
//	        String formattedId = String.format("%03d", nextId);
//	        String newBaithakId = prefix + formattedId;
//	        
//	        return newBaithakId;
//	    }
//	

	@Override
	public Baithak2Dto updateBaithak2(BaithakFrontEnd baithak, Integer baithakId) {

		Baithak2 baithak2 = this.baithak2Repo.findByBaithakId(baithakId);
		List<WeeklyOff> days = this.weekRepo.findAll();
		WeeklyOff day = null;
		for (WeeklyOff day1 : days) {
			if (baithak.getDayOfWeek() == day1.getId()) {
				day = day1;
				break;
			}
		}

		Location location = this.locationRepo.findByLocationId(baithak.getLocation());
//	int areaId = location.getArea().getAreaId();
//	Area area=this.areaRepo.findById(areaId).get();
//	String createId =area.getCountry().getCode()+""+area.getCity().getNumber()+""+area.getDivision().getName()+""+day.getId();	
//	        // Generate the baithakId
//	        String generatedId = generateBaithakId(createId);
		// Set the generated ID to the baithak object
//	        baithak2.setBithakId(generatedId);
		baithak2.setBaithakType(baithak.getBaithakType());

		baithak2.setDayOfWeek(day);

		baithak2.setFromTime(baithak.getFromTime());

		baithak2.setStatus(baithak.isStatus());

		baithak2.setLocation(location);

		baithak2.setToTime(baithak.getToTime());
		Baithak2 baithakFound = this.baithak2Repo.findByBaithak2ByLocTypeDayTime(baithak.getLocation(),
				baithak2.getBaithakType(), baithak.getDayOfWeek(), baithak2.getFromTime());
		if (baithakFound != null) {
//			throw new ResourceAllReadyExist(baithak2.getDayOfWeek() + " from Time : " + baithak2.getFromTime() + " for "
//					+ baithak2.getBaithakType());
			throw new ResourceAllReadyExist(
					day.getDay() + "from Time : " + baithak2.getFromTime() + " for " + baithak2.getBaithakType());
		} else {
			Baithak2 saveBaithak = this.baithak2Repo.save(baithak2);
			saveBaithak.setLocation(this.modifyLocation(location));
			return this.baithak2ToDto(saveBaithak);

		}

		// Save the baithak object

	}

	@Override
	public Baithak2Dto getbaithakByLocTypeDayTime(Integer locationId, Integer baithakType, String day, String fromTime) {
		Baithak2 foundBaithak = this.baithak2Repo.findByBaithak2ByLocTypeDayTime(locationId, day, baithakType, fromTime);
		Location location = foundBaithak.getLocation();

		foundBaithak.setLocation(this.modifyLocation(location));
		return this.baithak2ToDto(foundBaithak);
	}

	

	@Override
	public List<Baithak2Dto> getBaithak2BaseOnLocation(Integer locationId) {
	List<Baithak2> baithakList=this.baithak2Repo.findByLocationId(locationId);
	    List<Baithak2Dto> baithakDtosList=baithakList.stream().map(baithak->this.baithak2ToDto(baithak)).collect(Collectors.toList());
		return baithakDtosList;
	}
	@Override
	public Baithak2Dto getSingleBiathakDetails(Integer baithakId) {
		
		return null;
	}

	public Baithak2Dto baithak2ToDto(Baithak2 saveBaithak) {
        Baithak2Dto baithak2Dto=new Baithak2Dto();
        baithak2Dto.setBaithakId(saveBaithak.getBaithakId());
        baithak2Dto.setBaithakType(saveBaithak.getBaithakType());
        baithak2Dto.setDayOfWeek(saveBaithak.getDayOfWeek());
        baithak2Dto.setFromTime(saveBaithak.getFromTime());
     
        baithak2Dto.setLocation(locationServiceImpl.locationToDto(this.modifyLocation(saveBaithak.getLocation())));
        baithak2Dto.setStatus(saveBaithak.isStatus());
        
        baithak2Dto.setToTime(saveBaithak.getToTime());
		return baithak2Dto;
	}

	private Baithak2 DtoToBaithak2(Baithak2Dto baithakDto) {

		return this.modelMapper.map(baithakDto, Baithak2.class);
	}

	@Override
	public List<Baithak2Dto> getAllbaithak2Details() {
		// TODO Auto-generated method stub
		return null;
	}

	public Location modifyLocation(Location location) {
		Country country = location.getCountry();
		country.setStates(null);
		City city = location.getCity();
		city.setState(null);
		city.setDivisions(null);

		State state = location.getState();
		state.setCities(null);
		state.setCountry(null);
		location.setCountry(country);
		location.setState(state);
		location.setCity(city);

		return location;
	}


}
