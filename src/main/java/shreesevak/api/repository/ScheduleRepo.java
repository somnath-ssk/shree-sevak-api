package shreesevak.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import shreesevak.api.model.Scheduler;
import java.util.List;


public interface ScheduleRepo extends JpaRepository<Scheduler,Integer> {
	 
 Scheduler findByScheduleId(int scheduleId);
}
 