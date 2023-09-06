package shreesevak.api.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreesevak.api.exceptions.ResourceNotFoundException;
import shreesevak.api.model.Baithak;
import shreesevak.api.payloads.BaithakDto;
import shreesevak.api.repository.BaithakRepo;
import shreesevak.api.services.BaithakService;

@Service
public class BaithakServiceImpl implements BaithakService{

	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BaithakRepo baithakRepo;
	
	
	@Override
	public BaithakDto createBaithak(BaithakDto baithakDto) {
	
    Baithak baithak =this.dtoToBaithak(baithakDto);    
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BaithakDto> getAllBaithak() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<BaithakDto> getAllActiveStatus(String status) {
		// TODO Auto-generated method stub
		return null;
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
