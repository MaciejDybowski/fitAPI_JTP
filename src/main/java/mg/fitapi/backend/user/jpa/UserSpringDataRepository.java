package mg.fitapi.backend.user.jpa;

import mg.fitapi.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSpringDataRepository extends JpaRepository<User, Long> {

}
