package Universities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> getStudentByID(@PathVariable int id){
        Student student = studentService.getStudentById(id);
        if(student == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        } else {
           return ResponseEntity.status(HttpStatus.OK).body(student);

        }
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student " + student.getName() + "   successfully added");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody int grade){
         Student student = studentService.updateStudent(id, grade);
         if(student != null)
             return ResponseEntity.ok(student);
         else
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id){
       boolean value =  studentService.deleteStudent(id);
       if(value) {
           return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
       }

    }

    @PutMapping("/{pathid}")
    public ResponseEntity<?> updateStudentProfile(@RequestBody Student student, @PathVariable int pathid){
        Student st = studentService.updateStudentProfile(student, pathid);
        if(st != null)
            return ResponseEntity.status(HttpStatus.OK).body(new StudentUpdateProfile(st, "Student profile successfully updated"));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with that id not found");
    }

}
