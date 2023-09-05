package shreesevak.api.model;

import java.util.Date;

import org.springframework.stereotype.Service;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



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
	private String addharNumber;
	private String Education;
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
private String languagesRead;
private String languagesSpeak;
private String languagesWrite;
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
private String vehicleDetails;
private String vehicleType;
private String weeklyOffs;

//private String roles;
//private string bithakId;


	
	
	
}
