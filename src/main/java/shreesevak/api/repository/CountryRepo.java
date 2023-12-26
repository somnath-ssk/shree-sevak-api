package shreesevak.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shreesevak.api.model.Country;

public interface CountryRepo extends JpaRepository<Country, Long> {

}
