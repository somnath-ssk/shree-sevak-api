package shreesevak.api.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shreesevak.api.model.Location;
import shreesevak.api.model.User;


public interface LocationRepo extends JpaRepository<Location,Integer> {

	
	
	    Location findByDivision(String division);
	    Location findByCity(String city);
	   List<Location> findByLocationName(String locationName);
	   Location  findByLocationId(Location location);
	   Location  findByLocationId(Integer locationId);
      
	  @Query("SELECT e FROM Location e WHERE CONCAT(e.locationId,' ',e.locationName,' ',e.division ,' ',e.state,' ',e.division ,' ',e.state ,' ',e.country ,' ',e. city ,' ',e.status) LIKE %:keyword%")
     List<Location> searchLocation( String keyword);
	List<Location> findAllByStatus(String status);
	Location findByState(String state);
	
        


}
