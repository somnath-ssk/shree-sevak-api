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

	private String locationName;
	private String  add1 ;
	private String add2 ;
	
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
	private long  division ;
	private long city ;
	private long  state ;
	private long country; 
    private int area;
	
}
