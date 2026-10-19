package Universities.Repository;

import Universities.DTO.CourseStudentCountDTO;
import Universities.Entity.Department;
import Universities.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query("Select Avg(s.grade) from Student s")
    Double allStudentAverage();
    @Query("Select Avg(s.grade) from Student s where s.course = :course")
    Double allStudentAverageByCourse(@Param("course") String course);
    @Query("Select Count(s) from Student s Where s.course = :course")
    Long numberOfStudentOnACourse(@Param("course") String course);
    @Query("Select new Universities.DTO.CourseStudentCountDTO(s.course, Count(s)) From Student s Group By s.course")
    List<CourseStudentCountDTO> numberOfStudentPerCourse ();

    @Query("Select new Universities.DTO.CourseStudentCountDTO(s.course, Count(s)) from Student s Group By s.course Having Count(s) > :value ")
    List<CourseStudentCountDTO> numberOfStudentPerCourse(@Param("value") Long value);

    @Query("Select new Universities.DTO.CourseStudentCountDTO(s.course, Count(s)) from Student s Group By s.course Order By Count(s) Desc")
    List<CourseStudentCountDTO> numberOfStudentPerCourseOrder();

    @Query("Select s from Student s Left Join s.department d")
    List<Student> studentDepartmentRelationship(@Param("departmentName") String departmentName);
}
