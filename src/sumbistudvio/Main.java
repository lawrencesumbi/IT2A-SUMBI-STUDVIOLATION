package sumbistudvio;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);
        String resp;

        do {
            System.out.println("-----------------------------");
            System.out.println("*     STUDENT VIOLATION     *");
            System.out.println("*          SYSTEM           *");
            System.out.println("-----------------------------");
            System.out.println("*       SELECT CHOICE       *");
            System.out.println("*       1. STUDENT          *");
            System.out.println("*       2. VIOLATION        *");
            System.out.println("*       3. RECORD           *");
            System.out.println("*       4. EXIT             *");
            System.out.println("-----------------------------");

            int choice = 0;

            do {
                System.out.print("Input Choice: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    System.out.println("\tInvalid input, only integers are allowed.");
                    scanner.next();
                    continue;
                }
                if (!(choice >= 1 && choice <= 4)) {
                    System.out.println("\tInvalid number, input (1-4) only.");
                }
                
            } while (!(choice >= 1 && choice <= 4));

            switch (choice) {
                case 1:
                    Student student = new Student();
                    student.main(args);
                    break;
                case 2:
                    Violation violation = new Violation();
                    violation.main(args);
                    break;
                case 3:
                    Record record = new Record();
                    record.main(args);
                    break;
                case 4:
                    System.out.println("\nThank you for using the Student Violation System. Have a great day!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\tInvalid Choice");
                    break;
            }

            System.out.print("Do you want to continue? (yes/no): ");
            resp = scanner.next();
            
            while (!resp.equalsIgnoreCase("yes") && !resp.equalsIgnoreCase("no")) {
                System.out.print("Invalid Input, Enter Again: ");
                resp = scanner.next();
            }
            
        } while (resp.equalsIgnoreCase("yes"));
        
        System.out.println("\nThank you for using the Student Violation System. Have a great day!");
    }
}
