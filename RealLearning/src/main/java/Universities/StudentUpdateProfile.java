package Universities;

public class StudentUpdateProfile {
    private Student student;
    private String message;

    public StudentUpdateProfile(Student student, String message){
        this.student = student;
        this.message = message;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
