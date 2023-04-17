package kz.bitlab.springTask4;

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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/spring-task4","postgres","123ckjyf");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addTask(Task task) {
        int rows = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement("insert into tasks(name,description,deadlinedate,iscompleted) values (?,?,?,?)");
            stmt.setString(1,task.getName());
            stmt.setString(2,task.getDescription());
            stmt.setString(3, task.getDeadlineDate());
            stmt.setBoolean(4,task.isCompleted());

            rows = stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static ArrayList<Task> getAllTask() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("select * from tasks");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadlineDate(resultSet.getString("deadlinedate"));
                task.setCompleted(resultSet.getBoolean("iscompleted"));
                tasks.add(task);
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tasks;
    }

    public static Task getTask(Long id) {
        Task task = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("select * from tasks where id = ?");
            stmt.setLong(1,id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadlineDate(resultSet.getString("deadlinedate"));
                task.setCompleted(resultSet.getBoolean("iscompleted"));
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return task;
    }

    public static boolean updateTask(Task updItem) {
        int rows = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement("update items set name=?,description=?,deadlinedate=?,iscompleted=?where id=?");
            stmt.setString(1,updItem.getName());
            stmt.setString(2,updItem.getDescription());
            stmt.setString(3,updItem.getDeadlineDate());
            stmt.setBoolean(4,updItem.isCompleted());
            stmt.setLong(5,updItem.getId());

            rows = stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static boolean deleteTask(Long id) {
        int rows = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement("delete from tasks where id = ?");
            stmt.setLong(1,id);

            rows = stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }
}
