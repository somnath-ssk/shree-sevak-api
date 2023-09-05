package shreesevak.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import shreesevak.api.model.Member;
import shreesevak.api.model.Role;


public interface MemberRepo extends JpaRepository<Member,Integer> {

	 Member findByPhone(String phone);

	 Member findByEmail(String email);
	 Member findByAddharNumber(String email);


	 List<Member> findAllByStatus(String status);
	
	  

}
