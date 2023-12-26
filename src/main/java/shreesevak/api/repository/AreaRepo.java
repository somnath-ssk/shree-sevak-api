package shreesevak.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shreesevak.api.model.Area;
import shreesevak.api.model.Baithak;
import java.util.List;


public interface AreaRepo extends JpaRepository<Area,Integer> {
  List<Area> findByStatus(String status);
  Area findByAreaName(String areaName);
}
