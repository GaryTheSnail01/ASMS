package ObjectClasses;

import java.util.ArrayList;

public class Teacher extends Person {
    private String subject;
    private ArrayList<Course> taughtCourses;

    public Teacher(String name, int age, String email, String id, String subject, ArrayList<Course> taughtCourses){
        super(name, age, email, id);
        this.subject = subject;
        this.taughtCourses = taughtCourses;
    }

    public String getSubject() {
        return subject;
    }

    public ArrayList<Course> getTaughtCourses() {
        return taughtCourses;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTaughtCourses(ArrayList<Course> taughtCourses) {
        this.taughtCourses = taughtCourses;
    }
}
