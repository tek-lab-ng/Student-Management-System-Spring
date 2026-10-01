package Universities.Service;


import Universities.DTO.DepartmentRequest;
import Universities.DTO.DepartmentResponse;
import Universities.DTO.StudentResponse;
import Universities.Entity.Department;
import Universities.Entity.Student;
import Universities.Mapper.DepartmentMapper;
import Universities.Mapper.StudentMapper;
import Universities.Repository.DepartmentRepository;
import Universities.Repository.StudentJpaRepository;
import org.springframework.stereotype.Service;

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

    public List<StudentResponse> getStudentsByDepartment(DepartmentRequest departmentRequest){
        List<Student> listOfStudent = studentJpaRepository.findByDepartmentName(departmentRequest.getName());
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

}
