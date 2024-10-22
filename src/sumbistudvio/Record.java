package sumbistudvio;

import java.util.Scanner;

public class Record {
   
    public void addRecord() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter Student ID: ");
        int stud_id = sc.nextInt();
        System.out.print("Enter Violation ID: ");
        int vio_id = sc.nextInt();
        System.out.print("Enter Date Reported: ");
        String rec_reported = sc.next();
        sc.nextLine();
        System.out.print("Enter Date Settled: ");
        String rec_settled = sc.nextLine();
        
        String sql = "INSERT INTO record (stud_id, vio_id, rec_reported, rec_settled) VALUES (?, ?, ?, ?)";

        conf.addRecords(sql, stud_id, vio_id, rec_reported, rec_settled);   
    }
    
    private void viewRecord() {
        String query = "SELECT * FROM record";
        String[] headers = {"RECORD ID", "STUDENT ID", "VIOLATION ID", "DATE REPORTED", "DATE SETTLED"};
        String[] columns = {"rec_id", "stud_id", "vio_id", "rec_reported", "rec_settled"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }
    
    private void updateRecord(){
    
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Record ID to update: ");
        int id = sc.nextInt();
        
        sc.nextLine(); 
        System.out.print("Enter new Date Settled: ");
        String rec_settled = sc.nextLine();
         
        String query = "UPDATE record SET rec_settled = ? WHERE rec_id = ?";
        
        config conf = new config();
        conf.updateRecords(query, rec_settled, id);
        
    }
    
    private void deleteRecord(){
    
        Scanner sc = new Scanner (System.in);
        
        System.out.print("Enter Record ID to delete: ");
        int id = sc.nextInt();
        
        String query = "Delete FROM record WHERE rec_id = ?";
       
        config conf = new config();
        conf.deleteRecords(query, id);

    }
    
    public void main(String[] args) {
        
        Record rec = new Record();
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
                    rec.addRecord();
                break; 
                case 2:
                    rec.viewRecord();
                    rec.updateRecord();
                break;
                case 3:
                    rec.viewRecord();
                    rec.deleteRecord();
                    rec.viewRecord();
                    break;
                case 4:
                    rec.viewRecord();
                break;
                case 5:
                    return;
            }
            
            
        }while(true);
            
    }
    
    
    
    
}
