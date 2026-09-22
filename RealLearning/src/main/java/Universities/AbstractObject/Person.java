package Universities.AbstractObject;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

//The University Management Structure
@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "name field can not be empty")
    @Size(min = 4)
    @Pattern(regexp = "[A-Za-z]+")
    @Column(name = "name")
    private String name;
    @Min(16)
    @Max(50)
    @Column(name = "age")
    private int age;

    @Email(message = "Please provide a valid email address")
    private String email;


    public Person(Long id, String name, int age, String email) {

            this.id = id;
            this.name = name;
            this.age = age;
            this.email = email;

    }

    public Person(String name, int age, String email){
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Person() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public abstract void introduce();

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
