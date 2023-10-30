package shreesevak.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shreesevak.api.model.Scheduler;
import shreesevak.api.payloads.SchedularDto;

import java.util.List;

import shreesevak.api.model.Baithak;
import shreesevak.api.model.Location;

public interface ScheduleRepo extends JpaRepository<Scheduler, Integer> {

	Scheduler findByScheduleId(Integer scheduleId);
	

	boolean existsByDateAndLocation(String date, Location location);

	boolean existsByLocation(Location location);

	boolean existsByLocationAndDateAndBaithak(Location location, String date,Baithak baithak);

	@Query("SELECT e FROM Scheduler e WHERE e.date= :dateParam AND e.location.locationId = :locationIdParam AND e.baithak.bithakId= :baithakIdParam")
	Scheduler findByDateAndLocationIdBaithak(@Param("dateParam") String date, @Param("locationIdParam") Integer locationId,
			@Param("baithakIdParam") Integer bithakId);
	
	@Query("SELECT s FROM Scheduler s WHERE s.date LIKE CONCAT('%', :dynamicMonth, '% ', :dynamicYear) AND s.baithak.bithakId= :baithakIdParam" )
	List<Scheduler> findByMonthAndYearAndBaithak(@Param("dynamicMonth") String dynamicMonth,@Param("dynamicYear") String dynamicYear,@Param("baithakIdParam") Integer bithakId );
//  
    @Query("SELECT s FROM Scheduler s ORDER BY s.scheduleId DESC")
	List<Scheduler> findAllorderByDesc();
}
