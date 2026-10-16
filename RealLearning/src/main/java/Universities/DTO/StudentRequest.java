package Universities.DTO;

import jakarta.validation.constraints.*;

public class StudentRequest {
    @NotBlank
    @Size(min = 4)
    @Pattern(regexp = "[A-Za-z]+")
    private String name;

    @Min(16)
    @Max(50)
    @NotNull
    private Integer age;

    @Email
    @NotBlank
    private String email;

    @NotBlank()
    @Size(min = 5)
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$")
    private String course;

    @Min(0)
    @Max(100)
    private Integer grade;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

}
