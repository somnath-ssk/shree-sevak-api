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
//	private String locationName;
//	private String add1;
//	private String add2;
//	private String division;
//	private String state ;
//	private String country;
//	private String city;
//	private String pincode;
//	private String phoneNumber;
//	private String longitude;
//	private String latitude;
//	private String status;

	private String locationName;
	private String  add1 ;
	private String add2 ;
	private String division ;
	private String city ;
	private String  state ;
	private String country; 
   private String  pincode ;
   private String area;
	private String  latitude ;
	private String  longitude ;
    private String  status ;
	private String  googleMapLink;
	private String  add3;
	private String  add4;
	private String  additionalInfo;
	private String  contact1Email ;
	private String  contact1Initial;
	private String  contact1Name;
	private String  contact1Occupation;
	private String  contact1Phone1;
	private String  contact1Phone2;
	private String  contact2Email;
	private String  contact2Initial;
	private String  contact2Name;
	
	private String  contact2Phone1;
	private String  contact2Phone2;
	private boolean mixedGenderAllow;

	
//	@ManyToMany(mappedBy = "locations")
//	private List<User>users=new ArrayList<>();

//	@OneToMany(mappedBy = "location",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    private List<Baithak> baithak=new ArrayList<>();
	
//	@JsonManagedReference
//	@OneToMany(mappedBy = "location",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	
//private List<Member>membersLoc=new ArrayList<>();

	

	
}
