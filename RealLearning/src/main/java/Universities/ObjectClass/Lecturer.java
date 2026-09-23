package Universities.ObjectClass;


import Universities.AbstractObject.LibraryMember;
import Universities.Paymentmember.Payable;
import Universities.Student;

import java.util.Scanner;

//This is the Lecturer class
public class Lecturer extends LibraryMember implements Payable {

    private String department;
    private int salary;

    public Lecturer(Long id, String name, int age, String department, int libraryCardNumber, String email, int salary) {
        super(id, name, age, libraryCardNumber, email);
        this.department = department;
        this.salary = salary;

    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getSalary() {

        return calculatePay();
    }

    public static void assignGrade(Student s){
        Scanner in = new Scanner(System.in);
        System.out.print("Kindly enter the grade for the student selected: " + s.getName() + "  ");
        s.setGrade(in.nextInt());
        System.out.println("The name of student with grade:  " + s.getName() +"  " + s.getGrade());
    }

    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + getName()
                + " and I teach " + getDepartment());
    }

    @Override
    public double calculatePay() {
        double allowancePercentage = 0.1;
        double afterAllowance;
        if(salary > 0) {
          afterAllowance  = (salary * allowancePercentage) + salary;
            return afterAllowance;
        }
        return 0;
    }
}