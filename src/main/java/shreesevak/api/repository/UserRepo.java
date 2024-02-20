package shreesevak.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shreesevak.api.model.User;
import shreesevak.api.payloads.PaginationResponse;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.model.Role;


public interface UserRepo extends JpaRepository<User,Integer> {

	  User findByPhoneNumber(String phoneNumber);
       User findByEmailId(String emailId);
      User findByName(String name);
      List<User> findAllByStatus(String status);
      
     
     
     
//   @Query("SELECT u FROM User u WHERE " + "u.name LIKE CONCAT('%',:keyword,'%')"+"u.emailId LIKE CONCAT('%',:keyword,'%')"+"u.phoneNumber LIKE CONCAT('%',:keyword,'%')"+"u.status LIKE CONCAT('%',:keyword,'%')")
  
     
      
      @Query("SELECT u FROM User u WHERE CONCAT(u.name,' ', u.phoneNumber,' ', u.status,' ',u.emailId,' ',u.userId) LIKE %:keyword% OR EXISTS (SELECT a FROM u.selectedAreas a WHERE CONCAT(a.areaName) LIKE %:keyword%)")
      Page<User> searchUser(String keyword, Pageable pageable);

      
      @Query("SELECT u FROM User u WHERE CONCAT(u.name,' ', u.phoneNumber,' ', u.status,' ',u.emailId,' ',u.userId) LIKE %:keyword% AND u.status LIKE %:status% OR EXISTS (SELECT a FROM u.selectedAreas a WHERE CONCAT(a.areaName) LIKE %:keyword%)")
      Page<User> searchUser(String keyword,String status,Pageable pageable);

    
      Page<User> findAllByStatus(String status,Pageable pageable);
}

