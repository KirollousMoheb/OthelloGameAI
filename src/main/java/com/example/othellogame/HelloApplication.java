package com.example.othellogame;
import javafx.application.Application;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Othello Game");
        Gui gui = new Gui(primaryStage);
        gui.loadMainScreen();
    }

}
