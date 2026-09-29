package Universities.DTO;

import jakarta.validation.constraints.*;

public class StudentRequest {
    @NotBlank()
    @Size(min = 4)
    @Pattern(regexp = "[A-Za-z]+")
    private String name;

    @Min(16)
    @Max(50)
    private int age;

    @Email()
    private String email;

    @NotBlank()
    @Size(min = 5)
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$")
    private String course;

    @Min(0)
    @Max(100)
    private int grade;

    @NotNull
    private Long departmentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }


}
