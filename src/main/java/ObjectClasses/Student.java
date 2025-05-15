package ObjectClasses;

public class Student extends Person {

    private int grade;

    public Student(String name, int grade, int age, String email, String id){
        super(name, age, email, id);
        this.grade = grade;
    }

    @Override
    public String toString(){
        return "Student{id= " + getId() + ", name= " + getName() + ", grade= " + getGrade() + ", age= " + getAge() + ", email= " + getEmail() + "}";
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
