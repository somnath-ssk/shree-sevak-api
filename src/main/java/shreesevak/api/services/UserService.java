package shreesevak.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

import shreesevak.api.model.User;
import shreesevak.api.payloads.RoleDto;
import shreesevak.api.payloads.UserDto;

public interface UserService  {

	UserDto createUser(UserDto user);
UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	List<UserDto> getAllActiveUsers(String status);

	User assignUserRole(Integer userId, List<Integer> roleId);
	List<User> searchUsers(String keyword);
	
//    @Query("select u from User u join fetch u.userRoles where u.userId=?1")
//    Optional<User> findByUserIdRole(Integer id);
//	User assignLocationToUser(Integer locId,Integer userId);
}
