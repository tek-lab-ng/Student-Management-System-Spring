package Universities.Controllers;

import Universities.DTO.ProgramRequest;
import Universities.DTO.ProgramResponse;
import Universities.Service.ProgramService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
