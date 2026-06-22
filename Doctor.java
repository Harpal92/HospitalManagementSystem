package HospitalManagementSystem;

public class Doctor  extends Person  implements MedicalStaff{
   private static int idCounter=0;  
String speciality;
  public String getSpeciality(){
    return  speciality;
  }

 Doctor( String name,String speciality){
    super(++idCounter,name);
    
    this.speciality = speciality;
    
   
}
Doctor(int id, String name, String speciality) {
    super(id, name); 
    this.speciality = speciality;
   
}

public static void setIdCounter(int value) {
    idCounter = value;
}


public String toString(){
    return "Doctor [ID=" + getid() + ", Name=" + getName() + ", Speciality=" + speciality + "]";
}



 
}
