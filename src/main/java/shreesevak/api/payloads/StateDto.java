package shreesevak.api.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StateDto {
    private Long id;
	private String stateName;
    private List<CityDto> cities;

}
