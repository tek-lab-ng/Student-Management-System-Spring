package Universities.DTO;

import Universities.DTO.ProgramResponse;

import java.util.List;

public class StudentResponse {
    private Long id;
    private String name;
    private int age;
    private String email;
    private Long libraryCardNumber;
    private String course;
    private int grade;
    private Long departmentId;
    private List<ProgramResponse> programs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public void setLibraryCardNumber(Long libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
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

    public List<ProgramResponse> getPrograms() {
        return programs;
    }

    public void setPrograms(List<ProgramResponse> programs) {
        this.programs = programs;
    }
}
