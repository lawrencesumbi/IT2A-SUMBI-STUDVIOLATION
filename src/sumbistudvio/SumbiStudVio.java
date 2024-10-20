package sumbistudvio;

import java.util.Scanner;

public class SumbiStudVio {
  
    public void addStudents(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Student First Name: ");
        String stud_fname = sc.next();
        System.out.print("Student Last Name: ");
        String stud_lname = sc.next();
        System.out.print("Student Program: ");
        String stud_program = sc.next();
        System.out.print("Student Section: ");
        String stud_section = sc.next();

        String sql = "INSERT INTO student (stud_fname, stud_lname, stud_program, stud_section) VALUES (?, ?, ?, ?)";

        conf.addRecord(sql, stud_fname, stud_lname, stud_program, stud_section);

    }
    
    private void viewStudents() {
        String query = "SELECT * FROM student";
        String[] headers = {"ID", "Firstname", "Lastname", "Program", "Section"};
        String[] columns = {"stud_id", "stud_fname", "stud_lname", "stud_program", "stud_section"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }
    
    
    private void updateStudents(){
    
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        
        System.out.print("Enter new Student First Name: ");
        String new_fname = sc.next();
        System.out.print("Enter new Student Last Name: ");
        String new_lname = sc.next();
        System.out.print("Enter new Student Program: ");
        String new_program = sc.next();
        System.out.print("Enter new Student Section: ");
        String new_section = sc.next();
        
        String query = "UPDATE student SET stud_fname = ?, stud_lname = ?, stud_program = ?, stud_section = ? WHERE stud_id = ?";
        
        config conf = new config();
        conf.updateRecord(query, new_fname, new_lname, new_program, new_section, id);
        
    }
    
    private void deleteStudents(){
    
        Scanner sc = new Scanner (System.in);
        
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        
        String query = "Delete FROM student WHERE stud_id = ?";
       
        config conf = new config();
        conf.deleteRecord(query, id);

    }
    

    public void main(String[] args) {
        
        SumbiStudVio test = new SumbiStudVio();
        Scanner input = new Scanner(System.in);
       
        
        do{    
            System.out.println("1. ADD");
            System.out.println("2. UPDATE");
            System.out.println("3. DELETE");
            System.out.println("4. VIEW");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = input.nextInt();

            switch(action){
                case 1:
                    test.addStudents();
                break; 
                case 2:
                    test.viewStudents();
                    test.updateStudents();
                break;
                case 3:
                    test.viewStudents();
                    test.deleteStudents();
                    test.viewStudents();
                    break;
                case 4:
                    test.viewStudents();
                break;
                case 5:
                    return;
            }
            
            
        }while(true);
            
    }
     
}

    

