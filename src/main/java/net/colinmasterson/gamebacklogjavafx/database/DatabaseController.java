package net.colinmasterson.gamebacklogjavafx.database;

import net.colinmasterson.gamebacklogjavafx.HelloApplication;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {

    private Connection connection;

    public void connect() {

        try {
            String jdbcUrl = "jdbc:sqlite:games.db";
            this.connection = DriverManager.getConnection(jdbcUrl);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            String createTableSql = "create table games (name varchar(30), description varchar(70), console varchar(20))";

            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSql);

            System.out.println("Table Created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
