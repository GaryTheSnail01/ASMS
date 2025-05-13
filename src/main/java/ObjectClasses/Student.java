package ObjectClasses;

public class Student extends Person {

    public Student(String name, int age, String email, String id){
        super(name, age, email, id);
    }

    @Override
    public String toString(){
        return "Student{id= " + getId() + ", name=" + getName() + ", age= " + getAge() + ", email=" + getEmail() + "}";
    }
}
