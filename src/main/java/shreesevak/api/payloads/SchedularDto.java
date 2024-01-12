package shreesevak.api.payloads;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shreesevak.api.model.Baithak;
import shreesevak.api.model.Location;
import shreesevak.api.model.Member;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SchedularDto {

	private int scheduleId;

	private LocationDto location;

	private BaithakDto baithak;
	private List<MemberDto> members = new ArrayList<>();
	private String status;

	private String date;

}
