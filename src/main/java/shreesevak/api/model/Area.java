package shreesevak.api.model;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="Area")
@Entity
public class Area {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int areaId;
  private String areaName;
  private String  contactInitial;
  private String  contactName;
  private String  contactOccupation;
private String  contactPhone1;
private String  contactPhone2;
private String  contactEmail ;

@OneToOne(fetch = FetchType.EAGER)

	private City city;
@OneToOne(fetch = FetchType.EAGER)

	private Division division;
@OneToOne(fetch = FetchType.EAGER)

	private State state;
@OneToOne(fetch = FetchType.EAGER)

private Country country;
	private int status;
	private int maleCount;
	private int femaleCount;
	
	

}
