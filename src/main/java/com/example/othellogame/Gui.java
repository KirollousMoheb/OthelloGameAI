package com.example.othellogame;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Gui {
    private final Board board=new Board();
    private final Stage primaryStage;

    public Gui(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    public Scene getMainScene() {
        return mainScene;
    }

    private Scene mainScene;

    public void loadMainScreen(){
        // Create a welcome label and a choice label
        Label welcomeLabel = new Label("Welcome to Othello Game!");
        Label choiceLabel = new Label("Choose a game mode:");

        // Create a ComboBox for game mode selection
        ComboBox<String> gameModeComboBox = new ComboBox<>();
        gameModeComboBox.setItems(FXCollections.observableArrayList("Play against human", "Play against AI","AI vs AI"));
        // Pre-select the first option
        gameModeComboBox.getSelectionModel().selectFirst();


        // Create a ComboBox for game mode selection
        ComboBox<String> gameDifficultyComboBox = new ComboBox<>();
        gameDifficultyComboBox.setItems(FXCollections.observableArrayList("Easy", "Hard"));
        // Pre-select the first option
        gameDifficultyComboBox.getSelectionModel().selectFirst();



        // Create a container for the welcome label, the choice label, and the ComboBox
        VBox welcomeBox = new VBox(welcomeLabel, choiceLabel, gameModeComboBox,gameDifficultyComboBox);
        welcomeBox.setAlignment(Pos.CENTER);
        welcomeBox.setSpacing(10);

        // Create a container for the start button
        Button startButton = new Button("Start");
        startButton.setOnAction(event -> {
            String selectedGameMode = gameModeComboBox.getValue();
            String selectedDifficulty=gameDifficultyComboBox.getValue();
            if(selectedDifficulty.equals("Easy")){
                board.setDepth(9);
            } else if (selectedDifficulty.equals("Hard")) {
                board.setDepth(3);
            }
            if(selectedGameMode.equals("Play against AI")){
                board.setAIMode(1);
            }else if(selectedGameMode.equals("Play against human")){
                board.setAIMode(0);
            }else{
                board.setAIMode(2);

            }

            loadGameScreen(primaryStage);

        });

        // Create a container for the start button
        HBox startButtonBox = new HBox(startButton);
        startButtonBox.setAlignment(Pos.CENTER);
        VBox startBox = new VBox(welcomeBox, startButtonBox);

        // Create a container for the start button and the welcome box
        startBox.setAlignment(Pos.CENTER);
        mainScene=new Scene(startBox, board.getWINDOW_WIDTH(), board.getWINDOW_HEIGHT());

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    public void loadGameScreen(Stage primaryStage){
            // Create a container for the score label
            HBox scoreLabelBox = new HBox();
            scoreLabelBox.setAlignment(Pos.CENTER);
            scoreLabelBox.getChildren().add(board.getScoreLabel());

            // Create a container for the player label
            HBox playerLabelBox = new HBox();
            playerLabelBox.setAlignment(Pos.CENTER);
            playerLabelBox.getChildren().add(board.getPlayerLabel());

            // Create a container for the reset button
            Button resetButton = new Button("Reset Game");
            resetButton.setOnAction(event -> board.resetGame());
            HBox resetButtonBox = new HBox(resetButton);
            resetButtonBox.setAlignment(Pos.CENTER);
            // Create a container for the back button
            Button backButton = new Button("Back");
            backButton.setOnAction(event -> {
                board.resetGame();
                primaryStage.setScene(getMainScene());
                primaryStage.show();
            });

            // Create a container for the back button
            HBox backButtonBox = new HBox(backButton);
            backButtonBox.setAlignment(Pos.CENTER_LEFT);

            Button aiVersusAi=new Button("Start AI vs AI");
                aiVersusAi.setOnMouseClicked(event -> board.startAIvsAI());

            // Create a container for the score label, player label, and reset button
             VBox scoreBox;
            if(board.getMode()==2){
                scoreBox  = new VBox(scoreLabelBox, new Label("  "), playerLabelBox, resetButtonBox,aiVersusAi);

            }else{
              scoreBox  = new VBox(scoreLabelBox, new Label("  "), playerLabelBox, resetButtonBox);

            }

            scoreBox.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            // Add the back button container to the score box
            scoreBox.getChildren().add(backButtonBox);
            scoreBox.setAlignment(Pos.CENTER);
            scoreBox.setMaxHeight(120);
            scoreBox.setPrefHeight(120);

            board.drawBoard();
            board.addEvents();

            // Add the containers to the main VBox
            VBox vbox = new VBox(scoreBox, board.getRoot());
            Scene gameScene = new Scene(vbox, board.getWINDOW_WIDTH(), board.getWINDOW_HEIGHT() + 120);
            primaryStage.setScene(gameScene);
            primaryStage.show();

    }
}
