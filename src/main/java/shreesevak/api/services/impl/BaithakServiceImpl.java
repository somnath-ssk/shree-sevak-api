package shreesevak.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Location;
import shreesevak.api.model.Member;
import shreesevak.api.payloads.BaithakDto;
import shreesevak.api.repository.BaithakRepo;
import shreesevak.api.repository.LocationRepo;
import shreesevak.api.repository.MemberRepo;
import shreesevak.api.services.BaithakService;

@Service
public class BaithakServiceImpl implements BaithakService{

	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BaithakRepo baithakRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	@Autowired
	private LocationRepo locationRepo;
	@Override
	public BaithakDto createBaithak(BaithakDto baithakDto) {
	
    Baithak baithak =this.dtoToBaithak(baithakDto);
//    Location location=locationRepo.findByLocationId(baithakDto.getLocation());
//    
//    baithak.setLocation(location);
    
//    System.out.println(members);
    System.out.println("member detail");
    
    Baithak saveBaithak =this.baithakRepo.save(baithak);
    System.out.println(saveBaithak);
    System.out.println("baithakserviceImpl");
		return this.baithakToDto(saveBaithak);
	}

	@Override
	public BaithakDto updateBaithak(BaithakDto baithakDto, Integer baithakId) {
	
		try {
			Baithak baithak  = this.baithakRepo.findByBithakId(baithakId);
			baithak.setBaithakType(baithakDto.getBaithakType());
			baithak.setDayOfWeek(baithakDto.getDayOfWeek());
			baithak.setFromTime(baithakDto.getFromTime());
			baithak.setToTime(baithakDto.getToTime());
			baithak.setStatus(baithakDto.getStatus());
			baithak.setLocation(baithakDto.getLocation());
			baithak.setMembers(baithakDto.getMembers());
			return this.baithakToDto(baithak);
		}catch (Exception e) {
		  throw new ResourceNotFoundException("Baithak ", "Id", baithakId);
		}
		
	}

	@Override
	public void deleteBaithak(Integer baithakId) {
		Baithak baithak  = this.baithakRepo.findByBithakId(baithakId);
		this.baithakRepo.delete(baithak);
		
	}

	@Override
	public BaithakDto getSingleBiathakDetails(Integer baithakId) {
		Baithak baithak=this.baithakRepo.findByBithakId(baithakId);
		
		return this.baithakToDto(baithak);
	}

	@Override
	public List<BaithakDto> getAllBaithak() {
		List<Baithak> baiList=this.baithakRepo.findAll();
		List<BaithakDto>allBaithak=  baiList.stream().map((baithak)-> this.baithakToDto(baithak)).collect(Collectors.toList());
		return allBaithak;
	}

	
	public List<BaithakDto> getAllActiveStatus(String status) {
	List<Baithak>baithaks=this.baithakRepo.findByStatus(status);
	List<BaithakDto>allBaithak= baithaks.stream().map((base)-> this.baithakToDto(base)).collect(Collectors.toList());
		return allBaithak;
	}
	



	@Override
	public Baithak createBaithak2(BaithakDto baithakDto, Integer locationId, List<Integer> memberIds) {
		 Baithak baithak =this.dtoToBaithak(baithakDto);
		    Location location=locationRepo.findByLocationId(locationId);
		    
		    baithak.setLocation(location);
		  
		    System.out.println("member detail");
		    List<Member>members=memberIds.stream().map(memId->this.memberRepo.findByMemberId(memId)).collect(Collectors.toList());
		    baithak.setMembers(members);
		    Baithak saveBaithak =this.baithakRepo.save(baithak);
		    System.out.println(saveBaithak.toString());
		    System.out.println("baithakserviceImpl");
				return saveBaithak;
	
	}
	
	public BaithakDto baithakToDto(Baithak baithak) {
	BaithakDto baithakDto =this.modelMapper.map(baithak, BaithakDto.class);
		return baithakDto;
	}
	public Baithak dtoToBaithak(BaithakDto baithakDto)
	{  
	Baithak baithak	=this.modelMapper.map(baithakDto,Baithak.class);
		return baithak;
	}
}
