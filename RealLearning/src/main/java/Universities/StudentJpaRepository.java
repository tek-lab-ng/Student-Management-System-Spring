package Universities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourse(String course);
    List<Student> findByGradeGreaterThanEqual(int minimumGrade );
    Student findTopByOrderByGradeDesc();


}
