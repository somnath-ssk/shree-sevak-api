package shreesevak.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shreesevak.api.model.WeeklyOff;

public interface WeekRepo extends JpaRepository<WeeklyOff, Integer> {

}
