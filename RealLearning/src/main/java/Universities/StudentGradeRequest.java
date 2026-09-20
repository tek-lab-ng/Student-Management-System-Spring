package Universities;

public class StudentGradeRequest {
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
