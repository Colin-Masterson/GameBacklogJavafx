package net.colinmasterson.gamebacklogjavafx.database;


import net.colinmasterson.gamebacklogjavafx.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseController {

    private Connection connection;

    private void connect() {

        try {
            String jdbcUrl = "jdbc:sqlite:games.db";
            this.connection = DriverManager.getConnection(jdbcUrl);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            connect();

            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "games", new String[]{"TABLE"});

            if (!resultSet.next()) {
                String createTableSql = "create table games (ID INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(30), console varchar(20), status varchar(20))";

                Statement statement = connection.createStatement();
                statement.executeUpdate(createTableSql);

                System.out.println("Created Games Table");

            } else {
                System.out.println("Games Table Exists");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void addGame(String name, String console, String status) {


        String addGameSql = "INSERT INTO games(name, console, status) " +
                "VALUES(?,?,?)";



        try {
            connect();
            PreparedStatement preparedStatement = connection.prepareStatement(addGameSql);



            preparedStatement.setString(1, name);
            preparedStatement.setString(2, console);
            preparedStatement.setString(3, status);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }


    }

    public List<Game> getGames(){
        List<Game> games = new ArrayList<>();
        String getGamesSql = "SELECT * from games";
        try{
        connect();
        PreparedStatement preparedStatement = connection.prepareStatement(getGamesSql);

        ResultSet output = preparedStatement.executeQuery();

        while(output.next()){
            games.add(new Game(output.getInt("ID"),output.getString("name"), output.getString("console"), output.getString("status")));
        }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }

        for (Game game : games){
            System.out.println(game);
        }
        return games;
    }

    public void deleteGame(int id){
        String deleteSql = "DELETE FROM games WHERE ID = ?";

        try{
            connect();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }

    public void deleteAllGames(){
        String deleteAllSql = "DELETE from games";

        try {
            connect();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteAllSql);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }

    public void updateStatus(String status, int id){
        String updateStatusSql = "UPDATE games SET status = ? WHERE ID = ?";

        try{
            connect();

            PreparedStatement preparedStatement = connection.prepareStatement(updateStatusSql);
            preparedStatement.setString(1,status);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }


}
