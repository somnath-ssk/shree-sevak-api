package shreesevak.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shreesevak.api.model.Baithak2;

public interface Baithak2Repo extends JpaRepository<Baithak2, String> {
	
	
	Baithak2 findFirstByBithakIdStartingWithOrderByBithakIdDesc(String string);


}
