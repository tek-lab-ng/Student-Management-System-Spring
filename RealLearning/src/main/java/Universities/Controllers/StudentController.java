package Universities.Controllers;

import Universities.DTO.ProgramResponse;
import Universities.DTO.StudentGradeRequest;
import Universities.DTO.StudentRequest;
import Universities.DTO.StudentResponse;
import Universities.Service.StudentService;
import Universities.StudentUpdateProfile;
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
    public List<StudentResponse> getAllStudent(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentByID(@PathVariable Long id){
        StudentResponse studentResponse = studentService.getStudentById(id);
           return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest studentRequest){
       StudentResponse st = studentService.addStudent(studentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(st);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStudentGrade(@PathVariable Long id, @RequestBody  @Valid StudentGradeRequest stg){
         Optional<StudentResponse> student = studentService.updateStudentGrade(id, stg.getGrade());
         if(student.isPresent())
             return ResponseEntity.ok(student.get());
         else
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        boolean value = studentService.deleteStudent(id);
       if(value) {
           return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
       }
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{pathid}")
    public ResponseEntity<?> updateStudentProfile(@RequestBody @Valid StudentRequest studentRequest, @PathVariable Long pathid){
        StudentResponse st = studentService.updateStudentProfile(studentRequest, pathid);
        if(st != null)
            return ResponseEntity.status(HttpStatus.OK).body(new StudentUpdateProfile(st, "Student profile successfully updated"));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with that id not found");
    }

    @PostMapping("/{studentId}/programs/{programId}")
    public ResponseEntity<StudentResponse> addProgramToStudent(@PathVariable Long studentId, @PathVariable Long programId){
        StudentResponse studentResponse = studentService.addProgramToStudent(studentId, programId);
        return ResponseEntity.ok(studentResponse);
    }

    @GetMapping("/{studentId}/programs")
    public ResponseEntity<List<ProgramResponse>> getProgramsByStudent(@PathVariable Long studentId){
        List<ProgramResponse> listOfStudProg = studentService.getProgramsByStudent(studentId);
        return ResponseEntity.ok(listOfStudProg);
    }

    @DeleteMapping("/{studentId}/programs/{programId}")
    public ResponseEntity<List<ProgramResponse>> removeProgramFromStudent(@PathVariable Long studentId, @PathVariable Long programId){
       List<ProgramResponse> programResponses = studentService.removeProgramFromStudent(studentId, programId);
        return ResponseEntity.ok(programResponses);
    }
}
