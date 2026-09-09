package Universities;

import java.util.List;

public class StudentService {
    public List<Student> getAllStudents(){
        return StudentDao.getAllStudents();
    }

    public Student getStudentById(int id){
        return StudentDao.getStudentById(id);
    }

    public void addStudent(Student st){
        StudentDao.addStudent(st);
    }

    public void updateStudent(int id , int grade){
        StudentDao.updateGrade(id, grade);
    }

    public void deleteStudent(int id){
        StudentDao.deleteStudent(id);
    }
}
