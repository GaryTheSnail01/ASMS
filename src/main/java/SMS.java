import ObjectClasses.Student;
import SysUtils.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SMS {
    public static void main(String[] args) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.createTable();
        FileHandling.readImportFile("import.txt"); // Importing student data from import file on startup

        int response = -1;

        while (response != 0) {
            displayMenu();

            response = Validations.readPositiveInt("Enter Menu Selection: ");

            switch (response) {
                case 1:
                    handleAddStudent();
                    break;
                case 2:
                    handleSearchID();
                    break;
                case 3:
                    handleViewStudents();
                    break;
                case 4:
                    handleEditStudent();
                    break;
                case 5:
                    handleDeleteStudent();
                    break;
                case 6:
                    handleFilterByGrade();
                    break;
                case 7:
                    handleImportFile();
                    break;
                case 8:
                    handleExportFile();
                    break;
                case 0:
                    System.out.println("Closing program... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
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
        System.out.println("6. Filter by Grade");
        System.out.println("7. Import Students File");
        System.out.println("8. Export Students File");
        System.out.println("0. Exit");
    }

    public static void handleAddStudent() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        String name = Validations.readNonEmptyString("Enter student's name: ");
        int grade = Validations.readIntInRange("Enter student's grade: ", 1, 12);
        int age = Validations.readPositiveInt("Enter student's age: ");
        String email = Validations.readEmail("Enter student's email: ");
        String id = IDGeneration.GenerateID();

        Student student = new Student(name, grade, age, email, id);
        db.insertStudent(student);
    }

    public static void handleSearchID() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        String id = Validations.readNonEmptyString("Enter student ID: ");

        Student student = db.searchID(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("ID not found.");
        }
    }

    public static void handleViewStudents() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        List<Student> students = db.getAllStudents();

        // Split the students list in two
        int middleStudentsIndex = students.size() / 2;
        List<Student> students1 = new ArrayList<>(students.subList(0, middleStudentsIndex));
        List<Student> students2 = new ArrayList<>(students.subList(middleStudentsIndex, students.size()));

        Runnable printFirstHalfStudents = () -> {
            students1.forEach(student -> {
                System.out.println("Thread 1... " + student.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        };

        Runnable printSecondHalfStudents = () -> {
            students2.forEach(student -> {
                System.out.println("Thread 2... " + student.toString());
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        };

        Thread thread1 = new Thread(printFirstHalfStudents);
        Thread thread2 = new Thread(printSecondHalfStudents);

        thread1.start();
        thread2.start();

        // Main thread waits for threads to complete
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void handleEditStudent() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        String response = "";

        while (!response.equals("0")) {
            System.out.println("Editing a student...");
            response = Validations.readNonEmptyString("Enter student ID or '0' to return to the main menu: ");

            if (response.equals("0")) break; // Break the while loop before searching for an ID of 0

            Student student = db.searchID(response);
            boolean editing = true;
            boolean updated = false;

            if (student != null) {
                System.out.println(student);

                while (editing) {
                    String attribute = Validations.readNonEmptyString("Enter attribute to change or 'back' to stop editing (name/grade/age/email): ");

                    switch (attribute.toLowerCase()) {
                        case "name":
                            String newName = Validations.readNonEmptyString("Enter name: ");
                            student.setName(newName);
                            updated = true;
                            break;
                        case "grade":
                            int newGrade = Validations.readPositiveInt("Enter grade: ");
                            student.setGrade(newGrade);
                            updated = true;
                            break;
                        case "age":
                            int newAge = Validations.readPositiveInt("Enter age: ");
                            student.setAge(newAge);
                            updated = true;
                            break;
                        case "email":
                            String newEmail = Validations.readEmail("Enter email: ");
                            student.setEmail(newEmail);
                            updated = true;
                            break;
                        case "id":
                            System.out.println("IDs cannot be changed...");
                            break;
                        case "back":
                            // Prevents unnecessary update if user updated a student prior to entering 'back'
                            updated = false;
                            editing = false;
                            break;
                        default:
                            System.out.println("Invalid attribute.");
                            break;
                    }
                    // If updated is true then we send our updated student to replace the original
                    if (updated) {
                        db.editStudent(student);
                    }
                }
            } else {
                System.out.println("Student not found.");
            }
        }
    }

    public static void handleDeleteStudent() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        String response = "";

        while (!response.equals("0")) {
            response = Validations.readNonEmptyString("Enter student ID or '0' to return to the main menu: ");
            if (response.equals("0")) break;

            Student student = db.searchID(response);
            if (student != null) {
                boolean confirmDelete = true;

                while (confirmDelete) {
                    System.out.println(student);
                    int input = Validations.readPositiveInt("Enter '0' to cancel this action or '1' to confirm deletion: ");

                    if (input == 1) {
                        db.deleteStudent(student);
                        confirmDelete = false;
                    } else if (input == 0) {
                        System.out.println("Canceled deletion...");
                        confirmDelete = false;
                    } else {
                        System.out.println("Invalid input.");
                    }
                }
            } else {
                System.out.println("Student not found.");
            }
        }
    }

    public static void handleFilterByGrade() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        List<Student> students = db.getAllStudents();

        int grade = Validations.readIntInRange("Enter grade: ", 1, 12);

        long sum = students.stream()
                .filter(student -> student.getGrade() == grade)
                .count();

        if (sum > 0) {
            List<Student> filteredStudents = students.stream()
                    .filter(student -> student.getGrade() == grade)
                    .sorted(Comparator.comparing(Student::getName)) // Sort students by name alphabetically
                    .collect(Collectors.toList());

            System.out.println("Total students in grade " + grade + ": " + sum);
            System.out.println("List of sorted students:");
            filteredStudents.forEach(System.out::println);
        } else {
            System.out.println("No students found.");
        }

    }

    public static void handleExportFile() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        List<Student> students = db.getAllStudents();

        System.out.println("Exporting student data...");
        String fileName = Validations.readNonEmptyString("Enter file name: ");

        boolean success = FileHandling.createStudentFile(fileName, students);

        if (success) {
            System.out.println("File created.");
        } else {
            System.out.println("Something went wrong, please try again.");
        }
    }

    public static void handleImportFile() {
        System.out.println("Importing student data...");
        String fileName = Validations.readNonEmptyString("Enter file name: ");
        fileName = fileName + ".txt";

        boolean success = FileHandling.readImportFile(fileName);

        if (success) {
            System.out.println("Student data successfully imported.");
        } else {
            System.out.println("Something went wrong");
        }


    }
}
