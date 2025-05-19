package SysUtils;

import ObjectClasses.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FileHandling {
    public static boolean createStudentFile(String fileName, List<Student> students){
        String trimmedFileName = fileName.trim();
        boolean fileCreation = false;
        char[] invalidChars = {'\\', '/', ':', '*', '?', '"', '<', '>', '|'};
        // Handle if the file name has invalid characters
        for (char c : invalidChars) {
            if (trimmedFileName.contains(String.valueOf(c))) {
                System.out.println("Invalid character used: " + c);
                return fileCreation; // false
            }
        }

        if (trimmedFileName.isEmpty()){
            System.out.println("You must name the file.");
            return fileCreation;
        }

        try {
            File file = new File(fileName + ".txt");
            if (file.createNewFile()) {
                // add students info to file
                try (FileWriter writer = new FileWriter(fileName + ".txt")){
                    writer.write("Students:\n");
                    for (Student student : students) {
                        writer.write(student.toString());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(file.getAbsolutePath());
                fileCreation = true;
            } else {
                System.out.println(fileName + ".txt already exists");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileCreation;
    }

    public static boolean readImportFile(String fileName) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        List<Student> students = new ArrayList<>();

        boolean success = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Student student = parseStudent(line);
                if (student != null) {
                    students.add(student);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return success;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!students.isEmpty()) {
            for (Student student : students) {
                db.insertStudent(student); // Insert the student into the db
            }
            success = true;
        } else {
            System.out.println("File is empty");
            return success;
        }
        return success;
    }

    public static Student parseStudent(String line) {
        try {
            String[] attributes = line.split("-");
            // Only taking student info then generating a new ID
            // Format: name-grade-age-email
            if (attributes.length == 4) {
                String name = attributes[0].trim();
                int grade = parseInt(attributes[1].trim());
                int age = parseInt(attributes[2].trim());
                String email = attributes[3].trim();
                String id = IDGeneration.GenerateID();

                return new Student(name, grade, age, email, id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing line: " + line);
        }
        return null;
    }
}
