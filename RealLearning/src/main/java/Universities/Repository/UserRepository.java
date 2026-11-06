package Universities.Repository;

import Universities.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("Select u From User u Where u.username = :username")
//    User findUserByUsername (@Param("username") String username);

        User findByUsername (String username);
}
