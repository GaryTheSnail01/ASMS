package SysUtils;

import ObjectClasses.Student;
import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection instance;

    private static Connection conn;

    private DatabaseConnection(){
        String url = "jdbc:h2:mem:";

        try {
            this.conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseConnection getInstance(){
        if (instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public static void createTable(){
        String sql = "CREATE TABLE students (id VARCHAR(50) PRIMARY KEY, name VARCHAR(100), age INT, email VARCHAR(200))";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertStudent(Student student){
        String sql = "INSERT INTO students (id, name, age, email) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getAge());
            stmt.setString(4, student.getEmail());

            stmt.executeUpdate();
            System.out.println("Success, student has been added.\n" + student.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Object searchID(String inputID){
        String sql = "SELECT * FROM students WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, inputID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");

                return new Student(name, age, email, inputID);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
