package shreesevak.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

import shreesevak.api.model.User;
import shreesevak.api.payloads.PaginationResponse;
import shreesevak.api.payloads.RoleDto;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.payloads.UserFrontEndData;

public interface UserService  {

	UserDto createUser(UserFrontEndData user);
UserDto updateUser(UserFrontEndData user,Integer userId);
	UserDto getUserById(Integer userId);
	UserDto getUserByUserName(String userName);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	List<UserDto> getAllActiveUsers(String status);

	UserDto assignUserRole(Integer userId, List<Integer> roleId);
	PaginationResponse searchUsers(String keyword, String status, int pageNumber, int pageSize);
	
	
	
//    @Query("select u from User u join fetch u.userRoles where u.userId=?1")
//    Optional<User> findByUserIdRole(Integer id);
//	User assignLocationToUser(Integer locId,Integer userId);
}
