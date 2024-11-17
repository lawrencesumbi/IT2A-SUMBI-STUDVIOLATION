package sumbistudvio;

import java.util.Scanner;

public class Student {
    
    public static void main(String[] args) {
        
        Student stud = new Student();
        Scanner input = new Scanner(System.in);
        int action;
        String resp;

        do {    
            System.out.println("-----------------------------");
            System.out.println("|    1. ADD Student         |");
            System.out.println("|    2. UPDATE Student      |");
            System.out.println("|    3. DELETE Student      |");
            System.out.println("|    4. VIEW Student        |");
            System.out.println("|    5. EXIT Student        |");
            System.out.println("-----------------------------");
       
            while (true) {
                System.out.print("Enter Action: ");
                
                if (input.hasNextInt()) {
                    action = input.nextInt();
                    
                    if (action >= 1 && action <= 5) {
                        break;
                    } else {
                        System.out.println("\tInvalid number, enter (1-5) only.");
                    }
                } else {
                    System.out.println("\tInvalid input, only integers are allowed.");
                    input.next();
                }
            }

            switch(action) {
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
            
            System.out.print("Do you want to continue? (yes/no): ");
            resp = input.next();
            
            while (!resp.equalsIgnoreCase("yes") && !resp.equalsIgnoreCase("no")) {
                System.out.print("Invalid Input, Enter Again: ");
                resp = input.next();
            }
            
        }while(resp.equalsIgnoreCase("yes"));
        
        System.out.println("\nThank you for using the Student Violation System. Have a great day!");
        System.exit(0);
    }
    
    public void addStudents() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        String stud_name;
        while (true) {
            System.out.print("Enter Student Full Name: ");
            stud_name = sc.nextLine();
            if (stud_name.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("\tInvalid name, only alphabetic characters are allowed.");
        }
        
        String stud_program;
        while (true) {
            System.out.print("Enter Student Program: ");
            stud_program = sc.nextLine();
            if (stud_program.matches("[A-Z]+")){
                break;
            }
            System.out.println("\tInvalid program, follow this format (BSIT).");
        }
        
        String stud_section;
        while (true) {
            System.out.print("Enter Student Section: ");
            stud_section = sc.nextLine();
            if (stud_section.matches("[0-9][A-Z]")){
                break;
            }
            System.out.println("\tInvalid section, follow this format (2A).");
        }
        
        String stud_address;
        while (true) {
            System.out.print("Enter Student Address: ");
            stud_address = sc.nextLine();
            if (stud_address.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("\tInvalid address, only alphabetic characters are allowed.");
        }

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
        config conf = new config();

        System.out.print("Enter Student ID to update: ");
        int stud_id;

        while (true) {

            if (sc.hasNextInt()) {
                stud_id = sc.nextInt();

                if (conf.getSingleValue("SELECT stud_id FROM student WHERE stud_id = ?", stud_id) != 0) {
                    break;
                } else {
                    System.out.print("ID doesn't exist, Enter Again: ");
                }
            } else {

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }
        
        sc.nextLine();
        
        String new_name;
        while (true) {
            System.out.print("Enter New Student Full Name: ");
            new_name = sc.nextLine();
            if (new_name.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("\tInvalid name, only alphabetic characters are allowed.");
        }
        
        String new_program;
        while (true) {
            System.out.print("Enter New Student Program: ");
            new_program = sc.nextLine();
            if (new_program.matches("[A-Z]+")){
                break;
            }
            System.out.println("\tInvalid program, follow this format (BSIT).");
        }
        
        String new_section;
        while (true) {
            System.out.print("Enter New Student Section: ");
            new_section = sc.nextLine();
            if (new_section.matches("[0-9][A-Z]")){
                break;
            }
            System.out.println("\tInvalid section, follow this format (2A).");
        }
        
        String new_address;
        while (true) {
            System.out.print("Enter New Student Address: ");
            new_address = sc.nextLine();
            if (new_address.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("\tInvalid address, only alphabetic characters are allowed.");
        }
        
        
        String query = "UPDATE student SET stud_name = ?, stud_program = ?, stud_section = ?, stud_address = ? WHERE stud_id = ?";
        
        conf.updateRecords(query, new_name, new_program, new_section, new_address, stud_id);
        
    }
    
    private void deleteStudents(){
    
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        
        System.out.print("Enter Student ID to delete: ");
        int stud_id;

        while (true) {

            if (sc.hasNextInt()) {
                stud_id = sc.nextInt();

                if (conf.getSingleValue("SELECT stud_id FROM student WHERE stud_id = ?", stud_id) != 0) {
                    break;
                } else {
                    System.out.print("ID doesn't exist, Enter Again: ");
                }
            } else {

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }

        String query = "Delete FROM student WHERE stud_id = ?";
        conf.deleteRecords(query, stud_id);
    }
 
}