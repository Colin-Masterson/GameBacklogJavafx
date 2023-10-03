package net.colinmasterson.gamebacklogjavafx.database;

import net.colinmasterson.gamebacklogjavafx.HelloApplication;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {

    public void connect(){



        try {

            String jdbcUrl = "jdbc:sqlite:games.db";
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String createTableSql = "create table games (name varchar(30), description varchar(70), console varchar(20))";

           Statement statement = connection.createStatement();
           statement.executeUpdate(createTableSql);

            System.out.println("Table Created");

        }catch (SQLException  | NullPointerException e){
            e.printStackTrace();
        }
    }


}
