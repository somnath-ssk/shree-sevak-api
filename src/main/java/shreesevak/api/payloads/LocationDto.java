package shreesevak.api.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shreesevak.api.model.User;

@Getter
@Setter
@NoArgsConstructor
public class LocationDto {
	
	private int locationId;
	private String address;
	private String division;
	private String state ;
	private String country;
	private String city;
	private String status;

	
}
