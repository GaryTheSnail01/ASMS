import java.util.ArrayList;

public class Student extends Person {
    private int gradeLevel;
    private ArrayList<Course> enrolledCourses = new ArrayList<Course>();

    public Student(String name, int age, String email, String id, int gradeLevel, ArrayList<Course> enrolledCourses){
        super(name, age, email, id);
        this.gradeLevel = gradeLevel;
        this.enrolledCourses = enrolledCourses;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public void setEnrolledCourses(ArrayList<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
