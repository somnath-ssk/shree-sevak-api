package shreesevak.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="baithak")
@ToString
public class Baithak {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int bithakId;
	private String baithakType;
	private String dayOfWeek;
//	private String date;
	private String fromTime;
	private String status;
	private String toTime;
	

	@OneToOne
	@JoinColumn(name="location")
	private Location location;
	
//
//	@OneToMany(mappedBy ="baithak",cascade=CascadeType.ALL)
//	List<Member>members=new ArrayList<>();
	
	
	
	
	
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinTable(name="location_baithak",joinColumns=@JoinColumn(name="locationId"),inverseJoinColumns=@JoinColumn(name="bithakId"))
//private Location location;
////	
//	@ManyToMany()
//	@JoinTable(name="member_baithak",joinColumns=@JoinColumn(name="member_id"),inverseJoinColumns=@JoinColumn(name="baithak_id"))
//	private List<Member> member=new ArrayList<>();
//	
	
}
