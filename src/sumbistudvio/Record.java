package sumbistudvio;

import java.util.Scanner;

public class Record {
   
    public void addRecord() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        Student stud = new Student();
        Violation vio = new Violation();

        stud.viewStudents();
        System.out.print("Enter Student ID to add: ");
        int stud_id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT stud_id FROM student WHERE stud_id = ?", stud_id)) == 0){
            System.out.print("ID don't exist, Enter Again: ");
            stud_id = sc.nextInt();
        }
        
        vio.viewViolation();
        System.out.print("Enter Violation ID to add: ");
        int vio_id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT vio_id FROM violation WHERE vio_id = ?", vio_id)) == 0){
            System.out.print("ID don't exist, Enter Again: ");
            vio_id = sc.nextInt();
        }
        
        System.out.print("Enter Date Reported: ");
        String rec_reported = sc.next();
        sc.nextLine();
        System.out.print("Enter Date Settled: ");
        String rec_settled = sc.nextLine();
        
        String sql = "INSERT INTO record (stud_id, vio_id, rec_reported, rec_settled) VALUES (?, ?, ?, ?)";

        conf.addRecords(sql, stud_id, vio_id, rec_reported, rec_settled);   
    }
    
    private void viewRecord() {
        String query = "SELECT record.rec_id, student.stud_name, violation.vio_name, record.rec_reported, record.rec_settled "
                + "FROM record "
                + "JOIN student ON record.stud_id = student.stud_id "
                + "JOIN violation ON record.vio_id = violation.vio_id";
        String[] headers = {"RECORD ID", "STUDENT NAME", "VIOLATION NAME", "DATE REPORTED", "DATE SETTLED"};
        String[] columns = {"rec_id", "stud_name", "vio_name", "rec_reported", "rec_settled"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }
    
    private void searchStudent() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        Student stud = new Student();
        
        stud.viewStudents();
        System.out.print("Enter Student ID to search: ");
        int stud_id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT stud_id FROM student WHERE stud_id = ?", stud_id)) == 0){
            System.out.print("ID don't exist, Enter Again: ");
            stud_id = sc.nextInt();
        }
        
        String query = "SELECT record.rec_id, student.stud_name, violation.vio_name, record.rec_reported, record.rec_settled "
                + "FROM record "
                + "JOIN student ON record.stud_id = student.stud_id "
                + "JOIN violation ON record.vio_id = violation.vio_id "
                + "WHERE record.stud_id = " + stud_id;
        String[] headers = {"RECORD ID", "STUDENT NAME", "VIOLATION NAME", "DATE REPORTED", "DATE SETTLED"};
        String[] columns = {"rec_id", "stud_name", "vio_name", "rec_reported", "rec_settled"};

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
            System.out.println("1. ADD RECORD");
            System.out.println("2. UPDATE RECORD");
            System.out.println("3. DELETE RECORD");
            System.out.println("4. VIEW ALL RECORDS");
            System.out.println("5. SEARCH STUDENT");
            System.out.println("6. EXIT");

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
                    break;
                case 4:
                    rec.viewRecord();
                break;
                case 5:
                    rec.searchStudent();
                break;
                case 6:
                    return;
            }
            
            
        }while(true);
            
    }
    
    
    
    
}
