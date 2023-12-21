package shreesevak.api.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AreaDto {
	
	 private int areaId;
	  private String areaName;
	  private String  contactInitial;
	  private String  contactName;
	  private String  contactOccupation;
	private String  contactPhone1;
	private String  contactPhone2;
	private String  contactEmail ;
		private String city;
		private String division;
		private String state;
		private String country;
		

}
