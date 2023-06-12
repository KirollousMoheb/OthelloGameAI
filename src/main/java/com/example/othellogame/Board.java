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
    
        private void endGame() {
        calculateScore();
        String winnerText;
        if (player1Score > player2Score) {
            winnerText = "White Player Wins!";
        } else if (player2Score > player1Score) {
            winnerText = "Black Player Wins!";
        } else {
            winnerText = "Tie Game!";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("GameOver!");
        alert.setHeaderText(winnerText);
        alert.setContentText("White Player final Score:"+player1Score+"\n"+"Black Player final Score:"+player2Score);
        alert.showAndWait();
        resetGame();
    }
    public void setAIMode(int mode) {
        this.mode = mode;
    }
    public void resetGame() {
        // Reset the board to its initial state
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                BOARD[i][j] = 0;
            }
        }
        BOARD[3][3] = 1;
        BOARD[4][4] = 1;
        BOARD[3][4] = 2;
        BOARD[4][3] = 2;

        // Remove all the pieces from the board
        root.getChildren().removeIf(node -> node instanceof Circle);

        // Reset the scores and labels
        player1Score = 0;
        player2Score = 0;
        currentPlayer = 2;
        updateLabels();

        // Redraw the board and pieces
        drawBoard();
    }
    private static final int[][] STABILITY = {
            {4, -3, 2, 2, 2, 2, -3, 4},
            {-3, -4, -1, -1, -1, -1, -4, -3},
            {2, -1, 1, 0, 0, 1, -1, 2},
            {2, -1, 0, 1, 1, 0, -1, 2},
            {2, -1, 0, 1, 1, 0, -1, 2},
            {2, -1, 1, 0, 0, 1, -1, 2},
            {-3, -4, -1, -1, -1, -1, -4, -3},
            {4, -3, 2, 2, 2, 2, -3, 4}
    };

    private int getStability(int[][] board, int player) {
        int stability = 0;
        int opponent = player == 1 ? 2 : 1;

        // Count stable pieces
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == player) {
                    stability += STABILITY[i][j];
                }
            }
        }

        return stability;
    }

    private int evaporationHeuristic(){
        int early_game =player1Score+player2Score;
        int count_goodness=0;
        int positional_goodness=0;
        int total_board_goodness=0;
        if(early_game<15){
             count_goodness=5*(player1Score-player2Score);

        }else{
            count_goodness=5*(player2Score-player1Score);

        }
        positional_goodness = 4*getStability(BOARD,currentPlayer);
        total_board_goodness = count_goodness + positional_goodness;
        return total_board_goodness;
    }
    
    
    
    private int heuristic(int player) {
        int mobility = 0;
        int stability = 0;
        int evaporation=0;
        // calculate mobility
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (isValidMove(i, j, player)) {
                    mobility++;
                }
            }
        }

        // calculate stability
        int[][] stabilityBoard = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (BOARD[i][j] == player) {
                    stabilityBoard[i][j] = getStability(BOARD,player);
                    stability += stabilityBoard[i][j];
                }
            }
        }
        evaporation=evaporationHeuristic();
        // combine mobility and stability into a single score
        int score = mobility + ( stability*8)+evaporation;

        return score;
    }
    
        // calculate stability
        int[][] stabilityBoard = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (BOARD[i][j] == player) {
                    stabilityBoard[i][j] = getStability(BOARD,player);
                    stability += stabilityBoard[i][j];
                }
            }
        }
        evaporation=evaporationHeuristic();
        // combine mobility and stability into a single score
        int score = mobility + ( stability*8)+evaporation;

        return score;
    }
    private int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                newBoard[row][col] = board[row][col];
            }
        }
        return newBoard;
    }
    private void makeMove(int[][] board, int row, int col, int player) {
        board[row][col] = player;
    }
    List<int[]> getValidMoves(int[][] board,int player){
        List<int[]> validMoves = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (isValidMove(i, j, currentPlayer)) {
                    validMoves.add(new int[] {i, j});
                }
            }
        }
        return validMoves;
    }

    private int[] minimax(int[][] board, int depth, int maxDepth, int player, boolean maximizingPlayer, int alpha, int beta) {
        List<int[]> validMoves = getValidMoves(board, player);

        if (depth == maxDepth || validMoves.size() == 0) {
            return new int[]{-1, -1, heuristic(player)};
        }

        int bestRow = -1;
        int bestCol = -1;
        int bestScore = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int[] move : validMoves) {
            int[][] newBoard = copyBoard(board);
            makeMove(newBoard, move[0], move[1], player);
            int[] score = minimax(newBoard, depth + 1, maxDepth, player == 1 ? 2 : 1, !maximizingPlayer, alpha, beta);

            if (maximizingPlayer) {
                if (score[2] > bestScore) {
                    bestScore = score[2];
                    bestRow = move[0];
                    bestCol = move[1];
                }
                alpha = Math.max(alpha, bestScore);
            } else {
                if (score[2] < bestScore) {
                    bestScore = score[2];
                    bestRow = move[0];
                    bestCol = move[1];
                }
                beta = Math.min(beta, bestScore);
            }

            if (beta <= alpha) {
                break;
            }
        }

        return new int[]{bestRow, bestCol, bestScore};
    }
    private int[] getBestMove(int[][] board, int depth, int player) {
        int[] result = minimax(board, 0, depth, player, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{result[0], result[1]};
    }
    private void makeAIMove() {
        int[] move = getBestMove(BOARD, depth, currentPlayer);
        int row = move[0];
        int col = move[1];
        handleTileClicked(row,col);
    }


    private void calculateScore(){
        player1Score=0;
        player2Score=0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (BOARD[i][j] == 1) {
                    player1Score++;
                } else if (BOARD[i][j] == 2) {
                    player2Score++;
                }
            }
        }
    }
    private void updateLabels() {
        calculateScore();
        scoreLabel.setText("White Score: " + player1Score + "|Black Score: " + player2Score);
        playerLabel.setText("Current player: " + (currentPlayer==1?"White Player":"Black Player"));
        playerLabel.setTextFill(currentPlayer == 1 ? PLAYER1_COLOR : PLAYER2_COLOR);
    }
    public void addEvents(){
        // Add mouse click event to the game board tiles
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
    EventHandler<MouseEvent> tileClicked = event -> {
        // Get the clicked tile
        Node clickedNode = (Node) event.getSource();
        if(clickedNode instanceof Circle){
            Circle clickedCircle = (Circle) clickedNode;
            int circleRow = (int) clickedCircle.getCenterY() / TILE_SIZE;
            int circleCol = (int) clickedCircle.getCenterX() / TILE_SIZE;
            handleTileClicked(circleRow, circleCol);

        }else if(clickedNode instanceof Rectangle){
            // handle rectangle click
            Rectangle clickedRectangle = (Rectangle) clickedNode;
            // get the row and col of the clicked rectangle
            int rectangleRow = (int) clickedRectangle.getY() / TILE_SIZE;
            int rectangleCol = (int) clickedRectangle.getX() / TILE_SIZE;
            handleTileClicked(rectangleRow, rectangleCol);

        }
    };

}
