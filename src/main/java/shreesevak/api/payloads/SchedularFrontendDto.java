package shreesevak.api.payloads;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SchedularFrontendDto {

	private int locationId;
	private int baithakId;
	private int vachanGhenara;
	private int hajeriGhenara;

	private String status;

	private String date;

}
