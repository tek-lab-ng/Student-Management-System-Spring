package Universities.DTO;

public class CourseStudentCountDTO {
    private String course;
    private Long studentCount;


    public CourseStudentCountDTO(String course, Long studentCount) {
        this.course = course;
        this.studentCount = studentCount;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }
}
