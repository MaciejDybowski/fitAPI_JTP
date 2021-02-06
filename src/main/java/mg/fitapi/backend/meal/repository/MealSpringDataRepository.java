package mg.fitapi.backend.meal.repository;

import mg.fitapi.backend.meal.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealSpringDataRepository extends JpaRepository<Meal, Long> {

}
