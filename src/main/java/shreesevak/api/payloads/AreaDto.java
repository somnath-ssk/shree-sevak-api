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
	private CityDto city;
	private DivisionDto division;
	private StateDto state;
	private CountryDto country;
	private int status;
		private int maleCount;
		private int femaleCount;
		

}
