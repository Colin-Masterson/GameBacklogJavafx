package net.colinmasterson.gamebacklogjavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddGameController {


    public Button addGame;

    @FXML
    private TextField console;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<?> status;

    @FXML
    private void test(){
        addGame.setDisable(false);
    }


}