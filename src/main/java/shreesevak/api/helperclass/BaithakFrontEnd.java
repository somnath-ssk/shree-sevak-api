package shreesevak.api.helperclass;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaithakFrontEnd {

	 private String bithakId;
		private String baithakType;
		private String dayOfWeek;
		private String date;
		private String fromTime;
		private String status;
		private String toTime;
       private int locationId;
}
