package sumbistudvio;

import java.util.Scanner;

public class Student {
  
    public void addStudents(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter Student Full Name: ");
        String stud_name = sc.nextLine();
        System.out.print("Enter Student Program: ");
        String stud_program = sc.next();
        System.out.print("Enter Student Section: ");
        String stud_section = sc.next();
        sc.nextLine();
        System.out.print("Enter Student Address: ");
        String stud_address = sc.nextLine();

        String sql = "INSERT INTO student (stud_name, stud_program, stud_section, stud_address) VALUES (?, ?, ?, ?)";

        conf.addRecords(sql, stud_name, stud_program, stud_section, stud_address);

    }
    
    public void viewStudents() {
        System.out.println("STUDENTS INFORMATION:");
        String query = "SELECT * FROM student";
        String[] headers = {"ID", "Student Name", "Program", "Section", "Address"};
        String[] columns = {"stud_id", "stud_name", "stud_program", "stud_section", "stud_address"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }
    
    
    private void updateStudents(){
    
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        
        
        System.out.print("Enter new Student Full Name: ");
        String new_name = sc.nextLine();
        System.out.print("Enter new Student Program: ");
        String new_program = sc.next();
        System.out.print("Enter new Student Section: ");
        String new_section = sc.next();
        sc.nextLine();
        System.out.print("Enter new Student Address: ");
        String new_address = sc.nextLine();
        
        
        String query = "UPDATE student SET stud_name = ?, stud_program = ?, stud_section = ?, stud_address = ? WHERE stud_id = ?";
        
        config conf = new config();
        conf.updateRecords(query, new_name, new_program, new_section, new_address, id);
        
    }
    
    private void deleteStudents(){
    
        Scanner sc = new Scanner (System.in);
        
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        
        String query = "Delete FROM student WHERE stud_id = ?";
       
        config conf = new config();
        conf.deleteRecords(query, id);

    }
    

    public void main(String[] args) {
        
        Student stud = new Student();
        Scanner input = new Scanner(System.in);
       
        
        do{    
            System.out.println("1. ADD STUDENT");
            System.out.println("2. UPDATE STUDENT");
            System.out.println("3. DELETE STUDENT");
            System.out.println("4. VIEW STUDENT");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = input.nextInt();

            switch(action){
                case 1:
                    stud.addStudents();
                break; 
                case 2:
                    stud.viewStudents();
                    stud.updateStudents();
                break;
                case 3:
                    stud.viewStudents();
                    stud.deleteStudents();
                    break;
                case 4:
                    stud.viewStudents();
                break;
                case 5:
                    return;
            }
            
            
        }while(true);
            
    }
     
}

    

