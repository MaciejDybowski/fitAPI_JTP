package mg.fitapi.backend.meal.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import mg.fitapi.backend.day.model.QDay;
import mg.fitapi.backend.meal.model.Meal;
import mg.fitapi.backend.meal.model.QMeal;
import mg.fitapi.backend.user.model.QUser;
import mg.fitapi.backend.user.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class MealRepositroy {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Meal> findAllByUserAndDate(User user, LocalDate localDate) {
    JPAQuery<Meal> q = new JPAQueryFactory(entityManager)
            .selectFrom(QMeal.meal)
            .leftJoin(QMeal.meal.day).fetchJoin();


    return q.where(QMeal.meal.day.date.eq(localDate).and(QMeal.meal.day.user.id.eq(user.getId()))).fetch();
  }
}
