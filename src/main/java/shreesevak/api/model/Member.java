package shreesevak.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Generated;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="member")

public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;
	private String firstName;
    private String middleName;
	private String lastName;
	private String email;
	private String hajeriNo;
	private String hajeriNoDetails;
	private String addharNumber;
	private String Education;
	private String role;
	private String add1;
	private String add2;
	private String add3;
	private String add4;
	private String city;
	private String country;
	private String additionalInfo;
	private String division;
	private String DOB;
	private String gender;
	private String googleMapLink;
	private String initial;
	private boolean eligibleForChild;
	private boolean eligibleForGents;
	private boolean eligibleForLadies;
	private boolean eligibleForNone;
private boolean marathiSpeak;
private boolean marathiWrite;
private boolean marathiRead;
private boolean hindiSpeak;
private boolean hindiWrite;
private boolean hindiRead;
private boolean englishSpeak;
private boolean englishWrite;
private boolean englishRead;
private String longitude;
private String latitude;
private String mobile;
private String occupation;
private String ownBaithakDay;
private String panNo;
private String phone;
private String photoBase64;
private String pincode;
private String state ;
private String status;
//private String[] weeklyOffs;
private String area;
private boolean noVehical;
private boolean twoWheeler;
private boolean fourWheeler;
private String twoWheelerDetail;
private String fourWheelerDetail;

@JsonManagedReference
@OneToMany( cascade = CascadeType.ALL)
@JoinTable(name="member_weeklyOff",joinColumns=@JoinColumn(name="memberId"),inverseJoinColumns=@JoinColumn(name="weekId"))
private List<WeeklyOff> weeklyOffs =new ArrayList<>();



//private String roles;
//private string bithakId;


@ManyToOne(targetEntity = Baithak.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
@JoinColumn(name = "baithak_id")
private Baithak baithak;

//@JsonBackReference
//@ManyToOne()
//@JoinTable(name="member_location",joinColumns=@JoinColumn(name="memberId"),inverseJoinColumns=@JoinColumn(name="locationId"))
//private Location location;

}