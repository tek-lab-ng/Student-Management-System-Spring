package Universities;

import Universities.AbstractObject.LibraryMember;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

//This is the Student class

@Entity
@Table(name = "Students")
public class Student extends LibraryMember {

    @NotBlank(message = "Course field cannot be blank")
    @Size(min = 4, message = "Course name should be 4 or more letters")
    @Pattern(regexp = "[A-Za-z]+", message = "Course field cannot contain number and special characters")
    @Column(name = "course")
    private String course;
    @Min(0)
    @Max(100)
    @Column(name = "grade")
    private int grade;


    public Student(Long id, String name, int age, String course, int libraryCardNumber, String email, int grade){
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