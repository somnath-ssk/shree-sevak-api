package shreesevak.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import shreesevak.api.model.Location;
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
	   
	   Page<Member> findAllByStatus(String status, Pageable pageable);
//	   
//  List<Member> findByAreaId();
	
	   
	   @Query("SELECT m FROM Member m WHERE CONCAT(m.firstName,' ',m.middleName,' ',m.lastName ,' ',m.gender,' ',m.mobile ,' ',m.area.areaName ,' ',m.status,' ',m.pincode) LIKE %:keyword%")
	   Page<Member>searchMember(String keyword,Pageable pageable);
	   @Query("SELECT m FROM Member m WHERE CONCAT(m.firstName,' ',m.middleName,' ',m.lastName ,' ',m.gender,' ',m.mobile ,' ',m.area.areaName ,' ',m.status,' ',m.pincode) LIKE %:keyword% AND m.status LIKE %:status%")
	   Page<Member>searchMember(String keyword,String status,Pageable pageable);

}
