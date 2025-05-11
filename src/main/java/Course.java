import java.util.ArrayList;

public class Course {
    private String name;
    private int id;
    private ArrayList<Student> enrolledStudents;
    private Teacher teacher;

    public Course(String name, int id, ArrayList<Student> enrolledStudents, Teacher teacher){
        this.name = name;
        this.id = id;
        this.enrolledStudents = enrolledStudents;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
