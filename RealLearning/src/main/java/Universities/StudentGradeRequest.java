package Universities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class StudentGradeRequest {
    @Min(0)
    @Max(100)
    private int grade;

    public StudentGradeRequest(int grade){
        this.grade = grade;
    }
    public StudentGradeRequest(){

    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade(){
        return grade;
    }
}
