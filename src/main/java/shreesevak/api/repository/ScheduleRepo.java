package shreesevak.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shreesevak.api.model.Schedular;

public interface ScheduleRepo extends JpaRepository<Schedular,Integer> {
	 

}
 