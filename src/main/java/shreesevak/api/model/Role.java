package shreesevak.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="role")
public class Role {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int roleId;
private String roleCode;
private	String roleName;
private	String status;

//@OneToOne(mappedBy = "roles",cascade = CascadeType.ALL)
//private User user;

}
