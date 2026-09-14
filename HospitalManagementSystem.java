package HospitalManagementSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HospitalManagementSystem {
     private static List<Patient> patients=new ArrayList<>();
   private static List<Doctor> doctors=new ArrayList<>();
   private static List<Appointment> appointments=new ArrayList<>();
    public static void main(String[] args) {
        loadpatients();
        loaddoctors();
        loadappointments();
        Scanner sc=new Scanner(System.in);
        int choice;
       do {
        System.out.println("HospitalManagementSystem");
        System.out.println( "1 add patient");
        System.out.println( "2 add doctor");
        System.out.println ("3 schedule appointment");
        System.out.println( "4 view patients");
        System.out.println(" 5 view doctors");
        System.out.println(" 6 view appointment");
        System.out.println(" 7 delete patient");
        System.out.println( "8 delete doctor");
        System.out.println(" 0 exit");
        System.out.print("enter choice ");
        choice=sc.nextInt();
        switch (choice) {
            case 1:
                addpatient(sc);
                break;
                case 2:
                    adddoctor(sc);
                    break;
                    case 3:
                        scheduleappointment(sc);
                        break;
            case 4:
                viewpatients();
                    break;
          case 5:
            viewdoctors();
                    break;
         case 6:
            viewappointments();
                    break;
        case 7: 
                    deletepatient(sc);
                    break;
        case 8:
                deletedoctor(sc);
                break;
       

        case 0:
            
    savepatients();
    savedoctors();
    saveappointments();
    
    
            System.out.println("Exiting...");
                    break;
                    
        
            default: 
            System.out.println("invalid choice try again");

                
        }
       } while (choice !=0);
    }
   private static void deletedoctor(Scanner sc) {
    System.out.println("Enter doctor ID to delete:");
    int id = sc.nextInt();

    try {
        Doctor doctor = finddocbyid(id);
        doctors.remove(doctor);
        System.out.println("Doctor deleted successfully.");
    } catch (DoctorNotFoundException e) {
        System.out.println(e.getMessage());
    }
}
    private static void deletepatient(Scanner sc) {
    System.out.println("Enter patient ID to delete:");
    int id = sc.nextInt();

    try {
        Patient patient = findpatbyid(id);
        patients.remove(patient);
        System.out.println("Patient deleted successfully.");
    } catch (PatientNotFoundException e) {
        System.out.println(e.getMessage());
    }
}
    private static void viewappointments() {
       for(Appointment a:appointments)
        System.out.println(a);
    }
    private static void viewdoctors() {
    for(Doctor d:doctors)
        System.out.println(d);
    }
    private static void viewpatients() {
       for(Patient p:patients) 
        System.out.println(p);
    }
    private static void scheduleappointment(Scanner sc) {
    System.out.print("patient id ");
    int pid = sc.nextInt();
    System.out.print("doctor id ");
    int docid = sc.nextInt();
    System.out.print("date appoint ");
    String date = sc.next();
    System.out.print("time appoint (e.g. 10:30AM) ");
    String time = sc.next();
    try {
        Patient patient = findpatbyid(pid);
        Doctor doctor = finddocbyid(docid);

        checkConflict(docid, date, time);

        Appointment appointment = new Appointment(patient, doctor, date, time);
        appointments.add(appointment);
        System.out.println("Appointment added successfully");

    } catch (PatientNotFoundException | DoctorNotFoundException | AppointmentConflictException e) {
        System.out.println(e.getMessage());
    }
}

private static void checkConflict(int docid, String date, String time) {
    for (Appointment a : appointments) {
        if (a.getDoctor().getid() == docid && a.getDate().equals(date) && a.getTime().equals(time)) {
            throw new AppointmentConflictException(
                "Doctor with ID " + docid + " already has an appointment at " + date + " " + time
            );
        }
    }
}
    private static Doctor finddocbyid(int docid) {
       for (Doctor d : doctors) {
    if (d.getid() == docid) return d;
}
throw new DoctorNotFoundException(
        "DOCTOR with ID " + docid + " not found"
    );
    }
    private static Patient findpatbyid(int pid) {
        for(Patient p:patients) {
            if(p.getid()==pid) return p;
        }
       throw new PatientNotFoundException(
        "Patient with ID " + pid + " not found"
    );
    }
    private static void adddoctor(Scanner sc) {
       System.out.print("enter doctor name : ");
        sc.nextLine(); 
        String name=sc.nextLine();
        System.out.print("enter doctor speciality: ");
        String speciality=sc.nextLine();
        Doctor doctor=new Doctor(name, speciality);
         doctors.add(doctor);
         System.out.println("doctor added succesfully");
        
       
    }
    private static void addpatient(Scanner sc){
        System.out.print("Enter patient name: ");
        sc.nextLine(); 

        String name=sc.nextLine();
        System.out.print("Enter patient age: ");
        int age=sc.nextInt();
        System.out.print("Enter patient gender: ");
        String gender=sc.next();
        Patient patient=new Patient(name, gender, age);
        patients.add(patient);
       
    }
    private static void saveappointments(){
        try {
             FileWriter fw = new FileWriter("appointments.csv");
        for (Appointment a : appointments) {
    
            
        
            
         fw.write(a.getPatient().getid() + "," + a.getDoctor().getid() + "," + a.getDate() + "," + a.getTime() + "\n");
            
              
    }
     fw.close();
    System.out.println("appointment added succesfully");
        } catch (Exception e) {
           System.out.println(e);
        }
    }
    private static void savepatients(){
       try {
        FileWriter fw = new FileWriter("patients.csv");
        for (Patient p : patients) {
    
            
        
            
          fw.write(p.getid() + "," + p.getName() + "," + p.getAge() + "," + p.getGender() + "\n");
            
              
    }
     fw.close();
    System.out.println("patient added succesfully");
}
         catch (Exception e) {
           System.out.println(e);
        }
    }
       
    
     private static void savedoctors(){
       try {
        FileWriter fw = new FileWriter("doctors.csv");
        for (Doctor d : doctors) {
    
            
        
            
          fw.write(d.getid() + "," + d.getName() + ","  + d.getSpeciality() + "\n");
            
              
    }
     fw.close();
    System.out.println("doctor added succesfully");
}
         catch (Exception e) {
           System.out.println(e);
        }
    }
       
    private static void loadpatients(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("patients.csv"));
String line;
while((line = br.readLine()) != null) {
    String[] parts = line.split(",");
     int id = Integer.parseInt(parts[0]);
    String name = parts[1];
            int age = Integer.parseInt(parts[2]);
            String gender = parts[3];

            Patient p = new Patient(id, name, gender,age);
            patients.add(p);

}
int maxid = 0;
for(Patient p : patients) {
    if(p.getid() > maxid) maxid = p.getid();
}
Patient.setIdCounter(maxid);


br.close();

        } catch (Exception e) {
           System.out.println(e);
        }
    }


private static void loaddoctors(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("doctors.csv"));
String line;
while((line = br.readLine()) != null) {
    String[] parts = line.split(",");
     int id = Integer.parseInt(parts[0]);
    String name = parts[1];
            
            String speciality = parts[2];

            Doctor d = new Doctor(id, name,speciality);
            doctors.add(d);

}
int maxid = 0;
for(Doctor d : doctors) {
    if(d.getid() > maxid) maxid = d.getid();
}
Doctor.setIdCounter(maxid);


br.close();

        } catch (Exception e) {
           System.out.println(e);
        }
    }
    private static void loadappointments(){
        try {
           BufferedReader br = new BufferedReader(new FileReader("appointments.csv"));
String line;
while((line = br.readLine()) != null) {
    String[] parts = line.split(",");
     int pid = Integer.parseInt(parts[0]);
     int docid = Integer.parseInt(parts[1]);
    String date = parts[2];
    String time = parts[3];

    Patient patient = findpatbyid(pid);
    Doctor doctor = finddocbyid(docid);

     Appointment appointment = new Appointment(patient, doctor, date, time);
     appointments.add(appointment);

           

}




br.close();

         

        } catch (Exception e) {
           System.out.println(e);
        }
    }



    
    }






    
    





