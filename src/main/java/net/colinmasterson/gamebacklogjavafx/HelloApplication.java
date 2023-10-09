package net.colinmasterson.gamebacklogjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.colinmasterson.gamebacklogjavafx.database.DatabaseController;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-library.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Game Library");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DatabaseController db = new DatabaseController();
        db.createTable();


       // db.getGames();
        launch();
    }
}