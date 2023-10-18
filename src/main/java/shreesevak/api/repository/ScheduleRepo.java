package shreesevak.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import shreesevak.api.model.Scheduler;
import java.util.List;
import shreesevak.api.model.Location;



public interface ScheduleRepo extends JpaRepository<Scheduler,Integer> {
	 
 Scheduler findByScheduleId(Integer scheduleId);
  boolean existsByDateAndLocation(String date, Location location);
  boolean existsByLocation(Location location);
 boolean existsByLocationAndDate(Location location, String date);
}
 