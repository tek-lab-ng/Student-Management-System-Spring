package Universities.Mapper;

import Universities.DTO.ProgramResponse;
import Universities.DTO.StudentRequest;
import Universities.DTO.StudentResponse;
import Universities.Entity.Student;

import java.util.List;

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
        Long departmentId = student.getDepartment() != null ? student.getDepartment().getId() : null;
        List<ProgramResponse> programResponses = student.getPrograms().stream().map(ProgramMapper::toProgramResponse).toList();
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setAge(student.getAge());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setCourse(student.getCourse());
        studentResponse.setLibraryCardNumber(student.getLibraryCardNumber());
        studentResponse.setGrade(student.getGrade());
        studentResponse.setDepartmentId(departmentId);
        studentResponse.setPrograms(programResponses);

        return studentResponse;

    }
}
