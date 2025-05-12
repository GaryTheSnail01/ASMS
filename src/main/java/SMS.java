import SysUtils.Validations;

import java.util.Scanner;

public class SMS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int response = -1;

        while (response != 0) {
            displayMenu();

            try {
                response = Validations.readPositiveInt("Enter Menu Selection: ");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }

//            switch (response) {
//
//        }
        }
    }

    public static void displayMenu() {
        System.out.println("\nStudent Management System");
        System.out.println("1. Add Student/Teacher");
        System.out.println("2. Create Course");
        System.out.println("4. Assign Course(s)");
        System.out.println("5. Search by ID");
        System.out.println("6. View Students & Teachers");
        System.out.println("7. Update Info");
        System.out.println("8. Delete Info");
        System.out.println("9. Import or Export Data");
        System.out.println("0. Exit");
    }
}
