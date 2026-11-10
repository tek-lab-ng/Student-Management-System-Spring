package Universities.Repository;

import Universities.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("Select u From User u Where u.username = :username")
//    User findUserByUsername (@Param("username") String username);

        User findByUsername (String username);
}
