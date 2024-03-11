package shreesevak.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule2 {
	
@Id	
private int scheduleId;
private String date;
@OneToOne
private Baithak2 baithak;
@OneToOne
private Member member;
}
