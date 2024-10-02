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
    
    
    public static void main(String[] args) {
        
        SumbiStudVio test = new SumbiStudVio();
        Scanner input = new Scanner(System.in);
        
        System.out.println("1. ADD");
        System.out.println("2. VIEW");
        System.out.println("3. UPDATE");
        System.out.println("4. DELETE");
        System.out.println("5. EXIT");
        
        System.out.print("Enter Action: ");
        int action = input.nextInt();
        
        switch(action){
            case 1:
                test.addStudents();
            break; 
        }
        
    }
    
}
