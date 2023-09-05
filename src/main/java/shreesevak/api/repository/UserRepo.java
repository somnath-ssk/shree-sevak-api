package shreesevak.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shreesevak.api.model.User;
import shreesevak.api.payloads.UserDto;

public interface UserRepo extends JpaRepository<User,Integer> {

	  User findByPhoneNumber(String phoneNumber);
       User findByEmailId(String emailId);
      User findByName(String name);
      List<User> findAllByStatus(String status);
      
     
     
//   @Query("SELECT u FROM User u WHERE " + "u.name LIKE CONCAT('%',:keyword,'%')"+"u.emailId LIKE CONCAT('%',:keyword,'%')"+"u.phoneNumber LIKE CONCAT('%',:keyword,'%')"+"u.status LIKE CONCAT('%',:keyword,'%')")
      @Query("SELECT e FROM User e WHERE CONCAT(e.name,' ', e.phoneNumber,' ', e.status) LIKE %:keyword%")
      List<User> searchUser( String keyword);
}

