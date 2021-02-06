package mg.fitapi.backend.day.repository;

import mg.fitapi.backend.day.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaySpringDataRepository extends JpaRepository<Day, Long> {
}
