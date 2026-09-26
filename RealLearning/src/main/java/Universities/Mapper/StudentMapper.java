package Universities.Mapper;

import Universities.DTO.StudentRequest;
import Universities.Student;

public final class StudentMapper {
    public static Student toStudent(StudentRequest request) {
        Student student = new Student();

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setCourse(request.getCourse());
        student.setGrade(request.getGrade());

        return student;
    }
}
