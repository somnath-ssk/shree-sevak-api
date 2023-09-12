package shreesevak.api.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shreesevak.api.model.Location;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private int userId;
	@NotEmpty
	@Size(min=4 , message="User Name must min 4 charactor")
	private String name;
	private String password;
	

	private String emailId;
	
	@NotEmpty
	@Pattern(regexp="^\\\\d{10}$")
	private String phoneNumber;
	
	private String providerId;
	
	private String photoUrl;
	
	@NotEmpty
	private String status;

	private List<RoleDto>roles=new ArrayList<>();

//	private List<Location>locations=new ArrayList<>();
}
