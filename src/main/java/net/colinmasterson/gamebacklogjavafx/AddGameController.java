package net.colinmasterson.gamebacklogjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import net.colinmasterson.gamebacklogjavafx.database.DatabaseController;

import java.sql.SQLOutput;

public class AddGameController {

    DatabaseController db = new DatabaseController();


    public Button libraryAddGame;

    @FXML
    private Button addGame;

    @FXML
    private TextField console;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<?> status;

    public ObservableList<Game> gameList;
    public TableView<Game> table;

    @FXML
    private void addGame(ActionEvent event){
        if(name.getLength()>0 && console.getLength()>0) {
            db.addGame(name.getText(), console.getText(), "Test");

            gameList.add(db.getGames().get(db.getGames().size()-1));


            ((Node)(event.getSource())).getScene().getWindow().hide();
        }

        libraryAddGame.setDisable(false);
    }


}