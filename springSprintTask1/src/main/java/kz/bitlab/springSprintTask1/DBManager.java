package kz.bitlab.springSprintTask1;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;
    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/spring-sprint-task-one-database","postgres","123ckjyf");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addStudent(Student student) {
        int rows = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement("insert into students(name,surname,exam,mark) values (?,?,?,?)");
            stmt.setString(1,student.getName());
            stmt.setString(2,student.getSurname());
            stmt.setInt(3,student.getExam());
            stmt.setString(4, student.getMark());

            rows = stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("select * from students");

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setExam(resultSet.getInt("exam"));
                student.setMark(resultSet.getString("mark"));
                students.add(student);
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public static Student getStudent(Long id) {
        Student student = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("select * from students where id = ?");
            stmt.setLong(1,id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setExam(resultSet.getInt("exam"));
                student.setMark(resultSet.getString("mark"));
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return student;
    }

    public static boolean updateStudent(Student student) {
        int rows = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement("update students set name=?,surname=?,exam=?,mark=? where id=?");
            stmt.setString(1,student.getName());
            stmt.setString(2,student.getSurname());
            stmt.setInt(3,student.getExam());
            stmt.setString(4,student.getMark());
            stmt.setLong(5,student.getId());

            rows = stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static boolean deleteStudent(Long id) {
        int rows = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement("delete from students where id = ?");
            stmt.setLong(1,id);

            rows = stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }
}
