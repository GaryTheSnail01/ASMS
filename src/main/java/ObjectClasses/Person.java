package ObjectClasses;

import SysUtils.IDGeneration;

public class Person {
    private String name;
    private int age;
    private String email;
    private String id;

    public Person (String name, int age, String email, String id){
        this.name = name;
        this.age = age;
        this.email = email;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }
}
