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
public class CityDto {
    private Long id;
    private String number;
    private String cityName;
    private List<DivisionDto> divisions;

}
