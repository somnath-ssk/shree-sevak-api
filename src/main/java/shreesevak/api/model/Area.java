package shreesevak.api.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String city;
	private String division;
	private String state;
	private String country;
	
	

}
