package Universities.Repository;

import Universities.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("Select d from Department d Where Exists (Select s From Student s Where s.department = d)")
    List<Department> getAllDepartmentWithEachHasAStudent();

    @Query("Select d From Department d Where Not Exists (Select s From Student s Where s.department = d)")
    List<Department> getAllDepartmentWithEachHasNoStudent();



}
