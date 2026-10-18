package Universities.Repository;

import Universities.Entity.Department;
import Universities.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    Page<Student> findByCourse(String course, Pageable pageable);
    List<Student> findByGradeGreaterThanEqual(int minimumGrade );
    Student findTopByOrderByGradeDesc();
    Page<Student> findByDepartmentName(String name, Pageable pageable);
    Page<Student>  findByNameAndDepartment(String name, Department department, Pageable pageable);
    Page<Student>  findByName(String name, Pageable pageable);
    Page<Student>  findByDepartment(Department department, Pageable pageable);
    Page<Student> findByGradeBetween (Integer grade1, Integer grade2, Pageable pageable);
    Page<Student> findByGradeGreaterThanEqual(Integer grade, Pageable pageable);
    Page<Student> findByGradeLessThanEqual(Integer grade, Pageable pageable);
    Page<Student> findByGradeIn(List<Integer> listOfGrade, Pageable pageable);
    Page<Student> findByGradeGreaterThanEqualAndAgeLessThanEqual(Integer grade, Integer age, Pageable pageable);
    Page<Student> findByGradeGreaterThanEqualOrAgeLessThanEqual(Integer grade, Integer age, Pageable pageable);
    List<Student> findTop3ByOrderByGradeDesc();
    List<Student> findTop3ByOrderByGradeAsc();
    Student findFirstByOrderByGradeDesc();


}
