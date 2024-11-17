package sumbistudvio;

import java.util.Scanner;

public class Record {
   
    public static void main(String[] args) {
        
        Record rec = new Record();
        Scanner input = new Scanner(System.in);
        int action;
        String resp;
        
        do{    
            System.out.println("-----------------------------");
            System.out.println("|    1. ADD Record          |");
            System.out.println("|    2. UPDATE Record       |");
            System.out.println("|    3. DELETE Record       |");
            System.out.println("|    4. VIEW All Record     |");
            System.out.println("|    5. SEARCH Record       |");
            System.out.println("|    6. EXIT Record         |");
            System.out.println("-----------------------------");

            while (true) {
                System.out.print("Enter Action: ");
                
                if (input.hasNextInt()) {
                    action = input.nextInt();
                    
                    if (action >= 1 && action <= 6) {
                        break;
                    } else {
                        System.out.println("\tInvalid number, enter (1-6) only.");
                    }
                } else {
                    System.out.println("\tInvalid input, only integers are allowed.");
                    input.next();
                }
            }

            switch(action){
                case 1:
                    rec.addRecord();
                break; 
                case 2:
                    rec.viewRecord();
                    rec.updateRecord();
                break;
                case 3:
                    rec.viewRecord();
                    rec.deleteRecord();
                    break;
                case 4:
                    rec.viewRecord();
                break;
                case 5:
                    rec.searchRecord();
                break;
                case 6:
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
    
    public void addRecord() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        Student stud = new Student();
        Violation vio = new Violation();

        stud.viewStudents();
        System.out.print("Enter Student ID to add: ");
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
        
        vio.viewViolation();
        System.out.print("Enter Violation ID to add: ");
        int vio_id;
        
        while (true) {

            if (sc.hasNextInt()) {
                vio_id = sc.nextInt();

                if (conf.getSingleValue("SELECT vio_id FROM violation WHERE vio_id = ?", vio_id) != 0) {
                    break;
                } else {
                    System.out.print("ID doesn't exist, Enter Again: ");
                }
            } else {

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }
        
        System.out.print("Enter Date Reported (MM-DD-YYYY): ");
        String rec_reported = sc.next();
    
        while (!config.isValidDate(rec_reported)) {
            System.out.print("Invalid Date Format. Enter Again: ");
            rec_reported = sc.next();
        }

        String rec_settled = "ON GOING";
        
        String sql = "INSERT INTO record (stud_id, vio_id, rec_reported, rec_settled) VALUES (?, ?, ?, ?)";

        conf.addRecords(sql, stud_id, vio_id, rec_reported, rec_settled);   
    }
    
    private void viewRecord() {
        System.out.println("OVERALL REPORTS:");
        String query = "SELECT record.rec_id, student.stud_name, violation.vio_name, record.rec_reported, record.rec_settled "
                + "FROM record "
                + "JOIN student ON record.stud_id = student.stud_id "
                + "JOIN violation ON record.vio_id = violation.vio_id";
        String[] headers = {"RECORD ID", "STUDENT NAME", "VIOLATION NAME", "DATE REPORTED", "DATE SETTLED"};
        String[] columns = {"rec_id", "stud_name", "vio_name", "rec_reported", "rec_settled"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }
    
    private void searchRecord() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        Student stud = new Student();
        
        stud.viewStudents();
        System.out.println("Search a specific student to view their violations...");
        System.out.print("Enter Student ID to search: ");
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
        
        System.out.println("DETAILED REPORT:");       
        String query = "SELECT record.rec_id, student.stud_name, violation.vio_name, violation.vio_des, violation.vio_sanction, record.rec_reported, record.rec_settled "
                + "FROM record "
                + "JOIN student ON record.stud_id = student.stud_id "
                + "JOIN violation ON record.vio_id = violation.vio_id "
                + "WHERE record.stud_id = " + stud_id;
        String[] headers = {"RECORD ID", "STUDENT NAME", "VIOLATION NAME", "DESCRIPTION", "SANCTION", "DATE REPORTED", "DATE SETTLED"};
        String[] columns = {"rec_id", "stud_name", "vio_name", "vio_des", "vio_sanction", "rec_reported", "rec_settled"};

        conf.viewRecords(query, headers, columns);
    }
        
    private void updateRecord(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter Record ID to update: ");
        int rec_id;
        
        while (true) {

            if (sc.hasNextInt()) {
                rec_id = sc.nextInt();

                if (conf.getSingleValue("SELECT rec_id FROM record WHERE rec_id = ?", rec_id) != 0) {
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
        System.out.print("Enter new Date Settled (MM-DD-YYYY): ");
        String rec_settled = sc.next();
    
        while (!config.isValidDate(rec_settled)) {
            System.out.print("Invalid Date Format. Enter Again: ");
            rec_settled = sc.next();
        }
         
        String query = "UPDATE record SET rec_settled = ? WHERE rec_id = ?";
        
        conf.updateRecords(query, rec_settled, rec_id);
        
    }
    
    private void deleteRecord(){
    
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        
        System.out.print("Enter Record ID to delete: ");
        int rec_id;
        
        while (true) {

            if (sc.hasNextInt()) {
                rec_id = sc.nextInt();

                if (conf.getSingleValue("SELECT rec_id FROM record WHERE rec_id = ?", rec_id) != 0) {
                    break;
                } else {
                    System.out.print("ID doesn't exist, Enter Again: ");
                }
            } else {

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }
        
        String query = "Delete FROM record WHERE rec_id = ?";
       
        conf.deleteRecords(query, rec_id);

    }
    
    
    
}
