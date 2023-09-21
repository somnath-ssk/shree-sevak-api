package shreesevak.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




@NoArgsConstructor
@Entity
@Table(name="locations")
@Getter
@Setter

public class Location {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int locationId;
	private String locationName;
	private String add1;
	private String add2;
	private String division;
	private String state ;
	private String country;
	private String city;
	private String pincode;
	private String phoneNumber;
	

	private String longitude;
	private String latitude;
	private String status;

	
	
//	@ManyToMany(mappedBy = "locations")
//	private List<User>users=new ArrayList<>();

//	@OneToMany(mappedBy = "location",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    private List<Baithak> baithak=new ArrayList<>();
	
//	@JsonManagedReference
//	@OneToMany(mappedBy = "location",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	
//private List<Member>membersLoc=new ArrayList<>();

	

	
}
