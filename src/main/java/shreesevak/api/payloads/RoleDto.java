package shreesevak.api.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shreesevak.api.model.Location;
import shreesevak.api.model.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDto {
	
	private int roleId;
	private String roleCode;
	private String roleName;
	private String status;
	
	
 
   
	
}
