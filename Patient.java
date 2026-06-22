package HospitalManagementSystem;

public class Patient extends Person {
   
 private static int idCounter=0;
private String gender;
private int age; 
Patient(String name,String gender,int age) {
        super(++idCounter,name);
        this.age=age;
        this.gender=gender;
        
    }


Patient(int id, String name, String gender, int age) {
    super(id, name); 
    this.gender = gender;
    this.age = age;
}

public static void setIdCounter(int value) {
    idCounter = value;
}

public String toString(){
   return "Patient [ID=" + getid() + ", Name=" + getName() + ", Age=" + age + ", Gender=" + gender + "]";
}
public int getAge() {
    return age;
}
public String getGender() {
    return gender;
}
 

}

