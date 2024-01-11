package shreesevak.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import shreesevak.api.model.Member;
import shreesevak.api.model.Role;
import shreesevak.api.model.Scheduler;


@EnableJpaRepositories
public interface MemberRepo extends JpaRepository<Member,Integer> {

	 Member findByPhone(String phone);
	 Member  findByMemberId(Integer memberId);

	 Member findByEmail(String email);
	 Member findByAddharNumber(String email);

	 @Query("SELECT m FROM Member m WHERE m.status = :status ORDER BY m.memberId DESC")
 List<Member> findAllByStatus(@Param("status") String status);
	 
	   @Query("SELECT m FROM Member m ORDER BY m.memberId DESC")
		List<Member> findAllorderByDesc();
	   
	   
//	   
//  List<Member> findByAreaId();
	  

}
