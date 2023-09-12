package shreesevak.api.payloads;


import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Location;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberDto {
	     
	    private int memberId;
	    @NotEmpty
		private String firstName;
	    private String middleName;
	    @NotEmpty
	    @NotNull
		private String lastName;
		@NotEmpty
		private String email;
		
		@NotEmpty
		private String hajeriNo;
		@NotEmpty
		private String addharNumber;
		@NotEmpty
		private String Education;
		private String add1;
		private String add2;
		private String add3;
		private String add4;
		@NotEmpty
		private String city;
		@NotEmpty
		private String country;
		@Size(min=4,message = "information shuld not empty")
		private String additionalInfo;
		private String division;
		@NotEmpty
		private String DOB;
		@NotEmpty
		private String gender;
		private String googleMapLink;
		private String initial;
		@NotEmpty
	private String languagesRead;
		@NotEmpty
	private String languagesSpeak;
		@NotEmpty
	private String languagesWrite;
		
	private String longitude;
	private String latitude;
	@NotEmpty
	private String mobile;
	@NotEmpty
	private String occupation;
	@NotEmpty
	private String ownBaithakDay;
	@NotEmpty
	private String panNo;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String photoBase64;
	@NotEmpty
	private String pincode;
	@NotEmpty
	private String state ;
	@NotEmpty
	private String status;
	@NotEmpty
	private String vehicleDetails;
	private String vehicleType;
	private String weeklyOffs;
	
	private Location location;
	private List<Baithak>baithaks=new ArrayList<>();
}
