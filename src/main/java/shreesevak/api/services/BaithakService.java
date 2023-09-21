package shreesevak.api.services;

import java.util.List;

import shreesevak.api.model.Baithak;
import shreesevak.api.payloads.BaithakDto;

public interface BaithakService  {

	BaithakDto createBaithak(BaithakDto baithakDto);
	BaithakDto updateBaithak(BaithakDto baithakDto,Integer baithakId);
	void deleteBaithak(Integer baithakId);
	BaithakDto getSingleBiathakDetails(Integer baithakId);
	List<BaithakDto> getAllBaithak();
	List<BaithakDto>getAllActiveStatus(String status);
	Baithak createBaithak2(BaithakDto baithakDto, Integer locationId, List<Integer> memberId);
		
}
