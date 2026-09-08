package Universities;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JDBCTest {
    public static void main(String[] args) {
        testConnection();

//        new StudentRepository().showAllStudent();
//        new StudentRepository().findStudentById(1);
//        new StudentRepository().addNewStudent("Kemi", 24, "Kemi@gmail.com", "Medicine", 90, 106 );
//            new StudentRepository().updateStudentById(1, 90);
//        new StudentRepository().deleteStudentByID(6);

        Map<Integer, Integer> student = new HashMap<>();
        student.put(1, 78);
        student.put(2, 79);
        student.put(3, 80);
        student.put(4, 83);
        student.put(5, 68);

        new StudentRepository().searchStudent("Physics").forEach(n-> System.out.println(n.getName()));
    }

    public static void testConnection(){
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();
            if(!con.isClosed())
                System.out.println("Connection is still opened");
            else
                System.out.println("Connection is closed.");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
