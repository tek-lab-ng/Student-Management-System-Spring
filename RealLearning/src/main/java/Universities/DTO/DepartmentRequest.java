package Universities.DTO;

import jakarta.validation.constraints.NotBlank;

public class DepartmentRequest {
    @NotBlank(message= "Department name can not be blank")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
