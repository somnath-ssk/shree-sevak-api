package shreesevak.api.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String name;
	private String emailId;
	private String phoneNumber;
	private String providerId;
	private String photoUrl;
	private String status;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="users_roles",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
	private List<Role>roles=new ArrayList<>();

  


//    @JoinColumn(name="role_Id")
//    @OneToOne
//    private Role role;

	
	
	

}
