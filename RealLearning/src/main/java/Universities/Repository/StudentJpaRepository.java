package Universities.Repository;

import Universities.DTO.CourseStudentCountDTO;
import Universities.DTO.DepartmentStudentCountDTO;
import Universities.DTO.StudentAndDepartmentNameDTO;
import Universities.Entity.Department;
import Universities.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    @Query("Select s from Student s Left Join s.department d where d.name = :departmentName")
    List<Student> studentDepartmentRelationship(@Param("departmentName") String departmentName);

    @Query("Select new Universities.DTO.StudentAndDepartmentNameDTO(s.name, d.name)  from Student s Left Join s.department d")
     List<StudentAndDepartmentNameDTO> studentAndDepartmentName();
    @Query("Select new Universities.DTO.DepartmentStudentCountDTO(d.name, Count(s) ) from Student s Left Join s.department d Group By d.name")
    List<DepartmentStudentCountDTO> DepartmentAndStudentCount ();

    @Query("Select new Universities.DTO.DepartmentStudentCountDTO(d.name, Count(s)) From Student s Left Join s.department d Group By d.name Having Count(s) >= :value Order By Count(s) Desc")
    List<DepartmentStudentCountDTO> getDepartmentAndNumberOfStudent(@Param("value") Long value);

    //SubQueries
    @Query("Select s from Student s Where s.grade >= (Select Avg(s.grade) from Student s)")
    List<Student> getListOfStudentGradeGreaterThanOrEqualAverage();

    @Query("Select s From Student s Where s.department IN (Select st.department From Student st Where st.grade >= :value) ")
    List<Student> getAllStudentInCourseAndHasGrade(@Param("value") Long value);

    @Query("Select s From Student s Where s.grade > (Select Avg(st) From Student st Where st.department = s.department")
    List<Student> getStudentsWithGradeAboveDepartmentAverage();

    @Query("Select s from Student s Where s.grade > ALL (Select st.grade From Student st Where st.department.name = :department)")
    List<Student> getStudentsWithGradeHigherThanAllMathStudents(@Param("department") String department);

    @Query("Select s from Student s Where s.grade > ANY (Select st.grade From Student st Where st.department.name = :department)")
    List<Student> getStudentsWithGradeHigherThanANYMathStudents(@Param("department") String department);

    @Query("""
              SELECT s.name,
                       s.age,
                       CASE
                           WHEN s.age >= 18 THEN 'Adult'
                           ELSE 'Minor'
                       END
                FROM Student s
  
    """)
    List<Object[]> getStudentAgeCategories();

    @Transactional
    @Modifying
    @Query("""
    UPDATE Student s
    SET s.grade = s.grade + :point
    WHERE s.course = :course
    AND s.grade + :point <= 100
    """)
    int updateStudentGrade(@Param("point") Integer point, @Param("course") String course);

    @Query("SELECT DISTINCT s.course FROM Student s ")
    List<String> getUniqueCourseNames();

    @Query("SELECT s FROM Student s WHERE s.email IS NULL")
    List<Student>  getStudentsWithEmail();

    @Query("Select s From Student s Where s.name Like Concat('%',:value, '%')")
    List<Student> getStudentsWhoseNamesContain(@Param("value") String value);

    @Query("Select s From Student s Where s.grade Between :minGrade And :maxGrade")
    List<Student> getGradeBetween (@Param("minGrade") Integer minGrade, @Param("maxGrade") Integer maxGrade);

    @Query(value = "Select * From student Where grade >= :grade", nativeQuery = true)
    List<Student> getAllStudentOfAGrade (@Param("grade") Integer grade);
}
