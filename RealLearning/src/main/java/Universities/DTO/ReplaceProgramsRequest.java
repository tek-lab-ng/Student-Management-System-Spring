package Universities.DTO;

import java.util.List;

public class ReplaceProgramsRequest {
    private List<Long> programIds;

    public List<Long> getProgramIds() {
        return programIds;
    }

    public void setProgramIds(List<Long> programIds) {
        this.programIds = programIds;
    }
}
