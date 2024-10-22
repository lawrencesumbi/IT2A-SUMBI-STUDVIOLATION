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

        String sql = "INSERT INTO record (stud_id, vio_id) VALUES (?, ?)";

        conf.addRecords(sql, stud_id, vio_id);
        
        
        
    }
    
    
    public void main(String[] args) {
        
        Record test = new Record();
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
                    test.addRecord();
                break; 
                case 2:
                    
                break;
                case 3:
                    
                    break;
                case 4:
                    
                break;
                case 5:
                    return;
            }
            
            
        }while(true);
            
    }
    
    
    
    
}
