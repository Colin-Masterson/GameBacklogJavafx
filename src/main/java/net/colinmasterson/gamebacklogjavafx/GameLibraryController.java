package net.colinmasterson.gamebacklogjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import net.colinmasterson.gamebacklogjavafx.database.DatabaseController;

import java.net.URL;
import java.util.ResourceBundle;

public class GameLibraryController implements Initializable {

    DatabaseController db = new DatabaseController();

    @FXML
    public Button addGame;

    @FXML
    private Button deleteGame;

    @FXML
    private Label allGamesLabel;

    @FXML
    private Label completedGamesLabel;

    @FXML
    private TableColumn<Game, String> console;

    @FXML
    private TableColumn<Game, Integer> id;

    @FXML
    private TableColumn<Game, String> name;

    @FXML
    private TableColumn<Game, String> status;

    @FXML
    public TableView<Game> table;



    @FXML
    private void deleteGame(ActionEvent event) {
        event.consume();
        Game selectedGame = table.getSelectionModel().getSelectedItem();
        if(!(selectedGame == null)) {
            db.deleteGame(selectedGame.getId());

            list.remove(selectedGame);

        }
    }

    @FXML
    private void addGame(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-game.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            AddGameController addGameController = fxmlLoader.getController();
            addGameController.libraryAddGame = addGame;
            addGameController.gameList = list;
            addGameController.table = table;
            stage.setTitle("Add Game");
            stage.setScene(scene);
            addGame.setDisable(true);
            stage.setOnCloseRequest(e -> addGame.setDisable(false));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    ObservableList<Game> list = FXCollections.observableArrayList(db.getGames());

    private String completedGames(){
        int completed = 0;
        String labelText = "Completed Games: ";


        for (Game game: list){
            if(game.getStatus().equals("Completed")){
                completed++;
            }
        }

        return labelText + completed;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Game,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));
        status.setCellValueFactory(new PropertyValueFactory<Game, String>("status"));
        console.setCellValueFactory(new PropertyValueFactory<Game, String>("console"));



        allGamesLabel.setText("All Games: "+ list.size());
        completedGamesLabel.setText(completedGames());

        list.addListener((ListChangeListener<Game>) change -> allGamesLabel.setText("All Games: " + list.size()));
        list.addListener((ListChangeListener<Game>) change -> completedGamesLabel.setText(completedGames()));

        table.setItems(list);
    }

}
