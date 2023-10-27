package shreesevak.api.payloads;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserFrontEndData {
	
	private String name;
	
	@NotEmpty
	private String password;
	private String emailId;
	private String phoneNumber;
	
	private String photoUrl;
	private String status;
	private String role;

}
