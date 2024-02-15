package shreesevak.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shreesevak.api.model.Area;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Location;

import java.util.List;


public interface AreaRepo extends JpaRepository<Area,Integer> {
	
	

//	 @Query("SELECT a FROM Area a ORDER BY a.areaId DESC")
//  List<Area> findByStatus(String status);
	
	@Query("SELECT a FROM Area a WHERE a.status = :status ORDER BY a.areaId DESC")
	List<Area> findByStatus(@Param("status") String status);
	 
	 @Query("SELECT a FROM Area a ORDER BY a.areaId DESC")
	 List<Area> findAllAreas();
	 
   Area findByAreaName(String areaName);
  Page<Area> findAllByStatus(Integer status,Pageable pageable);
  
  @Query("SELECT a FROM Area a WHERE CONCAT(a.areaName,' ',a.maleCount,' ',a.femaleCount ,'  ',a.contactName,' ',a.country.name ,' ',a.city.name ,' ',a.state.name ,' ',a.status,' ',a.contactPhone1,' ',a.contactEmail ,' ',a. division.name) LIKE %:keyword% AND a.status LIKE %:status%")
  Page<Area> searchLocation( String keyword,String status,Pageable pageable);
  @Query("SELECT a FROM Area a WHERE CONCAT(a.areaName,' ',a.maleCount,' ',a.femaleCount ,'  ',a.contactName,' ',a.country.name ,' ',a.city.name ,' ',a.state.name ,' ',a.status,' ',a.contactPhone1,' ',a.contactEmail ,' ',a. division.name) LIKE %:keyword%")
  Page<Area> searchLocation( String keyword,Pageable pageable);
}
