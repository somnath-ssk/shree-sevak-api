package shreesevak.api.helperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shreesevak.api.model.Area;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationFrontEnd {
	private int locationId;
//	private String locationName;
//	private String add1;
//	private String add2;
//	private String division;
//	private String state ;
//	private String country;
//	private String city;
//	@NotEmpty(message = "Pincode  is required")
////	@Pattern(regexp="^[0-4]{4}$")
//	private String pincode;
//	
//@NotEmpty(message = "Phone number is required")
////@Pattern(regexp="^[0-9]{10}$")
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
	private String  contact2Occupation;
	private String  contact2Phone1;
	private String  contact2Phone2;
	private boolean mixedGenderAllow;
    private int area;
	
}
