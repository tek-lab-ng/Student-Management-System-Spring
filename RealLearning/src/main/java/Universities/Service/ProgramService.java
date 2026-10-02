package Universities.Service;

import Universities.DTO.ProgramRequest;
import Universities.DTO.ProgramResponse;
import Universities.Entity.Program;
import Universities.Mapper.ProgramMapper;
import Universities.Repository.ProgramRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository){
        this.programRepository = programRepository;
    }

    public ProgramResponse createProgram(ProgramRequest programRequest){
        Program program = ProgramMapper.toProgram(programRequest);
        program = programRepository.save(program);
        return ProgramMapper.toProgramResponse(program);
    }
}
