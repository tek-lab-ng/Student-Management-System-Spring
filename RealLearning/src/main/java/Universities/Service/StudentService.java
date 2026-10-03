package Universities.Service;


import Universities.DTO.ProgramResponse;
import Universities.DTO.StudentRequest;
import Universities.DTO.StudentResponse;
import Universities.Entity.Department;
import Universities.Entity.Program;
import Universities.Entity.Student;
import Universities.Exceptions.ProgramNotFoundException;
import Universities.Exceptions.StudentNotFoundException;
import Universities.Mapper.ProgramMapper;
import Universities.Mapper.StudentMapper;
import Universities.Repository.DepartmentRepository;
import Universities.Repository.ProgramRepository;
import Universities.Repository.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<StudentResponse> getAllStudents(){
//        return StudentDao.getAllStudents();
        List<Student> students = repository.findAll();
        return students.stream().map(StudentMapper::toStudentResponse).toList();
    }

    public StudentResponse getStudentById(Long studentId){
        Student student = repository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with ID  " + studentId + " not found"));
        return StudentMapper.toStudentResponse(student);
    }

    @Transactional
    public StudentResponse addStudent(StudentRequest studentRequest) {
        Student st = StudentMapper.toStudent(studentRequest);
        Department department = departmentRepository.findById(studentRequest.getDepartmentId()).orElseThrow();
        st.setDepartment(department);
        st = repository.save(st);
        st.setLibraryCardNumber(10000 + st.getId());
        return StudentMapper.toStudentResponse(st);
    }

    public Optional<StudentResponse> updateStudentGrade(Long id , int grade) {

        Optional<Student> studendentUpdatedGrade =repository.findById(id)
                .map(student -> {
                    student.setGrade(grade);
                    return repository.save(student);
                });

      return  studendentUpdatedGrade.map(StudentMapper::toStudentResponse);

    }

    public boolean deleteStudent(Long id){
       Optional<Student> student = repository.findById(id);
       if(student.isPresent()){
           repository.delete(student.get());
           return true;
       } else {
           return false;
       }
    }

    public List<Student> getStudentByCourse(String course){

//        return StudentDao.findStudentByCourse(course);
        return repository.findByCourse(course);
    }

    public List<Student> getStudentsByMinimumGrade(int minimumGrade){
//        return StudentDao.findStudentsByMinimumGrade(minimumGrade);
        return repository.findByGradeGreaterThanEqual(minimumGrade);
    }

    public Student getStudentWithHighestScore(){
//        Student st = null;
//        st = getAllStudents().stream().max(Comparator.comparing(Student::getGrade)).orElse(null);
//        return st;

        return repository.findTopByOrderByGradeDesc();

    }



    @Transactional
    public StudentResponse updateStudentProfile(StudentRequest studentRequest, Long pathid){
//        try(Connection con = DatabaseConnection.getConnection()){
//
//            return StudentDao.updateStudentProfile(con, student, pathid);
//
//        } catch (SQLException e){
//            throw new RuntimeException(e);
//        }
        Student existstudent = repository.findById(pathid).orElseThrow(() -> new StudentNotFoundException("Student with ID  " + pathid + "not found"));

        existstudent.setName(studentRequest.getName());
        existstudent.setAge(studentRequest.getAge());
        existstudent.setGrade(studentRequest.getGrade());
        existstudent.setCourse(studentRequest.getCourse());
        existstudent.setEmail(studentRequest.getEmail());

        return StudentMapper.toStudentResponse(existstudent);
    }

    @Transactional
    public StudentResponse addProgramToStudent(Long studentId, Long programId){
        Student student = repository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with ID  " + studentId + "not found"));
        Program program = programRepository.findById(programId).orElseThrow(() -> new ProgramNotFoundException("Program with ID  " + programId + " not found"));
        student.getPrograms().add(program);
        repository.save(student);

        return StudentMapper.toStudentResponse(student);
    }

    public List<ProgramResponse> getProgramsByStudent(Long studentId){
        Student student = repository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with ID  " + studentId + "not found"));
        List<Program> programList = student.getPrograms();
        return programList.stream().map(ProgramMapper::toProgramResponse).toList();
    }
/**
    public void addStudentAndUpdateGrade(Student student, int grade) {

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
