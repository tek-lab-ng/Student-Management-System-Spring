package Universities;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class StudentService {
    public List<Student> getAllStudents(){
        return StudentDao.getAllStudents();
    }

    public Student getStudentById(int id){
        return StudentDao.getStudentById(id);
    }

    public void addStudent(Student st) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        StudentDao.addStudent(connection, st);
    }

    public void updateStudent(int id , int grade) throws SQLException {
            Connection connection = DatabaseConnection.getConnection();
            StudentDao.updateGrade(connection, id, grade);
    }

    public void deleteStudent(int id){
        StudentDao.deleteStudent(id);
    }

    public List<Student> getStudentByCourse(String course){

        return StudentDao.findStudentByCourse(course);
    }

    public List<Student> getStudentsByMinimumGrade(int minimumGrade){
        return StudentDao.findStudentsByMinimumGrade(minimumGrade);
    }

    public Student getStudentWithHighestScore(){
        Student st = null;
        st = getAllStudents().stream().max(Comparator.comparing(Student::getGrade)).orElse(null);
        return st;

    }
}
