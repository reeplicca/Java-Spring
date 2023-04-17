package kz.bitlab.springmvc;

import antlr.preprocessor.PreprocessorTokenTypes;

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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/spring-mvc-database","postgres","123ckjyf");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addItem(Item item) {
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into items(name,price,amount,level) values (?,?,?,?)");
            stmt.setString(1,item.getName());
            stmt.setInt(2,item.getPrice());
            stmt.setInt(3,item.getAmount());
            stmt.setString(4, item.getLevel());

            rows = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("select * from items");
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getLong("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getInt("price"));
                item.setAmount(resultSet.getInt("amount"));
                item.setLevel(resultSet.getString("level"));
                items.add(item);
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    public static Item getItem(Long id) {
        Item item = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("select * from items where id = ?");
            stmt.setLong(1,id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                item = new Item();
                item.setId(resultSet.getLong("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getInt("price"));
                item.setAmount(resultSet.getInt("amount"));
                item.setLevel(resultSet.getString("level"));
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return item;
    }

    public static boolean updateItem(Item updItem) {
        int rows = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement("update items set name=?,price=?,amount=?,level=?where id=?");
            stmt.setString(1,updItem.getName());
            stmt.setInt(2,updItem.getPrice());
            stmt.setInt(3,updItem.getAmount());
            stmt.setString(4,updItem.getLevel());
            stmt.setLong(5,updItem.getId());

            rows = stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static boolean deleteItem(Long id) {
        int rows = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement("delete from items where id = ?");
            stmt.setLong(1,id);

            rows = stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }
}