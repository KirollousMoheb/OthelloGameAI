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
        public void drawValidMoves(){
        // Add circles to the board representing valid moves for the current player
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (isValidMove(i, j, currentPlayer)) {
                    Circle validMove = new Circle(TILE_SIZE / 2 - 10, Color.TRANSPARENT);
                    validMove.setStroke(currentPlayer == 1 ? PLAYER1_COLOR : PLAYER2_COLOR);
                    validMove.setStrokeWidth(5);
                    validMove.setCenterX(j * TILE_SIZE + TILE_SIZE / 2);
                    validMove.setCenterY(i * TILE_SIZE + TILE_SIZE / 2);
                    validMove.setOnMouseClicked(tileClicked);
                    root.getChildren().add(validMove);
                }
            }
        }
    }
    public boolean isValidMove(int row, int col, int player) {
        // Check if the tile is already occupied
        if (BOARD[row][col] != 0) {
            return false;
        }
        // Check if the move is adjacent to an opponent's tile
        boolean adjacentToOpponent = false;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int r = row + i;
                int c = col + j;
                if (r >= 0 && r < BOARD_SIZE && c >= 0 && c < BOARD_SIZE && BOARD[r][c] == 3 - player) {
                    // Check if the line from the move to the next tile of the same player is valid
                    while (r >= 0 && r < BOARD_SIZE && c >= 0 && c < BOARD_SIZE && BOARD[r][c] == 3 - player) {
                        r += i;
                        c += j;
                    }
                    if (r >= 0 && r < BOARD_SIZE && c >= 0 && c < BOARD_SIZE && BOARD[r][c] == player) {
                        adjacentToOpponent = true;
                        break;
                    }
                }
            }
        }
        return adjacentToOpponent;
    }
    public void handleTileClicked(int row, int col) {

        // Check if the move is valid for the current player
        if (isValidMove(row, col, currentPlayer)) {
            // Reset the hasPassed variable

            // Update the board with the new piece
            BOARD[row][col] = currentPlayer;

            // Add the new piece to the board
            Circle piece = new Circle(TILE_SIZE / 2 - 5, currentPlayer == 1 ? PLAYER1_COLOR : PLAYER2_COLOR);
            piece.setStroke(Color.BLACK);
            piece.setCenterX(col * TILE_SIZE + TILE_SIZE / 2);
            piece.setCenterY(row * TILE_SIZE + TILE_SIZE / 2);
            root.getChildren().add(piece);


            // Flip opponent pieces in all eight directions
            for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
                for (int colOffset = -1; colOffset <= 1; colOffset++) {
                    if (rowOffset == 0 && colOffset == 0) {
                        continue;
                    }
                    int r = row + rowOffset;
                    int c = col + colOffset;
                    boolean foundLine = false;
                    while (r >= 0 && r < BOARD_SIZE && c >= 0 && c < BOARD_SIZE && BOARD[r][c] == 3 - currentPlayer) {
                        r += rowOffset;
                        c += colOffset;
                        foundLine = true;
                    }
                    if (foundLine && r >= 0 && r < BOARD_SIZE && c >= 0 && c < BOARD_SIZE && BOARD[r][c] == currentPlayer) {
                        // Flip the line of opponent pieces
                        r -= rowOffset;
                        c -= colOffset;
                        while (r != row || c != col) {
                            BOARD[r][c] = currentPlayer;
                            for (Node node : root.getChildren()) {
                                if (node instanceof Circle) {
                                    Circle circle = (Circle) node;
                                    if ((int) circle.getCenterX() / TILE_SIZE == c && (int) circle.getCenterY() / TILE_SIZE == r) {
                                        circle.setFill(currentPlayer == 1 ? PLAYER1_COLOR : PLAYER2_COLOR);
                                    }
                                }
                            }
                            r -= rowOffset;
                            c -= colOffset;
                        }
                    }
                }
            }

            // Switch to the next player
            currentPlayer = 3 - currentPlayer;
            // Remove existing valid move circles from the board
            root.getChildren().removeIf(node -> node instanceof Circle && ((Circle) node).getFill() == Color.TRANSPARENT);

            drawValidMoves();

            updateLabels();
            // Check if the game is over
            boolean hasValidMoves = false;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (isValidMove(i, j, currentPlayer) ) {
                        hasValidMoves = true;
                        break;
                    }
                }
            }
            if (!hasValidMoves) {
                endGame();
            }else{
                if (mode==1 && currentPlayer == 1) {
                    makeAIMove();
                }
                if (mode==2){
                    makeAIMove();
                }
            }



        }
    }
}
