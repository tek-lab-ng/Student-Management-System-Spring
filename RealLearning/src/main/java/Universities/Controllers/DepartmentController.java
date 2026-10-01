package Universities.Controllers;

import Universities.DTO.DepartmentRequest;
import Universities.DTO.DepartmentResponse;
import Universities.DTO.StudentResponse;
import Universities.Service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping()
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody @Valid DepartmentRequest departmentRequest){
        DepartmentResponse departmentResponse = departmentService.createDepartment(departmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentResponse);

    }

    @GetMapping("{departmentName}/students")
    public ResponseEntity<?> getStudentsByDepartment(@PathVariable @Valid DepartmentRequest departmentRequest){
        List<StudentResponse> departmentResponse = departmentService.getStudentsByDepartment(departmentRequest);
        if(departmentResponse.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(departmentResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id){
        DepartmentResponse departmentResponse = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(departmentResponse);
    }

    @GetMapping
    public ResponseEntity< List<DepartmentResponse>> getAllDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

}
