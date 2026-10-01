package Universities.Mapper;

import Universities.DTO.ProgramRequest;
import Universities.DTO.ProgramResponse;
import Universities.Entity.Program;

public final class ProgramMapper {

    public static Program toProgram(ProgramRequest programRequest){
        Program program = new Program();
        program.setName(programRequest.getName());
        return program;
    }

    public static ProgramResponse toProgramResponse(Program program){
        ProgramResponse programResponse = new ProgramResponse();
        programResponse.setName(program.getName());
        programResponse.setId(program.getId());

        return programResponse;
    }
}
