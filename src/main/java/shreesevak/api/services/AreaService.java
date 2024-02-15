package shreesevak.api.services;


import java.util.List;

import shreesevak.api.helperclass.AreaFrontEnd;
import shreesevak.api.model.Area;
import shreesevak.api.payloads.AreaDto;
import shreesevak.api.payloads.PaginationResponse;

public interface AreaService {

	AreaDto createArea(AreaFrontEnd area);
    AreaDto updateArea(AreaFrontEnd area,int areaId);
	AreaDto getSingleAreas(Integer areaId);
	List<AreaDto> getallArea();
	List<AreaDto> getAllAreasByStatus(String status);
	PaginationResponse getallArea(Integer pageNumber,Integer pageSize);
	PaginationResponse getAllAreasByStatus(Integer pageNumber,Integer pageSize,Integer status);
	AreaDto getSingleAreaByNames(String areaName);
	List<AreaDto> getAllUnselectedAreas();
	List<AreaDto> getUnselectedAndSingleUserAreas(Integer userId);
	PaginationResponse searchArea(String keyword, String status, int pageNumber, int pageSize);
    
}
