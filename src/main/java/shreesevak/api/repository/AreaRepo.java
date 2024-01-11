package shreesevak.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shreesevak.api.model.Area;
import shreesevak.api.model.Baithak;
import java.util.List;


public interface AreaRepo extends JpaRepository<Area,Integer> {
	
	

//	 @Query("SELECT a FROM Area a ORDER BY a.areaId DESC")
//  List<Area> findByStatus(String status);
	
	@Query("SELECT a FROM Area a WHERE a.status = :status ORDER BY a.areaId DESC")
	List<Area> findByStatus(@Param("status") String status);
	 
	 @Query("SELECT a FROM Area a ORDER BY a.areaId DESC")
	 List<Area> findAllAreas();
	 
   Area findByAreaName(String areaName);
}
