package Universities.ObjectClass;

import Universities.AbstractObject.Person;
import Universities.Paymentmember.Payable;

//This is the Janitor class
class Janitor extends Person implements Payable {

    private String cleanArea;
    private int salary;

    public Janitor(int id, String name, int age, String email, String cleanArea, int salary) {
        super(id, name, age, email);
        this.cleanArea = cleanArea;
        this.salary = salary;
    }

    public void setCleanArea(String cleanArea){
        this.cleanArea = cleanArea;
    }

    public String getCleanArea(){
        return cleanArea;
    }

    public double getSalary() {
        return calculatePay();
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + getName()
                + " and I clean on the " + getCleanArea());
    }
    @Override
    public double calculatePay() {
        double allowancePercentage = 0.03;
        double afterAllowance;
        if(salary > 0) {
            afterAllowance  = (salary * allowancePercentage) + salary;
            return afterAllowance;
        }
        return 0;
    }
}