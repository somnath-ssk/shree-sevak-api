package shreesevak.api.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shreesevak.api.model.Location;
import shreesevak.api.model.User;


public interface LocationRepo extends JpaRepository<Location,Integer> {

	
	
	 
	   List<Location> findByLocationName(String locationName);
	   Location  findByLocationId(Location location);
	   Location  findByLocationId(Integer locationId);
	    
	   @Query("SELECT e FROM Location e ORDER BY e.locationId DESC")
	   List<Location>  findAllByOrderDesc();
//      
	  @Query("SELECT e FROM Location e WHERE CONCAT(e.locationId,' ',e.locationName,' ',e.division.name ,'  ',e.state.name ,' ',e.country.name ,' ',e. city.name ,' ',e.status,' ',e.area.areaName,' ',e.pincode) LIKE %:keyword% AND e.status LIKE %:status%")
	  Page<Location> searchLocation( String keyword,String status,Pageable pageable);
	  @Query("SELECT e FROM Location e WHERE CONCAT(e.locationId,' ',e.locationName,' ',e.division.name ,'  ',e.state.name ,' ',e.country.name ,' ',e. city.name ,' ',e.status,' ',e.area.areaName,' ',e.pincode) LIKE %:keyword%")
	  Page<Location> searchLocation( String keyword,Pageable pageable);
	  
//	  @Query("SELECT E FROM Location e WHERE e.status ORDER BY e.locationId DESC ")
	
	  @Query("SELECT l FROM Location l WHERE l.status= :status ORDER BY l.locationId DESC")
	List<Location> findAllByStatus(@Param("status") String status);
//	Location findByState(String state);
	 @Query("SELECT l.status FROM Location l WHERE l.status = :status ORDER BY l.locationId DESC")
	List<Location> findStatusByOrderByLocationIdDesc(String status);
	
	 @Query("SELECT l FROM Location l WHERE l.area.areaId= :areaId")
	List<Location> findByLocationAreaId(@Param("areaId") Integer areaId);
	 Page<Location> findAllByStatus(String status,Pageable pageable);
        


}
