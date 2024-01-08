package shreesevak.api.services;


import java.util.List;

import shreesevak.api.model.Area;
import shreesevak.api.payloads.AreaDto;

public interface AreaService {

	AreaDto createArea(Area area);
    AreaDto updateArea(AreaDto area,int areaId);
	AreaDto getSingleAreas(Integer areaId);
	List<AreaDto> getallArea();
	List<AreaDto> getAllAreasByStatus(String status);
	AreaDto getSingleAreaByNames(String areaName);
	List<AreaDto> getAllUnselectedAreas();
	List<AreaDto> getUnselectedAndSingleUserAreas(Integer userId);
    
}
