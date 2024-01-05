package shreesevak.api.model;



 
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name="Weekly Off")
public class WeeklyOff {
	
	@Id
	private int id;
	private String day;
 
	@ManyToOne
	@JoinColumn(name="memberId")
	private Member member;
}
 