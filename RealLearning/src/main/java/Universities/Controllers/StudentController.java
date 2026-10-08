package Universities.Controllers;

import Universities.DTO.*;
import Universities.Service.StudentService;
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


    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentByID(@PathVariable Long studentId){
        StudentResponse studentResponse = studentService.getStudentById(studentId);
           return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest studentRequest){
       StudentResponse st = studentService.addStudent(studentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(st);
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
    @PatchMapping("/{studentId}/department/{departmentId}")
    public ResponseEntity<StudentResponse> changeStudentDepartment(@PathVariable Long studentId, @PathVariable Long departmentId){
        StudentResponse studentResponse = studentService.changeStudentDept(studentId, departmentId);
        return ResponseEntity.ok(studentResponse);
    }
    @PatchMapping("/{studentId}/department")
    public ResponseEntity<StudentResponse> removeDepartmentFromStudent(@PathVariable Long studentId){
        StudentResponse studentResponse = studentService.removeDepartmentFromStudent(studentId);
        return ResponseEntity.ok(studentResponse);
    }

    @PatchMapping("/{studentId}/programs")
    public ResponseEntity<List<ProgramResponse>> replaceAllProgStudent(@RequestBody ReplaceProgramsRequest replaceProgramsRequest, @PathVariable Long studentId){
        StudentResponse studentResponse = studentService.replaceProgramsForStudent(replaceProgramsRequest.getProgramIds(), studentId);
        return ResponseEntity.ok(studentResponse.getPrograms());
    }
    @PatchMapping("/{studentId}")
    public ResponseEntity<StudentResponse> updateStudentProfile(@PathVariable Long studentId, @RequestBody @Valid StudentPatchRequest studentPatchRequest){
        StudentResponse studentResponse = studentService.updateStudentProfile(studentId, studentPatchRequest);
        return ResponseEntity.ok(studentResponse);
    }
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents(@RequestParam(required = false) String name, @RequestParam(required = false) Long departmentId){
        List<StudentResponse> studentResponses = studentService.filterStudents(name, departmentId);
        return ResponseEntity.ok(studentResponses);
    }
}
