package Universities.DTO;

public class DepartmentStudentCountDTO {
    private String departmentName;
    private Long numberOfStudent;

    public DepartmentStudentCountDTO(String departmentName, Long numberOfStudent) {
        this.departmentName = departmentName;
        this.numberOfStudent = numberOfStudent;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(Long numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }
}