package Universities.Service;


import Universities.DTO.ProgramResponse;
import Universities.DTO.StudentPatchRequest;
import Universities.DTO.StudentRequest;
import Universities.DTO.StudentResponse;
import Universities.Entity.Department;
import Universities.Entity.Program;
import Universities.Entity.Student;
import Universities.Exceptions.DepartmentNotFoundException;
import Universities.Exceptions.ProgramNotFoundException;
import Universities.Exceptions.ResourceNotFoundException;
import Universities.Exceptions.StudentNotFoundException;
import Universities.Mapper.ProgramMapper;
import Universities.Mapper.StudentMapper;
import Universities.Repository.DepartmentRepository;
import Universities.Repository.ProgramRepository;
import Universities.Repository.StudentJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentJpaRepository repository;
    private final DepartmentRepository departmentRepository;
    private final ProgramRepository programRepository;


    public StudentService(StudentJpaRepository studentJpaRepository, DepartmentRepository departmentRepository, ProgramRepository programRepository){
        this.repository =  studentJpaRepository;
        this.departmentRepository = departmentRepository;
        this.programRepository = programRepository;

    }

    public StudentResponse getStudentById(Long studentId){
        Student student = repository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with ID  " + studentId + " not found"));
        return StudentMapper.toStudentResponse(student);
    }

    @Transactional
    public StudentResponse addStudent(StudentRequest studentRequest) {
        Student st = StudentMapper.toStudent(studentRequest);
//        Department department = departmentRepository.findById(studentRequest.getDepartmentId()).orElseThrow();
//        st.setDepartment(department);
        st = repository.save(st);
        st.setLibraryCardNumber(10000 + st.getId());
        return StudentMapper.toStudentResponse(st);
    }


    public void deleteStudent(Long id){
       Student student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id" + id + " can not be found"));
       repository.delete(student);
    }

    public List<StudentResponse> getStudentByCourse(String course, Pageable pageable){
        Page<Student> student = repository.findByCourse(course, pageable );
        return student.stream().map(StudentMapper::toStudentResponse).toList();
    }

    public List<StudentResponse> getStudentsByMinimumGrade(int minimumGrade){
        List<Student> students = repository.findByGradeGreaterThanEqual(minimumGrade);
        return students.stream().map(StudentMapper::toStudentResponse).toList();
    }

    public Student getStudentWithHighestScore(){
//        Student st = null;
//        st = getAllStudents().stream().max(Comparator.comparing(Student::getGrade)).orElse(null);
//        return st;

        return repository.findTopByOrderByGradeDesc();

    }


    @Transactional
    public StudentResponse addProgramToStudent(Long studentId, Long programId){
        Student student = repository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with ID  " + studentId + "not found"));
        Program program = programRepository.findById(programId).orElseThrow(() -> new ProgramNotFoundException("Program with ID  " + programId + " not found"));
        student.getPrograms().add(program);

        return StudentMapper.toStudentResponse(student);
    }

    public List<ProgramResponse> getProgramsByStudent(Long studentId){
        Student student = repository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with ID  " + studentId + "not found"));
        List<Program> programList = student.getPrograms();
        return programList.stream().map(ProgramMapper::toProgramResponse).toList();
    }

    @Transactional
    public List<ProgramResponse> removeProgramFromStudent(Long studentId, Long programId){
        Student student = repository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("student with " + studentId + " can not be found"));
        Program program = programRepository.findById(programId).orElseThrow(()-> new ProgramNotFoundException("Program with id " + programId + " can not be found"));
        List<Program> lisOfProgramByStudent = student.getPrograms();
        boolean removed = lisOfProgramByStudent.remove(program);
        if(!removed)
            throw new ResourceNotFoundException("Student is not enrolled in this program");
        else
             return lisOfProgramByStudent.stream().map(ProgramMapper::toProgramResponse).toList();
    }

    @Transactional
    public StudentResponse changeStudentDept(Long studentId, Long departmentId){
        Student student = repository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("student with " + studentId + " can not be found"));
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department with " + departmentId + " can not be found"));
        student.setDepartment(department);
        return StudentMapper.toStudentResponse(student);
    }

    @Transactional
    public StudentResponse removeDepartmentFromStudent(Long studentId){
        Student student = repository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("student with " + studentId + " can not be found"));
        student.setDepartment(null);
        return StudentMapper.toStudentResponse(student);
    }

    @Transactional
    public StudentResponse replaceProgramsForStudent(List<Long> programId, Long studentId){
        List<Program> programs = programId.stream().map(id -> programRepository.findById(id)
                                .orElseThrow(() -> new ProgramNotFoundException("The program with id " + id + " cannot be found" ))).toList();
        Student student = repository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("student with " + studentId + " can not be found"));
        student.setPrograms(programs);

        return StudentMapper.toStudentResponse(student);

    }
    @Transactional
    public StudentResponse updateStudentProfile(Long studentId, StudentPatchRequest request) {
        Student student = repository.findById(studentId).orElseThrow(() ->new StudentNotFoundException("Student with id " + studentId + " can not be found "));
        if (request.getName() != null)
            student.setName(request.getName());

        if (request.getAge() != null)
            student.setAge(request.getAge());

        if (request.getEmail() != null)
            student.setEmail(request.getEmail());

        if (request.getCourse() != null)
            student.setCourse(request.getCourse());

        if (request.getGrade() != null)
            student.setGrade(request.getGrade());

        return StudentMapper.toStudentResponse(student);
    }

    public Page<StudentResponse> filterStudentsPaginated(String name, Long departmentId, Pageable pageable) {
        Page<Student> student;
       if(name != null && departmentId != null ){
           Department department = departmentRepository.findById(departmentId).orElseThrow(()-> new DepartmentNotFoundException("Department not found"));
           student = repository.findByNameAndDepartment(name, department,pageable );
       }else if (name != null){
           student = repository.findByName(name, pageable);
        } else if(departmentId != null ){
            Department department = departmentRepository.findById(departmentId).orElseThrow(()-> new DepartmentNotFoundException("Department not found"));
            student = repository.findByDepartment(department, pageable);
        } else {
           student = repository.findAll(pageable);
        }
        return student.map(StudentMapper::toStudentResponse);
    }


    public Page<StudentResponse> findByGradeBetween(Integer grade1, Integer grade2, Pageable pageable){
        Page<Student> students = repository.findByGradeBetween(grade1, grade2, pageable);
        return students.map(StudentMapper::toStudentResponse);
    }

    public Page<StudentResponse> findByGradeGreaterThanEqual(Integer grade, Pageable pageable){
        Page<Student> students = repository.findByGradeGreaterThanEqual(grade, pageable);
        return students.map(StudentMapper::toStudentResponse);

    }

    public Page<StudentResponse> findByGradeLessThanEqual(Integer grade, Pageable pageable){
        Page<Student> students = repository.findByGradeLessThanEqual(grade, pageable);
        return students.map(StudentMapper::toStudentResponse);
    }

    public Page<StudentResponse> findByGradeIn(List<Integer> listOfGrade, Pageable pageable){
        Page<Student> students = repository.findByGradeIn(listOfGrade, pageable);
        return students.map(StudentMapper::toStudentResponse);
    }

    public Page<StudentResponse> findByGradeGreaterAndAgeLess(Integer grade, Integer age, Pageable pageable){
        Page<Student> students = repository.findByGradeGreaterThanEqualAndAgeLessThanEqual(grade, age, pageable);
        return students.map(StudentMapper::toStudentResponse);
    }

    public Page<StudentResponse> findByGradeGreaterThanEqualOrAgeLessThanEqual(Integer grade, Integer age, Pageable pageable){
        Page<Student> students = repository.findByGradeGreaterThanEqualOrAgeLessThanEqual(grade, age, pageable);
        return students.map(StudentMapper::toStudentResponse);
    }

/**
    pu[blic void addStudentAndUpdateGrade(Student student, int grade) {

        try (Connection con = DatabaseConnection.getConnection()){

            try  {
                    con.setAutoCommit(false);
                if (StudentDao.addStudent(con, student) && StudentDao.updateGrade(con, student.getId(), grade)) {
                    con.commit();
                } else {
                    System.out.println("Rolling back because condition failed");
                    con.rollback();
                }

            } catch (SQLException e) {
                try {
                    System.out.println("Rolling back, as their are errors");
                    con.rollback();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBothStudent(int id1, int grade1,  int id2, int grade2){
        try(Connection con = DatabaseConnection.getConnection()) {
            try {
                con.setAutoCommit(false);
                if (StudentDao.updateGrade(con, id1, grade1) && StudentDao.updateGrade(con, id2, grade2)) {
                    System.out.println("Successfully updated the information" + id1 + "&&" + id2);
                    con.commit();
                } else {
                    System.out.println("Something went wrong");
                    con.rollback();
                }

            } catch (SQLException e){
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex.getMessage());
                }
                throw new RuntimeException(e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    public void updateThreeStudent(int id1, int grade1, int id2, int grade2, int id3, int grade3){
        try(Connection con = DatabaseConnection.getConnection()) {
            try{
                con.setAutoCommit(false);

                if (StudentDao.updateGrade(con, id1, grade1) && StudentDao.updateGrade(con, id2, grade2) && StudentDao.updateGrade(con, id3, grade3)){
                    System.out.println("Successfully updated all grade");
                    con.commit();
                }
                else {
                    System.out.println("Something went wrong");
                    con.rollback();
                }
            }catch (SQLException e){
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 **/


}
