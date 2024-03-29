package shreesevak.api.payloads;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shreesevak.api.model.Location;
import shreesevak.api.model.Member;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaithakDto {
 
	   private int bithakId;
		private String baithakType;
		private String dayOfWeek;
		private String date;
		private String fromTime;
		private String status;
		private String toTime;
     private LocationDto location;
		

		
}
