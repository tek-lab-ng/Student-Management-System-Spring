package Universities;

//This is the Student class
public class Student extends LibraryMember {

    private String course;
    private int grade;

    public Student(int id, String name, int age, String course, int libraryCardNumber, String email, int grade){
        super(id, name, age, libraryCardNumber, email);
        this.course = course;
        this.grade = grade;
    }

    public Student(String name, int age, String course, int libraryCardNumber, String email, int grade){
        super(name, age, libraryCardNumber, email);
        this.course = course;
        this.grade = grade;
    }

    public Student(){
        super();
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setGrade(int grade){
        this.grade = grade;
    }
    public int getGrade(){return grade;}

    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + getName()
                + " and I study " + getCourse());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id ='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", age='" + getAge() + '\'' +
                ", course='" + getCourse() + '\'' +
                ", library_card ='" + getLibraryCardNumber() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", grade='" + getGrade() + '\'' +
                '}';
    }
}