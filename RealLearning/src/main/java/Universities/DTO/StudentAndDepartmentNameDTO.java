package Universities.DTO;

public class StudentAndDepartmentNameDTO {
    private String studentName;
    private String departmentName;


    public StudentAndDepartmentNameDTO(String studentName, String departmentName) {
        this.studentName = studentName;
        this.departmentName = departmentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
