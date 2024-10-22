package sumbistudvio;

import java.util.Scanner;

public class Violation {
    
     public void addViolation(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        
        System.out.print("Enter Violation Name: ");
        String vio_name = sc.nextLine();  
        
        System.out.print("Enter Description: ");
        String vio_des = sc.nextLine();
        
        System.out.print("Enter Severity of Violation: ");
        String vio_severity = sc.nextLine();
        
        System.out.print("Enter Sanction: ");
        String vio_sanction = sc.nextLine();

        
        String sql = "INSERT INTO violation (vio_name, vio_des, vio_severity, vio_sanction) VALUES (?, ?, ?, ?)";

        conf.addRecords(sql, vio_name, vio_des, vio_severity, vio_sanction);

    }
    
    private void viewViolation() {
        String query = "SELECT * FROM violation";
        String[] headers = {"VIOLATION ID", "VIOLATION NAME", "DESCRIPTION", "SEVERITY", "SANCTION"};
        String[] columns = {"vio_id", "vio_name", "vio_des", "vio_severity", "vio_sanction"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }
    
    
    private void updateViolation(){
    
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Violation ID to update: ");
        int id = sc.nextInt();
        
        
        System.out.print("Enter new Violation Name: ");
        String vio_name = sc.nextLine();
        
        System.out.print("Enter new Description: ");
        String vio_des = sc.nextLine();
        
        System.out.print("Enter new Severity of Violation: ");
        String vio_severity = sc.nextLine();
        
        System.out.print("Enter new Sanction: ");
        String vio_sanction = sc.nextLine();

        
        String query = "UPDATE violation SET vio_name = ?, vio_des = ?, vio_severity = ?, vio_sanction = ? WHERE vio_id = ?";
        
        config conf = new config();
        conf.updateRecords(query, vio_name, vio_des, vio_severity, vio_sanction, id);
        
    }
    
    private void deleteViolation(){
    
        Scanner sc = new Scanner (System.in);
        
        System.out.print("Enter Violation ID to delete: ");
        int id = sc.nextInt();
        
        String query = "Delete FROM violation WHERE vio_id = ?";
       
        config conf = new config();
        conf.deleteRecords(query, id);

    }
    

    public void main(String[] args) {
        
        Violation test = new Violation();
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
                    test.addViolation();
                break; 
                case 2:
                    test.viewViolation();
                    test.updateViolation();
                break;
                case 3:
                    test.viewViolation();
                    test.deleteViolation();
                    test.viewViolation();
                    break;
                case 4:
                    test.viewViolation();
                break;
                case 5:
                    return;
            }
            
            
            
        }while(true);
            
    }
     
}
