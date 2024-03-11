package shreesevak.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shreesevak.api.model.Baithak2;
import shreesevak.api.model.Scheduler;
import shreesevak.api.payloads.Baithak2Dto;

import java.util.List;

public interface Baithak2Repo extends JpaRepository<Baithak2, Integer>{
Baithak2 findByBaithakId(int baithakId);

@Query("SELECT b FROM Baithak2 b WHERE b.location.locationId= :locationId  AND b.baithakType= :baithakType AND b.dayOfWeek.id= :day AND fromTime= :fromTime")
Baithak2 findByBaithak2ByLocTypeDayTime(@Param("locationId") Integer locationId,@Param("baithakType") String baithakType,@Param("day") Integer day, @Param("fromTime") String fromTime );

//   Baithak2 findByDayOfWeekAndFromTimeAndBaithakType(String day,String time,String baithakType);


@Query("SELECT b FROM Baithak2 b WHERE b.location.locationId= :locationId")
    List<Baithak2> findByLocationId(@Param("locationId") Integer locationId);
}

