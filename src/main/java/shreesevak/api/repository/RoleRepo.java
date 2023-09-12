package shreesevak.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shreesevak.api.model.Role;
import shreesevak.api.model.User;
import shreesevak.api.payloads.RoleDto;

public interface RoleRepo extends JpaRepository<Role,Integer>{
	 List<Role> findAllByStatus(String status); 
         Role findByRoleName(String roleName);
            List<Role> findByStatus(String status);
            @Query("SELECT e FROM Role e WHERE CONCAT(e.roleId,' ', e.roleCode,' ', e.roleName,' ',e.status) LIKE %:keyword%")
            List<Role> searchRole( String keyword);
	     
}
