package Universities.Service;


import Universities.DTO.DepartmentRequest;
import Universities.DTO.DepartmentResponse;
import Universities.DTO.StudentResponse;
import Universities.Entity.Department;
import Universities.Entity.Student;
import Universities.Exceptions.DepartmentNotFoundException;
import Universities.Exceptions.ResourceNotFoundException;
import Universities.Mapper.DepartmentMapper;
import Universities.Mapper.StudentMapper;
import Universities.Repository.DepartmentRepository;
import Universities.Repository.StudentJpaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final StudentJpaRepository studentJpaRepository;

    public DepartmentService(DepartmentRepository departmentRepository, StudentJpaRepository studentJpaRepository){
        this.departmentRepository = departmentRepository;
        this.studentJpaRepository = studentJpaRepository;
    }

    public DepartmentResponse createDepartment(DepartmentRequest request){
        Department department = DepartmentMapper.toDepartment(request);
        department = departmentRepository.save(department);
        return DepartmentMapper.toDepartmentResponse(department);
    }

    public List<StudentResponse> getStudentsByDepartment(String departmentName){
        List<Student> listOfStudent = studentJpaRepository.findByDepartmentName(departmentName);
        if(listOfStudent.isEmpty())
            return List.of();
        else
            return listOfStudent.stream().map(StudentMapper::toStudentResponse).toList();

    }

    public DepartmentResponse getDepartmentById(Long id){
        Department department = departmentRepository.findById(id).orElseThrow();
        return DepartmentMapper.toDepartmentResponse(department);
    }

    public List<DepartmentResponse> getAllDepartments(){
        List<Department> departmentList = departmentRepository.findAll();
        if(departmentList.isEmpty())
            return List.of();
        else
            return departmentList.stream().map(DepartmentMapper::toDepartmentResponse).toList();
    }

    public List<StudentResponse> getStudentByDepartment(Long departmentId){
        Department department = departmentRepository.findById(departmentId).orElseThrow(()-> new DepartmentNotFoundException("Department with ID" + departmentId + "can not be found"));
        List<Student> listOfStudent = department.getStudents();
        if(listOfStudent.isEmpty())
            return List.of();
        else
            return listOfStudent.stream().map(StudentMapper::toStudentResponse).toList();
    }

    @Transactional
    public DepartmentResponse deleteDepartment(Long departmentId){
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException("Department with id " + departmentId + " not found"));
        List<Student> students = department.getStudents();
        students.forEach(student -> student.setDepartment(null));
        departmentRepository.delete(department);
        return DepartmentMapper.toDepartmentResponse(department);
    }

}
