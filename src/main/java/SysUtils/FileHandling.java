package SysUtils;

import ObjectClasses.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
            File file = new File(fileName);
            if (file.createNewFile()) {
                // add students info to file
                try (FileWriter writer = new FileWriter(fileName + ".txt")){
                    writer.write("Students");
                    for (Student student : students) {
                        writer.write(student.toString());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("File created");
                fileCreation = true;
            } else {
                System.out.println(fileName + ".txt already exists");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileCreation;
    }
}
