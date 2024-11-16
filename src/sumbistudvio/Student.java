package sumbistudvio;

import java.util.Scanner;

public class Student {
    
    public static void main(String[] args) {
        
        Student stud = new Student();
        Scanner input = new Scanner(System.in);
        validator validator = new validator();
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
                    
                    if (validator.isValidAction(action)) {
                        break;
                    } else {
                        System.out.println("\tINVALID ACTION. Enter number from 1-5 only.");
                    }
                } else {
                    System.out.println("\tINVALID ACTION. Enter number from 1-5 only.");
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
        
        System.out.println("Thank you for using the Student Violation System. Have a great day!");
        System.exit(0);
    }
    
    public void addStudents() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        validator validator = new validator();
        
        String stud_name;
        do {
            System.out.print("Enter Student Full Name: ");
            stud_name = sc.nextLine();
            if (!validator.isValidName(stud_name)) {
                System.out.println("\tINVALID NAME. Only alphabetic characters are allowed.");
            }
        } while (!validator.isValidName(stud_name));
        
        String stud_program;
        do {
            System.out.print("Enter Student Program: ");
            stud_program = sc.next();
            if (!validator.isValidProgram(stud_program)) {
                System.out.println("\tINVALID PROGRAM. Follow this format (BSIT).");
            }
        } while (!validator.isValidProgram(stud_program));
        
        String stud_section;
        do {
            System.out.print("Enter Student Section: ");
            stud_section = sc.next();
            if (!validator.isValidSection(stud_section)) {
                System.out.println("\tINVALID SECTION. Follow this format (2A).");
            }
        } while (!validator.isValidSection(stud_section));
        
        sc.nextLine();
        
        String stud_address;
        do {
            System.out.print("Enter Student Address: ");
            stud_address = sc.nextLine();
            if (!validator.isValidAddress(stud_address)) {
                System.out.println("\tINVALID ADDRESS. Only alphabetic characters are allowed.");
            }
        } while (!validator.isValidAddress(stud_address));

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
        validator validator = new validator();

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

                System.out.print("INVALID INPUT, Enter Again: ");
                sc.next();
            }
        }
        
        sc.nextLine();
        
        String new_name;
        do {
            System.out.print("Enter new Student Full Name: ");
            new_name = sc.nextLine();
            if (!validator.isValidName(new_name)) {
                System.out.println("\tINVALID NAME. Only alphabetic characters are allowed.");
            }
        } while (!validator.isValidName(new_name));
        
        String new_program;
        do {
            System.out.print("Enter new Student Program: ");
            new_program = sc.next();
            if (!validator.isValidProgram(new_program)) {
                System.out.println("\tINVALID PROGRAM. Follow this format (BSIT).");
            }
        } while (!validator.isValidProgram(new_program));
        
        String new_section;
        do {
            System.out.print("Enter new Student Section: ");
            new_section = sc.next();
            if (!validator.isValidSection(new_section)) {
                System.out.println("\tINVALID SECTION. Follow this format (2A).");
            }
        } while (!validator.isValidSection(new_section));
        
        sc.nextLine();
        
        String new_address;
        do {
            System.out.print("Enter new Student Address: ");
            new_address = sc.nextLine();
            if (!validator.isValidAddress(new_address)) {
                System.out.println("\tINVALID ADDRESS. Only alphabetic characters are allowed.");
            }
        } while (!validator.isValidAddress(new_address));
        
        
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

                System.out.print("INVALID INPUT, Enter Again: ");
                sc.next();
            }
        }

        String query = "Delete FROM student WHERE stud_id = ?";
        conf.deleteRecords(query, stud_id);
    }
 
}