package net.colinmasterson.gamebacklogjavafx;

import java.util.ArrayList;

public class Game {

    private int id;
    private String name;
    private String console;
    private String status;

    public Game(int id,String name, String console, String status){
        this.id = id;
        this.name = name;
        this.console = console;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
