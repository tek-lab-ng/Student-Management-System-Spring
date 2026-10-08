package Universities.DTO;

import jakarta.validation.constraints.*;


public class StudentPatchRequest {

    @Size(min = 4)
    @Pattern(regexp = "[A-Za-z]+")
    private String name;
    @Min(18)
    @Max(50)
    private Integer age;
    @Email(message = "Please provide a valid email address")
    private String email;
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$",
            message = "Course field cannot contain number and special characters")
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
