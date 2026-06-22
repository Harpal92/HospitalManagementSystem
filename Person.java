package HospitalManagementSystem;

public abstract class Person {

private int id=0;
public int getid() {
    return id;
   
}
private String name;
Person(int id,String name){
   
    this.name=name;
    this.id=id;
}
public String getName() {
    return name;
}

}
