package HospitalManagementSystem;

public class Appointment {
    private Patient patient;
   private Doctor doctor;
   private String date;


public Appointment(Patient patient,Doctor doctor,String date){
     this.patient=patient;
     this.doctor=doctor;
     this.date=date;
}
public String toString(){
    return "Appointment [Patient=" + patient + ", Doctor=" + doctor + ", Date=" + date + "]";
}
public Doctor getDoctor() {
    return doctor;
}
public Patient getPatient() {
    return patient;
}
public String getDate() {
    return date;
}
}
