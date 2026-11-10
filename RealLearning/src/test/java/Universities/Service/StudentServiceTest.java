package Universities.Service;

import Universities.DTO.StudentResponse;
import Universities.Entity.Student;
import Universities.Exceptions.StudentNotFoundException;
import Universities.Repository.DepartmentRepository;
import Universities.Repository.ProgramRepository;
import Universities.Repository.StudentJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentJpaRepository studentJpaRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private ProgramRepository programRepository;
    @InjectMocks
    private StudentService studentService;

    @Test
    void testStudentById() {
        Student student = new Student();
        student.setId(1L);
        student.setName("gabriel");
        student.setEmail("email");
        student.setAge(24);
        student.setCourse("Computer Science");
        student.setGrade(90);
        student.setLibraryCardNumber(1002L);


        when(studentJpaRepository.findById(1L)).thenReturn(Optional.of(student));
        StudentResponse result = studentService.getStudentById(1L);

        assertEquals("gabriel", result.getName());
        assertEquals(1L, result.getId());
        assertEquals("Computer Science", result.getCourse());

        verify(studentJpaRepository).findById(1L);
    }

    @Test
    void testStudentByIdWhenStudentDoesNotExist() {
        when(studentJpaRepository.findById(99L))
                .thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class, () -> {studentService.getStudentById(99L);});
    }

    @Test
    void deleteByStudent(){
        Student st = new Student();
        st.setId(1000L);
        st.setName("victor");

//        when(studentJpaRepository.findById(1L)).thenReturn(Optional.of(st));
//        studentService.deleteStudent(1L);
//        verify(studentJpaRepository).delete(st);

        when(studentJpaRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class, ()-> {studentService.deleteStudent(999L);});
        verify(studentJpaRepository, never()).delete(any(Student.class));
    }

}