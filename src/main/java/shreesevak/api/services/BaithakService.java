package shreesevak.api.services;

import java.util.List;

import shreesevak.api.helperclass.BaithakFrontEnd;
import shreesevak.api.model.Baithak;
import shreesevak.api.payloads.BaithakDto;

public interface BaithakService  {

	Baithak createBaithak(BaithakFrontEnd baithak);
	Baithak updateBaithak(BaithakFrontEnd baithakDto,Integer baithakId);
	void deleteBaithak(Integer baithakId);
	Baithak getSingleBiathakDetails(Integer baithakId);
	List<BaithakDto> getAllBaithak();
	List<BaithakDto>getAllActiveStatus(String status);
	Baithak createBaithak2(BaithakDto baithakDto);
//	Baithak createBaithakForGentsAndLadies(BaithakFrontEnd baithak);
		
}
