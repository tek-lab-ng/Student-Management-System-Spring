package Universities;

import Universities.DTO.StudentResponse;

public class StudentUpdateProfile {
    private StudentResponse studentResponse;
    private String message;

    public StudentUpdateProfile(StudentResponse student, String message){
        this.studentResponse = student;
        this.message = message;
    }

    public StudentResponse getStudent() {
        return studentResponse;
    }

    public void setStudent(StudentResponse student) {
        this.studentResponse = student;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
