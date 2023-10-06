package net.colinmasterson.gamebacklogjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.colinmasterson.gamebacklogjavafx.database.DatabaseController;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    DatabaseController db = new DatabaseController();

    @FXML
    private Button addGame;

    @FXML
    private TableColumn<Game, String> console;

    @FXML
    private Button deleteGame;

    @FXML
    private TableColumn<Game, Integer> id;

    @FXML
    private TableColumn<Game, String> name;

    @FXML
    private TableColumn<Game, String> status;

    @FXML
    private TableView<Game> table;

    @FXML
    private void deleteGame(ActionEvent event) {
        event.consume();
        Game selectedGame = table.getSelectionModel().getSelectedItem();
        if(!(selectedGame == null)) {
            db.deleteGame(selectedGame.getId());

            list.remove(selectedGame);

        }
    }


    ObservableList<Game> list = FXCollections.observableArrayList(db.getGames());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Game,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));
        status.setCellValueFactory(new PropertyValueFactory<Game, String>("status"));
        console.setCellValueFactory(new PropertyValueFactory<Game, String>("console"));

        table.setItems(list);
    }
}
