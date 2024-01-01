package shreesevak.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shreesevak.api.model.State;
import java.util.List;


public interface StateRepo extends JpaRepository<State, Long> {
	
	
	@Query("SELECT s FROM State s WHERE s.country.id= :countryId")
	List<State> findAllStateByCountryId(@Param("countryId") Long countryId);
}
