package sumbistudvio;

import java.util.Scanner;

public class Main {
    
      public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);
        String resp;
       
        do{
        System.out.println("-----------------------------");
        System.out.println("|     STUDENT VIOLATION     |");
        System.out.println("|          SYSTEM           |");
        System.out.println("-----------------------------");
        System.out.println("|       SELECT CHOICE       |");
        System.out.println("|       1. STUDENT          |");
        System.out.println("|       2. VIOLATION        |");
        System.out.println("|       3. RECORD           |");
        System.out.println("|       4. EXIT             |");
        System.out.println("-----------------------------");
        
        System.out.print("Input Action: ");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                Student test = new Student(); 
                test.main(args);  
                break;
            case 2:
                Violation vio = new Violation(); 
                vio.main(args);  
               break;
            case 3:
                Record rec = new Record();
                rec.main(args);
                break;
            case 4:
               System.out.println("Thank you for using my system.");
               System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
       System.out.print("Do you want to continue?: ");
         resp = scanner.next();

       }while(resp.equalsIgnoreCase("yes"));
        System.out.println("Thank you for using my system.");
    }
        
 }
