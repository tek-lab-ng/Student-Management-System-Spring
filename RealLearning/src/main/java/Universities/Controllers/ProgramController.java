package Universities.Controllers;

import Universities.DTO.ProgramRequest;
import Universities.DTO.ProgramResponse;
import Universities.DTO.StudentResponse;
import Universities.Service.ProgramService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/program")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService){
        this.programService = programService;
    }

    @PostMapping()
    public ResponseEntity<ProgramResponse> createProgram(@RequestBody @Valid ProgramRequest programRequest){
        ProgramResponse programResponse = programService.createProgram(programRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(programResponse);

    }
    @GetMapping("/{programId}")
    public ResponseEntity<ProgramResponse> getProgramByID(@PathVariable Long programId){
        return ResponseEntity.ok(programService.getProgram(programId));
    }

    @GetMapping("/{programId}/students")
    public ResponseEntity<List<StudentResponse>> getStudentsByProgram(@PathVariable Long programId){
        List<StudentResponse> studentResponse = programService.getStudentsByProgram(programId);
        return  ResponseEntity.ok(studentResponse);
    }
}
