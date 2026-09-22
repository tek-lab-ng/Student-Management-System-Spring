package Universities;

import Universities.DatabaseConnection.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentRepository {


    public void showAllStudent() {

        Connection con = null;
        ResultSet resultSet = null;
        Statement stmt = null;

        try {
            con = DatabaseConnection.getConnection();
            String sql = "Select id, name, age,email, course, grade, library_card_number from Students";
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(sql);

            while (resultSet.next())
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") + " " + resultSet.getInt("age") + " "
                        + resultSet.getString("email") + " " + resultSet.getString("course") + " " + resultSet.getInt("grade") + " "
                        + resultSet.getInt("library_card_number"));


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

    public void findStudentById(int id) {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Select id, name, email from Students where id = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();

            if (resultSet.next())
                System.out.println("The student info is " + resultSet.getInt("id") + " " + resultSet.getString("name")
                        + " " + resultSet.getString("email"));
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
    }

    public void addNewStudent(String name, int age, String email, String course, int grade, int library_card_number) {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;


        try {
            con = DatabaseConnection.getConnection();
            sql = "Insert into Students (name, age, email, course, grade, library_card_number) Values (?,?,?,?,?,?)";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setInt(2, age);
            ptmt.setString(3, email);
            ptmt.setString(4, course);
            ptmt.setInt(5, grade);
            ptmt.setInt(6, library_card_number);

            int info = ptmt.executeUpdate();
            if (info > 0)
                System.out.println("The new entry successfully added");
            else
                System.out.println("Something not right with the values");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
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

    public void updateStudentById(int id, int newGrade) {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Update Students Set grade = ? where id = ?";
            ptmt = con.prepareStatement(sql);

            ptmt.setInt(1, newGrade);
            ptmt.setInt(2, id);

            int outcome = ptmt.executeUpdate();
            if (outcome > 0)
                System.out.println("The student with " + id + " successfully updated");
            else
                System.out.println("Something went wrong, check the query");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
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

    public void deleteStudentByID(int id) {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Delete from Students where id = ?";
            ptmt = con.prepareStatement(sql);

            ptmt.setInt(1, id);

            int outcome = ptmt.executeUpdate();
            if (outcome > 0)
                System.out.println("Successfully deleted student with id " + id);
            else
                System.out.println("Student not found");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
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

    public void findStudentsByMinimumGrade(int grade) {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Select id, name, email, course, grade from Students where grade >= ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, grade);
            resultSet = ptmt.executeQuery();

            System.out.println("The student with grades equal and above " + grade + " are \n");
            while (resultSet.next())
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name")
                        + " " + resultSet.getString("email") + " " + resultSet.getString("course") + " " + resultSet.getInt("grade"));

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

    public void findStudentwithHighestScore() {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Select id, name, email, course, grade from Students where grade = (Select max(grade) from Students)";
            ptmt = con.prepareStatement(sql);
            resultSet = ptmt.executeQuery();

            System.out.println("The student with the highest grades is as shown below");
            if (resultSet.next())
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name")
                        + " " + resultSet.getString("email") + " " + resultSet.getString("course") + " " + resultSet.getInt("grade"));
            else
                System.out.println("None is found");

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

    public void totalNumberStudents() {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Select count(*) from Students";
            ptmt = con.prepareStatement(sql);
            resultSet = ptmt.executeQuery();


            if (resultSet.next())
                System.out.println("The Total number of Students are: " + resultSet.getInt(1));
            else
                System.out.println("None is found");

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

    public void findStudentByCourse(String course) {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Select id, name, course, grade from Students where course = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, course);
            resultSet = ptmt.executeQuery();
            boolean found = false;

            System.out.println("The list of student studying the course " + course);

            while (resultSet.next()) {
                found = true;
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") + " " + resultSet.getString("course") + " " + resultSet.getInt("grade"));
            }
            if (!found) {
                System.out.println("There are no student with this course: " + course);
            }

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

    public void findStudentsByCourseKeyword(String course) {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Select id, name, course, grade from Students where course Like ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + course + "%");
            resultSet = ptmt.executeQuery();
            boolean found = false;

            System.out.println("The list of student studying the course " + course);

            while (resultSet.next()) {
                found = true;
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") + " " + resultSet.getString("course") + " " + resultSet.getInt("grade"));
            }
            if (!found) {
                System.out.println("There are no student with this course: " + course);
            }

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

    public void sortStudentsByGrade() {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Select id, name, course, grade from Students Order By grade desc";
            ptmt = con.prepareStatement(sql);
            resultSet = ptmt.executeQuery();

            System.out.println("The list of student with grade in descending order");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") + " " + resultSet.getString("course") + " " + resultSet.getInt("grade"));
            }


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

    public void sortStudentsByGradeAndName() {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Select id, name, course, grade from Students Order By grade desc, name asc";
            ptmt = con.prepareStatement(sql);
            resultSet = ptmt.executeQuery();

            System.out.println("The list of student with grade in descending order");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") + " " + resultSet.getString("course") + " " + resultSet.getInt("grade"));
            }


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

    public List<Student> searchStudent(String course) {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

        List<Student> returnedStudent = new ArrayList<>();


        try {
            con = DatabaseConnection.getConnection();
            sql = "Select id, name, age, course, library_card_number, email from Students where course Like ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + course + "%" );
            resultSet = ptmt.executeQuery();

            while (resultSet.next()) {
                returnedStudent.add(new Student(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("age"),
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


    public void transferGrade(String name, int grade)  {
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Update Students Set grade = ? where name = ?";
            con.setAutoCommit(false);
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, grade);
            ptmt.setString(2, name);

            int outCome = ptmt.executeUpdate();


           if(outCome > 0){
               System.out.println("Student information with " + name + " Successfully updated");
               con.commit();
           } else{
               System.out.println("Something went wrong");
               con.rollback();
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

    public void updateGradesBatch(Map<Integer, Integer> studentMap){
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Update Students Set grade = ? where id = ?";
            con.setAutoCommit(false);
            ptmt = con.prepareStatement(sql);
            int count = 0;
            int batchsize = 2;

            for(Map.Entry<Integer, Integer> looped: studentMap.entrySet()) {
                ptmt.setInt(1, looped.getValue());
                ptmt.setInt(2, looped.getKey());

                ++count;
                ptmt.addBatch();

                if(count % batchsize == 0){
                    ptmt.executeBatch();
                    con.commit();
                    System.out.println("Batch successfully");
                }
            }

            if(count % batchsize != 0){
                ptmt.executeBatch();
                con.commit();
                System.out.println("Remaining batch succeeded");
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

    public void deleteStudent(int id){
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

    public void getStudentsPage(int pageNumber, int pageSize){
        Connection con = null;
        String sql;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        int offSet = (pageNumber - 1) * pageSize;

        try {
            con = DatabaseConnection.getConnection();
            sql = "Select id, name, course, grade from Students Order by grade desc Limit ? Offset ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, pageSize);
            ptmt.setInt(2, offSet);
            resultSet = ptmt.executeQuery();

            System.out.println("The List");

            while(resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") + " " + resultSet.getString("course") + " " + resultSet.getInt("grade"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
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
