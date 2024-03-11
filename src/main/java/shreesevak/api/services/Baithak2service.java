package shreesevak.api.services;

import java.util.List;

import shreesevak.api.helperclass.BaithakFrontEnd;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Baithak2;
import shreesevak.api.payloads.Baithak2Dto;

public interface Baithak2service {
	
	Baithak2Dto createBaithakForGentsAndLadies(BaithakFrontEnd baithak);
	Baithak2Dto updateBaithak2(BaithakFrontEnd baithak, Integer baithakId);
	Baithak2Dto getSingleBiathakDetails(Integer baithakId);
List<Baithak2Dto> getAllbaithak2Details();
//	Baithak2Dto getSingleBiathakDetails(Integer baithakId);
Baithak2Dto getbaithakByLocTypeDayTime(Integer locationId, Integer baithakType, String day, String fromTime);
}
