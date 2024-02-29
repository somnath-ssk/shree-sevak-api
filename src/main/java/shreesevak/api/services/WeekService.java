package shreesevak.api.services;

import java.util.List;

import shreesevak.api.model.WeeklyOff;
import shreesevak.api.payloads.WeeklyOffDto;

public interface WeekService {
  List<WeeklyOff> getAllDays();
}
