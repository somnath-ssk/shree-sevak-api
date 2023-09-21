package shreesevak.api.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="schedula")
@NoArgsConstructor
@Getter
@Setter
public class Schedular {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleId;
	
	@OneToOne
	@JoinColumn(name="locationId")
	private Location location1;
	@OneToOne
	@JoinColumn(name="baithakId")
	private Baithak baithak1;
	@OneToMany
	private List<Member>members=new ArrayList<>();
	 
	
	
	public void addBaithak(Baithak baithak1) {
		this.baithak1=baithak1;
		
	}
	public void addMembers(Member  members) {
		this.members.add(members);
		
	}
}
