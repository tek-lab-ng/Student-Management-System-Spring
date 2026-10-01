package Universities.Service;

import Universities.Repository.ProgramRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository){
        this.programRepository = programRepository;
    }
}
