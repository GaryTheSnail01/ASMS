import ObjectClasses.Student;
import SysUtils.*;

import java.util.Scanner;

public class SMS {
    public static void main(String[] args) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        DatabaseConnection.createTable();

        Scanner scanner = new Scanner(System.in);
        int response = -1;

        while (response != 0) {
            displayMenu();

            try {
                response = Validations.readPositiveInt("Enter Menu Selection: ");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }

            switch (response) {
                case 1:
                    handleAddStudent();
                    break;
                case 2:
                    handleSearchID();
                    break;
                case 3:
//                    handleViewStudents();
//                    break;
                case 4:
//                    handleEditStudent();
//                    break;
                case 5:
//                    handleDeleteStudent();
//                    break;
                case 6:
//                    handleImportFile();
//                    break;
                case 7:
//                    handleExportFile();
//                    break;
                case 0:
                    System.out.println("Closing program... Goodbye!");
                    break;
                default:
                    System.out.println();
                    break;
            }
        }
    }

    public static void displayMenu() {
        System.out.println("\nStudent Management System");
        System.out.println("1. Add Student");
        System.out.println("2. Search Student by ID");
        System.out.println("3. View Students");
        System.out.println("4. Edit Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Import Students File");
        System.out.println("7. Export Students File");
        System.out.println("0. Exit");
    }

    public static void handleAddStudent() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        String name = Validations.readNonEmptyString("Enter student's name: ");
        int age = Validations.readPositiveInt("Enter student's age: ");
        String email = Validations.readEmail("Enter student's email: ");
        String id = IDGeneration.GenerateID();

        Student student = new Student(name, age, email, id);
        db.insertStudent(student);
    }

    public static void handleSearchID() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        String id = Validations.readNonEmptyString("Enter student ID: ");

        Object student = db.searchID(id);
        if (student != null) {
            System.out.println(student.toString());
        } else {
            System.out.println("ID not found.");
        }
    }
}
