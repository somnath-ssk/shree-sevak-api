package shreesevak.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shreesevak.api.model.Member;
import shreesevak.api.model.Role;
import shreesevak.api.model.Scheduler;



public interface MemberRepo extends JpaRepository<Member,Integer> {

	 Member findByPhone(String phone);
	 Member  findByMemberId(Integer memberId);

	 Member findByEmail(String email);
	 Member findByAddharNumber(String email);


	 List<Member> findAllByStatus(String status);
	   @Query("SELECT m FROM Member m ORDER BY m.memberId DESC")
		List<Member> findAllorderByDesc();
	
	  

}
