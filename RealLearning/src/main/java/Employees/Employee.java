package Employees;

import java.sql.*;
import java.util.*;


public class Employee {

    private String first_name;
    private String last_name;
    private int id;
    private String department;



    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Demo";
        String user = "root";
        String pwd = "pass123";

        return DriverManager.getConnection(url, user, pwd);

    }

    public void createEmployee(String firstName, String lastName, String department, double salary){
        this.first_name = firstName;
        this.last_name = lastName;
        this.department = department;


        try{
            getConnection();
            String sql = "INSERT INTO employee(FirstName, LastName, Department, Salary) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = getConnection().prepareStatement(sql);

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, department);
            pstmt.setDouble(4,salary);

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " row(s) inserted.");

            pstmt.close();
            getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Employee returnEmployee(int id){
            Employee employees = null;
        try{
            getConnection();
            String sql = "Select * from employee where ID = ?";
            PreparedStatement prep = getConnection().prepareStatement(sql);
            prep.setInt(1,id);


            ResultSet resultSet = prep.executeQuery();

            if (resultSet.next()) {
                employees = new Employee();
                employees.setId(resultSet.getInt("ID"));
                employees.setFirst_name(resultSet.getString("FirstName"));
                employees.setLast_name(resultSet.getString("LastName"));
                employees.setDepartment(resultSet.getString("Department"));
            }

            prep.close();
            return employees;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Employee> returnEmployees (){
        List<Employee> employees = new ArrayList<>();
        Employee employee = null;
       try{
           getConnection();
           String sql = "Select ID,FirstName,LastName,Department from employee";
           PreparedStatement pst = getConnection().prepareStatement(sql);

           ResultSet resultSet = pst.executeQuery();

           while(resultSet.next()){
               employee = new Employee();
               employee.setId(resultSet.getInt("ID"));
               employee.setFirst_name(resultSet.getString("FirstName"));
               employee.setLast_name(resultSet.getString("LastName"));
               employee.setDepartment(resultSet.getString("Department"));
               employees.add(employee);
           }

           pst.close();
           return  employees;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

    }

    public void updateEmployee(int id, String FirstName, String LastName){

        try{
            getConnection();
            String sql = "update employee set FirstName = ? , LastName = ? where ID = ?";
            PreparedStatement pst = getConnection().prepareStatement(sql);
            pst.setString(1, FirstName);
            pst.setString(2, LastName);
            pst.setInt(3, id);

            pst.execute();
            System.out.println("The user at " + id + " As been updated");

            pst.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEmployee(int id){

        try{
            getConnection();
            String sql = "Delete from employee where id = ?";
            PreparedStatement pst = getConnection().prepareStatement(sql);
            pst.setInt(1, id);

            pst.execute();
            System.out.println("The user at " + id + " As been deleted");

            pst.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchEmployee(int id){

        try{
            getConnection();
            String sql = "Select * from employee where ID = ?";
            PreparedStatement prep = getConnection().prepareStatement(sql);
            prep.setInt(1,id);


            ResultSet resultSet = prep.executeQuery();

            if (resultSet.next()) {
              String firstname = resultSet.getString("FirstName");
              String lastname = resultSet.getString("LastName");
              System.out.println("The searched item result " + firstname  + " The last name " + lastname);
            }

            prep.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public  void filterEmployee(String department){
        try{
            getConnection();
            String sql = "Select * from employee where Department = ?";
            PreparedStatement prep = getConnection().prepareStatement(sql);
            prep.setString(1, department);


            ResultSet resultSet = prep.executeQuery();

            if (resultSet.next()) {
                String firstname = resultSet.getString("FirstName");
                String lastname = resultSet.getString("LastName");
                System.out.println("The filtered item result " + firstname  + " The last name " + lastname);
            }
            prep.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void calcSalaryStat(){

        try{
            getConnection();
            String sql = "Select min(Salary) as Minimum , max(Salary) as Maximum, avg(Salary) as Average, sum(Salary) as Total  from employee";
            PreparedStatement pst = getConnection().prepareStatement(sql);

            ResultSet resultSet =pst.executeQuery();

            while(resultSet.next()){
                double minimum = resultSet.getDouble("Minimum");
                double maximum = resultSet.getDouble("Maximum");
                double average = resultSet.getDouble("Average");
                double sum  = resultSet.getDouble("Total");
                System.out.println("The minimum salary:  " + minimum);
                System.out.println("The maximum salary:  " + maximum);
                System.out.println("The average salary:  " + average);
                System.out.println("The total salary:  " + sum);

            }

            pst.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Employee> paginationEmployee (int number){
        List<Employee> employees = new ArrayList<>();
        Employee employee = null;
        try{
            getConnection();
            String sql = "Select ID,FirstName,LastName,Department from employee limit " + number;
            PreparedStatement pst = getConnection().prepareStatement(sql);

            ResultSet resultSet =pst.executeQuery();

            while(resultSet.next()){
                employee = new Employee();
                employee.setId(resultSet.getInt("ID"));
                employee.setFirst_name(resultSet.getString("FirstName"));
                employee.setLast_name(resultSet.getString("LastName"));
                employee.setDepartment(resultSet.getString("Department"));
                employees.add(employee);
            }

            pst.close();
            return employees;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(first_name, employee.first_name) && Objects.equals(last_name, employee.last_name) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name, id, department);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", id=" + id +
                ", department='" + department + '\'' +
                '}';
    }

    //    abstract void introduce();
}
