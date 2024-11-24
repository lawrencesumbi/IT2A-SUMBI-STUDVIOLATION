package sumbistudvio;

import java.util.Scanner;

public class Violation {
    
    public static void main(String[] args) {
        
        Violation vio = new Violation();
        Scanner input = new Scanner(System.in);
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

            switch(action){
                case 1:
                    vio.addViolation();
                break; 
                case 2:
                    vio.viewViolation();
                    vio.updateViolation();
                    vio.viewViolation();
                break;
                case 3:
                    vio.viewViolation();
                    vio.deleteViolation();
                    vio.viewViolation();
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
        
        System.out.println("\nThank you for using the Student Violation System. Have a great day!");
        System.exit(0);
    }
    
    public void addViolation(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        String vio_name;
        while (true) {
            System.out.print("Enter Violation Name: ");
            vio_name = sc.nextLine();
            if (vio_name.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("\tInvalid name, only alphabetic characters are allowed.");
        }
        
        String vio_des;
        while (true) {
            System.out.print("Enter Description: ");
            vio_des = sc.nextLine();
            if (vio_des.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("\tInvalid description, only alphabetic characters are allowed.");
        }
        
        String vio_severity;
        while (true) {
            System.out.print("Enter Severity of Violation: ");
            vio_severity = sc.nextLine();
            if (vio_severity.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("\tInvalid severity, only alphabetic characters are allowed.");
        }
        
        String vio_sanction;
        while (true) {
            System.out.print("Enter Sanction: ");
            vio_sanction = sc.nextLine();
            if (vio_sanction.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("\tInvalid sanction, only alphabetic characters are allowed.");
        }

        
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

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }
        
        sc.nextLine();
        
        String vio_name;
        while (true) {
            System.out.print("Enter New Violation Name: ");
            vio_name = sc.nextLine();
            if (vio_name.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("\tInvalid name, only alphabetic characters are allowed.");
        }
        
        String vio_des;
        while (true) {
            System.out.print("Enter New Description: ");
            vio_des = sc.nextLine();
            if (vio_des.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("\tInvalid description, only alphabetic characters are allowed.");
        }
        
        String vio_severity;
        while (true) {
            System.out.print("Enter New Severity of Violation: ");
            vio_severity = sc.nextLine();
            if (vio_severity.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("\tInvalid severity, only alphabetic characters are allowed.");
        }
        
        String vio_sanction;
        while (true) {
            System.out.print("Enter New Sanction: ");
            vio_sanction = sc.nextLine();
            if (vio_sanction.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("\tInvalid sanction, only alphabetic characters are allowed.");
        }

        
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

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }

        String query = "Delete FROM violation WHERE vio_id = ?";
        conf.deleteRecords(query, vio_id);

    }
 
}