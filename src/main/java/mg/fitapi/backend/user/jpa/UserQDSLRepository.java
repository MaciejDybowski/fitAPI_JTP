package mg.fitapi.backend.user.jpa;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mg.fitapi.backend.user.model.QUser;
import mg.fitapi.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserQDSLRepository {


  @PersistenceContext
  private EntityManager entityManager;

  public List<User> findAll() {
    JPAQuery<User> q = new JPAQueryFactory(entityManager)
            .selectFrom(QUser.user).fetchAll();

    return q.fetch();
  }

  public Optional<User> findByLogin(String login) {
    JPAQuery<User> q = new JPAQueryFactory(entityManager)
            .selectFrom(QUser.user)
            .where(QUser.user.login.eq(login));

    return Optional.ofNullable(q.fetchOne());
  }
}
