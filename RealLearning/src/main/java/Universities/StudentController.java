package Universities;

import Universities.DTO.StudentRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudent(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentByID(@PathVariable Long id){
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        } else {
           return ResponseEntity.status(HttpStatus.OK).body(student);

        }
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody StudentRequest studentRequest){
        studentService.addStudent(studentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student " + studentRequest.getName() + "   successfully added");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody  @Valid StudentGradeRequest stg){
         Optional<Student> student = studentService.updateStudent(id, stg.getGrade());
         if(student.isPresent())
             return ResponseEntity.ok(student);
         else
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
       studentService.deleteStudent(id);
//       if(value) {
//           return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//       } else {
//           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
//       }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{pathid}")
    public ResponseEntity<?> updateStudentProfile(@RequestBody @Valid Student student, @PathVariable Long pathid){
        Student st = studentService.updateStudentProfile(student, pathid);
        if(st != null)
            return ResponseEntity.status(HttpStatus.OK).body(new StudentUpdateProfile(st, "Student profile successfully updated"));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with that id not found");
    }

}
