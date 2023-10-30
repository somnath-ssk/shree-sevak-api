package shreesevak.api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shreesevak.api.payloads.RoleDto;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String name;
	
	@NotEmpty
	private String password;
	private String emailId;
	private String phoneNumber;
	
	private String photoUrl;
	private String status;
	
	
	
	 @JsonManagedReference
	@ManyToMany()
	@JoinTable(name="users_roles",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
	private List<Role>roles=new ArrayList<>();
//	
//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(name="users_locations",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="location_id"))
//	private List<Location>locations=new ArrayList<>();


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
//	 @Override
//	    public Collection<? extends GrantedAuthority> getAuthorities() {
//	        // Convert user roles to GrantedAuthority
//	        List<GrantedAuthority> authorities = new ArrayList<>();
//	        for (Role role : roles) {
//	            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//	        }
//	        return authorities;
//	    }


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.emailId;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

public void addRole(Role role) {
	this.roles.add(role);
}


  


//    @JoinColumn(name="role_Id")
//    @OneToOne
//    private Role role;

	
	
	

}
