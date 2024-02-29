package shreesevak.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="baithak2")
@ToString
public class Baithak2 {
	@Id
	private String bithakId;
	private String baithakType;
	private String dayOfWeek;
//	private String date;
	private String fromTime;
	private String status;
	private String toTime;
	

	@OneToOne
	@JoinColumn(name="location")
	private Location location;
	

}
