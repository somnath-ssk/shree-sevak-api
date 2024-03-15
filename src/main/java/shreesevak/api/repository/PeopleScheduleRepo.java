package shreesevak.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shreesevak.api.model.PeopleSchedule;

public interface PeopleScheduleRepo extends JpaRepository<PeopleSchedule,Integer> {
	@Query("SELECT e FROM PeopleSchedule e WHERE e.date= :dateParam AND e.baithak.baithakId= :baithakIdParam AND e.baithak.location.locationId= :locationIdParam")
	PeopleSchedule findByDateAndLocationbaithak(@Param("dateParam") String date,
	@Param("baithakIdParam") Integer bithakId,@Param("locationIdParam") Integer locationId);

	@Query("SELECT s FROM PeopleSchedule s WHERE s.date LIKE CONCAT('%', :dynamicMonth, '% ', :dynamicYear) AND s.baithak.baithakId= :baithakIdParam" )
	List<PeopleSchedule> findByMonthAndYearAndBaithak(@Param("dynamicMonth") String dynamicMonth,@Param("dynamicYear") String dynamicYear,@Param("baithakIdParam") Integer baithakId );
}
