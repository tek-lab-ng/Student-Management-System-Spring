package Universities.Mapper;

import Universities.DTO.StudentRequest;
import Universities.DTO.StudentResponse;
import Universities.Entity.Student;

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

    public static StudentResponse toStudentResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setAge(student.getAge());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setCourse(student.getCourse());
        studentResponse.setLibraryCardNumber(student.getLibraryCardNumber());
        studentResponse.setGrade(student.getGrade());

        return studentResponse;

    }
}
