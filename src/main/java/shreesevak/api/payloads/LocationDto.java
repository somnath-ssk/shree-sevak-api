package shreesevak.api.payloads;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Member;
import shreesevak.api.model.User;

@Getter
@Setter
@NoArgsConstructor
public class LocationDto {
	
	private int locationId;
	private String locationName;
	private String add1;
	private String add2;
	private String division;
	private String state ;
	private String country;
	private String city;
	
	
	@NotEmpty(message = "Pincode  is required")
	@Pattern(regexp="^[0-4]{4}$")
	private String pincode;
	
@NotEmpty(message = "Phone number is required")
@Pattern(regexp="^[0-9]{10}$")
	private String phoneNumber;
	

	private String longitude;
	private String latitude;
	private String status;

//	private List<UserDto>users=new ArrayList<>();
//	  private List<BaithakDto> baithak=new ArrayList<>();
//	
//	private List<Baithak> baithak=new ArrayList<>();
//	private List<Member>membersLoc=new ArrayList<>();
	
}
