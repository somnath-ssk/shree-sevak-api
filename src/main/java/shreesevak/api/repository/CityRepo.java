package shreesevak.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shreesevak.api.model.City;

public interface CityRepo extends JpaRepository<City, Long> {
	
	@Query("SELECT c FROM City c WHERE c.state.id= :stateId")
	List<City> findAllByStateId(@Param("stateId")Long stateId);
}
