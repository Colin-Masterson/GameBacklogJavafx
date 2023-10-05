package net.colinmasterson.gamebacklogjavafx.database;


import java.sql.*;


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
                String createTableSql = "create table games (ID INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(30), description varchar(70), console varchar(20))";

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

    public void addGame(String name, String description, String console) {


        String addGameSql = "INSERT INTO games(name, description, console) " +
                "VALUES(?,?,?)";



        try {
            connect();
            PreparedStatement preparedStatement = connection.prepareStatement(addGameSql);



            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, console);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }


    }

    public void getGames(){
        String getGamesSql = "SELECT * from games";
        try{
        connect();
        PreparedStatement preparedStatement = connection.prepareStatement(getGamesSql);

        ResultSet output = preparedStatement.executeQuery();

            System.out.println("ID" +"\t"+ "Name" + "\t" + "Desc" + "\t" + "Console");
            while (output.next()){
                int ID = output.getInt("ID");
                String name = output.getString("name");
                String desc = output.getString("description");
                String console = output.getString("console");

                System.out.println(ID + "\t" + name + "\t" + desc + "\t" + console);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }


}
