package shreesevak.api.helperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaFrontEnd {
	 private int areaId;
	  private String areaName;
	  private String  contactInitial;
	  private String  contactName;
	  private String  contactOccupation;
	private String  contactPhone1;
	private String  contactPhone2;
	private String  contactEmail ;
	private long city;
	private long division;
	private long state;
	private int status;
	private long country;
		private int maleCount;
		private int femaleCount;

}
