package com.example.othellogame;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int TILE_SIZE = 50;

    public Board() {
    }

    private final int BOARD_SIZE = 8;

    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }

    public int getWINDOW_WIDTH() {
        return WINDOW_WIDTH;
    }

    public int getWINDOW_HEIGHT() {
        return WINDOW_HEIGHT;
    }

    public Group getRoot() {
        return root;
    }

    private final int WINDOW_WIDTH = TILE_SIZE * BOARD_SIZE;
    private final int WINDOW_HEIGHT = TILE_SIZE * BOARD_SIZE;
    private final Color TILE_COLOR = Color.GREEN;
    private final Color PLAYER1_COLOR = Color.WHITE;
    private final Color PLAYER2_COLOR = Color.BLACK;
    private final int[][] BOARD = new int[BOARD_SIZE][BOARD_SIZE];
    private final Group root = new Group();

    private int currentPlayer = 2;
    private int player1Score=0;
    private int player2Score=0;

    private int mode = 0;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    private int depth=1;

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public Label getPlayerLabel() {
        return playerLabel;
    }

    private final Label scoreLabel = new Label("White Score: 2|Black Score: 2");
    private final Label playerLabel = new Label("Current player: Black Player");

    public int getMode() {
        return mode;
    }

    public void startAIvsAI(){

        if(mode==2){
            makeAIMove();
        }
    }

    public void drawBoard(){
        // Create the game board
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE, TILE_COLOR);
                tile.setStroke(Color.BLACK);
                tile.setX(j * TILE_SIZE);
                tile.setY(i * TILE_SIZE);
                // Add the EventHandler to the tile
                tile.setOnMouseClicked(tileClicked);
                root.getChildren().add(tile);
            }
        }

        // Initialize the starting pieces on the board
        BOARD[3][3] = 1;
        BOARD[4][4] = 1;
        BOARD[3][4] = 2;
        BOARD[4][3] = 2;

        // Add the pieces to the board
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (BOARD[i][j] == 1) {
                    Circle piece = new Circle(TILE_SIZE / 2 - 5, PLAYER1_COLOR);
                    piece.setStroke(Color.BLACK);
                    piece.setCenterX(j * TILE_SIZE + TILE_SIZE / 2);
                    piece.setCenterY(i * TILE_SIZE + TILE_SIZE / 2);
                    root.getChildren().add(piece);
                } else if (BOARD[i][j] == 2) {
                    Circle piece = new Circle(TILE_SIZE / 2 - 5, PLAYER2_COLOR);
                    piece.setStroke(Color.BLACK);
                    piece.setCenterX(j * TILE_SIZE + TILE_SIZE / 2);
                    piece.setCenterY(i * TILE_SIZE + TILE_SIZE / 2);
                    root.getChildren().add(piece);
                }
            }
        }
        drawValidMoves();
    }
}
