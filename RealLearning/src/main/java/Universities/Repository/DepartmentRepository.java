package Universities.Repository;

import Universities.Entity.Department;
import Universities.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {


}
