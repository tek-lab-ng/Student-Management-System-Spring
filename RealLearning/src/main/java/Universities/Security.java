package Universities;

import Universities.AbstractObject.Person;
import Universities.Paymentmember.Payable;

//This is the Security class
class Security extends Person implements Payable {
    private int gateNumber;
    private int salary;

    public Security(int id, String name, int age, String email, int gateNumber, int salary) {
        super(id, name, age, email);
        this.gateNumber = gateNumber;
        this.salary = salary;
    }

    public void setGateNumber(int gateNumber){
        this.gateNumber = gateNumber;
    }

    public double getSalary() {
        return calculatePay();
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getGateNumber(){
        return gateNumber;
    }
    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + getName()
                + " and I work at Gate " + getGateNumber());
    }

    @Override
    public double calculatePay() {
        double allowancePercentage = 0.05;
        double afterAllowance;
        if(salary > 0) {
            afterAllowance  = (salary * allowancePercentage) + salary;
            return afterAllowance;
        }
        return 0;
    }
}