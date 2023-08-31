package shreesevak.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@NoArgsConstructor
@Entity
@Table(name="locations")
@Getter
@Setter
public class Location {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int locationId;
	private String address;
	private String division;
	private String state ;
	private String country;
	private String city;
	private String status;
	
	



	


	
	
}
