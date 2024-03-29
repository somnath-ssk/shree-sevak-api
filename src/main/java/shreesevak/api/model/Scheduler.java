package shreesevak.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shreesevak.api.payloads.LocationDto;


@AllArgsConstructor
@Entity
@Table(name="scheduler")
@NoArgsConstructor
@Getter
@Setter
public class Scheduler {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleId;
	
//	 @JsonBackReference
	@OneToOne
	@JoinColumn(name="location")
	private Location location;
	@OneToOne
	@JoinColumn(name="baithak")
	private Baithak baithak;
	
//	@JsonBackReference
	@JoinTable(name="schedule_members",joinColumns = @JoinColumn(name="schedule_id"),inverseJoinColumns =@JoinColumn(name="memberId"))
	@ManyToMany
	private List<Member>members=new ArrayList<>();
	private String status;
	
	private String date;
	
	
//	public void addBaithak(Baithak baithak1) {
//		this.baithak1=baithak1;
//		
//	}
//	public void addMembers(Member  members) {
//		this.members.add(members);
//		
//	}
}
