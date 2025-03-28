package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection connection;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection connection) {
        this.connection = connection;
    }

    private void init() {
        try (InputStream input = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(input);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO items(name,created) values(?, ?)")) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setTimestamp(1, Timestamp.valueOf(item.getCreated()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add item", e);
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE items\n"
                + "SET name = ?, created = ?\n"
                + "WHERE id = ?;")) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setTimestamp(1, Timestamp.valueOf(item.getCreated()));
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE from items\n"
                + "WHERE id = ?;")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete item with id: " + id, e);
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from items\n");
        ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCreated(rs.getTimestamp("created").toLocalDateTime());
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find all: ", e);
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from items where name = ?");
             ResultSet rs = preparedStatement.executeQuery()) {
             preparedStatement.setString(1, key);
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCreated(rs.getTimestamp("created").toLocalDateTime());
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find by name: " + key, e);
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = new Item();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from items where id = ?");
             ResultSet rs = preparedStatement.executeQuery()) {
            preparedStatement.setInt(1, id);
            while (rs.next()) {
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCreated(rs.getTimestamp("created").toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find by id: " + id, e);
        }
        return item;
    }
}