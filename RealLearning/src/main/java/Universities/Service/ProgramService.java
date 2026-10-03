package Universities.Service;

import Universities.DTO.ProgramRequest;
import Universities.DTO.ProgramResponse;
import Universities.DTO.StudentResponse;
import Universities.Entity.Program;
import Universities.Entity.Student;
import Universities.Exceptions.ProgramNotFoundException;
import Universities.Mapper.ProgramMapper;
import Universities.Mapper.StudentMapper;
import Universities.Repository.ProgramRepository;
import Universities.Repository.StudentJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;
    private final StudentJpaRepository studentJpaRepository;

    public ProgramService(ProgramRepository programRepository, StudentJpaRepository studentJpaRepository){
        this.programRepository = programRepository;
        this.studentJpaRepository = studentJpaRepository;
    }

    public ProgramResponse createProgram(ProgramRequest programRequest){
        Program program = ProgramMapper.toProgram(programRequest);
        program = programRepository.save(program);
        return ProgramMapper.toProgramResponse(program);
    }

    public ProgramResponse getProgram(Long programID){
        Program program = programRepository.findById(programID).orElseThrow(()-> new ProgramNotFoundException("Program with ID  " + programID + " not found"));
        return ProgramMapper.toProgramResponse(program);
    }

    public List<StudentResponse> getStudentsByProgram(Long programId){
        Program program = programRepository.findById(programId).orElseThrow();
        List<Student> listOfStudProg = program.getStudentList();
        return listOfStudProg.stream().map(StudentMapper::toStudentResponse).toList();

    }
}
