package shreesevak.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shreesevak.api.model.Country;
import shreesevak.api.model.State;

public interface CountryRepo extends JpaRepository<Country, Long> {

	
	
}
