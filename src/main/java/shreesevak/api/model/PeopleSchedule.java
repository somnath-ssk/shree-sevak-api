package shreesevak.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PeopleSchedule {
	
@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int scheduleId;
private String date;
@OneToOne(fetch = FetchType.LAZY)
private Baithak2 baithak;
@OneToOne(fetch = FetchType.LAZY)
private Member member;
}
