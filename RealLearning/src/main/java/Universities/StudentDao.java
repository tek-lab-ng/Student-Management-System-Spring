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
                        + resultSet.getInt("grade"));

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
                                    resultSet.getString("course"), resultSet.getInt("library_card_number"), resultSet.getString("email"),
                                    resultSet.getInt("grade"));
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
}
