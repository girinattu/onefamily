package uk.onefamily.api;

public class EmployeeObj {
    int id;
    String name;
    String salary;
    String age;
    String profile_image;

    public EmployeeObj(String employee_name, String employee_salary, String employee_age) {
        this.name = employee_name;
        this.salary = employee_salary;
        this.age = employee_age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return name;
    }

    public void setEmployee_name(String employee_name) {
        this.name = employee_name;
    }

    public String getEmployee_salary() {
        return salary;
    }

    public void setEmployee_salary(String employee_salary) {
        this.salary = employee_salary;
    }

    public String getEmployee_age() {
        return age;
    }

    public void setEmployee_age(String employee_age) {
        this.age = employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String toString(){
        return "{name:" +getEmployee_name()+",salary:"+getEmployee_salary()+",age:"+getEmployee_salary()+"}";
    }
}
