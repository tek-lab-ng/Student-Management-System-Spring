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

    public void addStudent(Student st) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            StudentDao.addStudent(connection, st);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(int id , int grade) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            StudentDao.updateGrade(connection, id, grade);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

}
