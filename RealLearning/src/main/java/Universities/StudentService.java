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
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
