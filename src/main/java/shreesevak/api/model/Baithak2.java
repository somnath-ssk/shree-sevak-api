package shreesevak.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="baithak2")
@ToString
public class Baithak2 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int baithakId;
	
	@NotEmpty
	private String baithakType;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="dayOfWeek")
	private WeeklyOff dayOfWeek;
	private String fromTime;
	private boolean status;
	private String toTime;
	

	@JsonManagedReference
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="location")
	private Location location;
	

}
