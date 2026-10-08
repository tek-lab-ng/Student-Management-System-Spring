package Universities.Repository;

import Universities.DTO.StudentResponse;
import Universities.Entity.Department;
import Universities.Entity.Program;
import Universities.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourse(String course);
    List<Student> findByGradeGreaterThanEqual(int minimumGrade );
    Student findTopByOrderByGradeDesc();
    List<Student> findByDepartmentName(String name);
    List<Student>  findByNameAndDepartment(String name, Department department);
    List<Student>  findByName(String name);
    List<Student>  findByDepartment(Department department);

}
