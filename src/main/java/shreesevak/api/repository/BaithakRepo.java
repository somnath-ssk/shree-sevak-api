package shreesevak.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shreesevak.api.model.Baithak;
import java.util.List;


public interface BaithakRepo extends JpaRepository<Baithak,Integer>{
	
	Baithak findByBithakId(int bithakId);
	List<Baithak> findByStatus(String status);
     

}