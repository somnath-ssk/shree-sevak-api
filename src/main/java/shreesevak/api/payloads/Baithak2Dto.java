package shreesevak.api.payloads;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shreesevak.api.model.Location;
import shreesevak.api.model.WeeklyOff;


@NoArgsConstructor
@AllArgsConstructor
@Data
//@Table(name="baithak")
@ToString
public class Baithak2Dto {
	
	private int baithakId;
	private String baithakType;
	private WeeklyOff dayOfWeek;
	private String fromTime;
	private String status;
	private String toTime;
	private LocationDto location;
	
}
