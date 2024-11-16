package sumbistudvio;

import java.util.Scanner;

public class Violation {
    
    public static void main(String[] args) {
        
        Violation vio = new Violation();
        Scanner input = new Scanner(System.in);
        validator validator = new validator();
        int action;
        String resp;
        
        do{    
            System.out.println("-----------------------------");
            System.out.println("|    1. ADD Violation       |");
            System.out.println("|    2. UPDATE Violation    |");
            System.out.println("|    3. DELETE Violation    |");
            System.out.println("|    4. VIEW Violation      |");
            System.out.println("|    5. EXIT Violation      |");
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

            switch(action){
                case 1:
                    vio.addViolation();
                break; 
                case 2:
                    vio.viewViolation();
                    vio.updateViolation();
                break;
                case 3:
                    vio.viewViolation();
                    vio.deleteViolation();
                    break;
                case 4:
                    vio.viewViolation();
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
    
    public void addViolation(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        validator validator = new validator();
        
        String vio_name;
        do {
            System.out.print("Enter Violation Name: ");
            vio_name = sc.nextLine();
            if (!validator.isValidName(vio_name)) {
                System.out.println("\tINVALID NAME. Only alphabetic characters are allowed.");
            }
        } while (!validator.isValidName(vio_name)); 
        
        String vio_des;
        do {
            System.out.print("Enter Description: ");
            vio_des = sc.nextLine();
            if (!validator.isValidName(vio_des)) {
                System.out.println("\tINVALID DESCRIPTION. Only alphabetic characters are allowed.");
            }
        } while (!validator.isValidName(vio_des)); 
        
        String vio_severity;
        do {
            System.out.print("Enter Severity of Violation: ");
            vio_severity = sc.nextLine();
            if (!validator.isValidName(vio_severity)) {
                System.out.println("\tINVALID SEVERITY. Only alphabetic characters are allowed.");
            }
        } while (!validator.isValidName(vio_severity)); 
        
        String vio_sanction;
        do {
            System.out.print("Enter Sanction: ");
            vio_sanction = sc.nextLine();
            if (!validator.isValidName(vio_sanction)) {
                System.out.println("\tINVALID SANCTION. Only alphabetic characters are allowed.");
            }
        } while (!validator.isValidName(vio_sanction)); 

        
        String sql = "INSERT INTO violation (vio_name, vio_des, vio_severity, vio_sanction) VALUES (?, ?, ?, ?)";

        conf.addRecords(sql, vio_name, vio_des, vio_severity, vio_sanction);

    }
    
    public void viewViolation() {
        System.out.println("LIST OF VIOLATIONS:");
        String query = "SELECT * FROM violation";
        String[] headers = {"Violation ID", "Violation Name", "Description", "Severity", "Sanction"};
        String[] columns = {"vio_id", "vio_name", "vio_des", "vio_severity", "vio_sanction"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }
    
    
    private void updateViolation(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        validator validator = new validator();
        
        System.out.print("Enter Violation ID to update: ");
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

                System.out.print("INVALID INPUT, Enter Again: ");
                sc.next();
            }
        }
        
        sc.nextLine();
        
        String vio_name;
        do {
            System.out.print("Enter new Violation Name: ");
            vio_name = sc.nextLine();
            if (!validator.isValidName(vio_name)) {
                System.out.println("\tINVALID NAME. Only alphabetic characters are allowed.");
            }
        } while (!validator.isValidName(vio_name)); 
        
        String vio_des;
        do {
            System.out.print("Enter new Description: ");
            vio_des = sc.nextLine();
            if (!validator.isValidName(vio_des)) {
                System.out.println("\tINVALID DESCRIPTION. Only alphabetic characters are allowed.");
            }
        } while (!validator.isValidName(vio_des)); 
        
        String vio_severity;
        do {
            System.out.print("Enter new Severity of Violation: ");
            vio_severity = sc.nextLine();
            if (!validator.isValidName(vio_severity)) {
                System.out.println("\tINVALID SEVERITY. Only alphabetic characters are allowed.");
            }
        } while (!validator.isValidName(vio_severity)); 
        
        String vio_sanction;
        do {
            System.out.print("Enter new Sanction: ");
            vio_sanction = sc.nextLine();
            if (!validator.isValidName(vio_sanction)) {
                System.out.println("\tINVALID SANCTION. Only alphabetic characters are allowed.");
            }
        } while (!validator.isValidName(vio_sanction)); 

        
        String query = "UPDATE violation SET vio_name = ?, vio_des = ?, vio_severity = ?, vio_sanction = ? WHERE vio_id = ?";
        conf.updateRecords(query, vio_name, vio_des, vio_severity, vio_sanction, vio_id);
        
    }
    
    private void deleteViolation(){
    
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        
        System.out.print("Enter Violation ID to delete: ");
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

                System.out.print("INVALID INPUT, Enter Again: ");
                sc.next();
            }
        }

        String query = "Delete FROM violation WHERE vio_id = ?";
        conf.deleteRecords(query, vio_id);

    }
 
}