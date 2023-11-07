package net.colinmasterson.gamebacklogjavafx;


import javafx.scene.control.ComboBox;
import net.colinmasterson.gamebacklogjavafx.database.DatabaseController;

public class Game {

    private final int id;
    private final String name;
    private final String console;
    private ComboBox status;

    private String[] statusList ={"Not Started", "In Progress", "Completed","Want to Get"};
    DatabaseController db = new DatabaseController();


    public Game(int id,String name, String console, String status){
        this.id = id;
        this.name = name;
        this.console = console;
        this.status = new ComboBox<>();


            this.status.getItems().addAll(statusList);

        this.status.getSelectionModel().select(status);

        this.status.setOnAction(e->{
            db.updateStatus(this.status.getSelectionModel().getSelectedItem().toString(), id);
        });

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getConsole() {
        return console;
    }

    public ComboBox getStatus() {
        return status;
    }

    public void setStatus(ComboBox status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", console='" + console + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
