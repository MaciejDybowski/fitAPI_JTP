package mg.fitapi.backend.day.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mg.fitapi.backend.day.model.Day;
import mg.fitapi.backend.day.model.QDay;
import mg.fitapi.backend.user.model.QUser;
import mg.fitapi.backend.user.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class DayQDSLRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public Optional<Day> findByUserAndDate(User user, LocalDate localDate) {
    JPAQuery<Day> q = new JPAQueryFactory(entityManager)
            .selectFrom(QDay.day)
            .leftJoin(QDay.day.user).fetchJoin()
            .where(QDay.day.user.eq(user).and(QDay.day.date.eq(localDate)));

    return Optional.ofNullable(q.fetchOne());
  }

}
