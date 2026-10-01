package Universities.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Program {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "programs")
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudentList() {
        return students;
    }

    public void setStudentList(List<Student> studentList) {
        this.students = studentList;
    }


}
