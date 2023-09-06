package shreesevak.api.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaithakDto {
 
	   private int bithakId;
		private String baithakType;
		private String dayOfWeek;
		private String fromTime;
		private String status;
		private String toTime;
  //    private String locationId; relation ship
	
}
