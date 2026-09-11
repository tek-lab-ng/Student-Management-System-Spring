package Universities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class StudentDao {


    public static List<Student> getAllStudents(){

        List<Student> allStudent = new ArrayList<>();
        Connection con = null;
        ResultSet resultSet = null;
        Statement stmt = null;
        try {
            con = DatabaseConnection.getConnection();
            String sql = "Select id, name, age,email, course, grade, library_card_number from Students";
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Student st = new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"),
                         resultSet.getString("course"), resultSet.getInt("library_card_number"),resultSet.getString("email"),
                         resultSet.getInt("grade"));

                allStudent.add(st);
            }
            return allStudent;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stmt != null) {
                    stmt.close();
                }

                if (con != null)
                    con.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static Student getStudentById(int id){
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Select id, name, age, course, library_card_number, email, grade from Students where id = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();

            if (resultSet.next())
                return new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"),
                                    resultSet.getString("course"), resultSet.getInt("library_card_number"), resultSet.getString("email"), resultSet.getInt("grade"));
            else
                System.out.println("Student with that id: " + id + " not found");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }

                if (con != null)
                    con.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public static boolean addStudent(Connection con, Student st) throws SQLException{
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        try {

            sql = "Insert into Students (name, age, email, course, grade, library_card_number) Values (?,?,?,?,?,?)";
            ptmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, st.getName());
            ptmt.setInt(2, st.getAge());
            ptmt.setString(3, st.getEmail());
            ptmt.setString(4, st.getCourse());
            ptmt.setInt(5, st.getGrade());
            ptmt.setInt(6, st.getLibraryCardNumber());

            int info = ptmt.executeUpdate();

            if (info > 0) {
                if(resultSet.next()) {
                    resultSet = ptmt.getGeneratedKeys();
                    int id = resultSet.getInt(1);
                    st.setId(id);
                    System.out.println("The new entry successfully added");
                    System.out.println("My returned id: " + id);
                    return true;
                } else {
                    System.out.println("The student id was not returned!!!");
                    return false;
                }
            }
            else{
                System.out.println("Student with name " + st.getName() + " not added");
                return false;
            }

        }  finally {
            try {

                if (resultSet != null){
                    resultSet.close();
                }

                if (ptmt != null) {
                    ptmt.close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }


    public static boolean updateGrade(Connection con, int id, int grade) throws SQLException{
        String sql;
        PreparedStatement ptmt = null;

        try {
            sql = "Update Students Set grade = ? where id = ?";
            ptmt = con.prepareStatement(sql);

            ptmt.setInt(1, grade);
            ptmt.setInt(2, id);

            int outcome = ptmt.executeUpdate();
            if (outcome > 0) {
                System.out.println("The student Grade with studentID " + id + " successfully updated");
                return true;
            }
            else {
                System.out.println("No studentID " + id + " with this found");
                return false;
            }

        }  finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deleteStudent(int id){
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Delete from Students where id = ?";
            con.setAutoCommit(false);
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, id);

            int outcome = ptmt.executeUpdate();

            if(outcome > 0) {
                con.commit();
                System.out.println("Student successfully deleted");
            } else {
                con.setAutoCommit(true);
                System.out.println("Not successful");
            }

        } catch (SQLException e) {
            if(con != null){

                try{
                    con.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            throw new RuntimeException(e);
        }finally {
            try {

                if (ptmt != null) {
                    ptmt.close();
                }

                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static List<Student> findStudentByCourse(String course){
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        List<Student> returnedStudent = new ArrayList<>();


        try {
            con = DatabaseConnection.getConnection();
            sql = "Select id, name, age, course, library_card_number, email, grade from Students where course Like ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + course + "%" );
            resultSet = ptmt.executeQuery();

            while (resultSet.next()) {
                returnedStudent.add(new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"),
                        resultSet.getString("course"), resultSet.getInt("library_card_number"),
                        resultSet.getString("email"), resultSet.getInt("grade")));
            }

            return returnedStudent;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }

                if (con != null)
                    con.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Student> findStudentsByMinimumGrade(int minimumGrade){
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        List<Student> returnedStudent = new ArrayList<>();


        try {
            con = DatabaseConnection.getConnection();
            sql = "Select id, name, age, course, library_card_number, email, grade from Students where grade >= ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, minimumGrade);
            resultSet = ptmt.executeQuery();

            while (resultSet.next()) {
                returnedStudent.add(new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"),
                        resultSet.getString("course"), resultSet.getInt("library_card_number"),
                        resultSet.getString("email"), resultSet.getInt("grade")));
            }

            return returnedStudent;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }

                if (con != null)
                    con.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
