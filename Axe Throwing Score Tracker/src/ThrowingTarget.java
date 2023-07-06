/**
 * Target is constructed with target artwork (Target.png). Transparent circles are overlaid over the target art and aligned to
 * match up with the target art boundaries. Each circle has its own event handler to detect mouse click making each circle a 
 * Hit Zone.
 * */
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.VBox;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ThrowingTarget{
	
	private GridPane pane;
	private GridPane gridPane;
    private List<Player> players;
    private int currentPlayerIndex = 0;
    private VBox scoreBox;
    private int roundCounter = 1;
    private StackPane targetStackPane;

	// Construct a Target
	public ThrowingTarget(List<Player> players, GridPane gridPane, VBox scoreBox){
		
		this.players = players;
        this.gridPane = gridPane;
        this.scoreBox = scoreBox;
        
		pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		pane.setBackground(new Background(new BackgroundFill(Color.rgb(245, 245, 245), CornerRadii.EMPTY, Insets.EMPTY)));
			
		// Create target art using Target.png
		Image targetArt = new Image("Target.png");
		ImageView imageView = new ImageView(targetArt);
		targetStackPane = new StackPane(imageView);
		imageView.setFitHeight(400);
		imageView.setPreserveRatio(true);
			
		//CREATE HIT ZONES
		
		
		
		// Create Hit Zone for 1 Point
		Circle score1 = new Circle(0,0,170);
		score1.setStroke(Color.TRANSPARENT);
		score1.setFill(Color.TRANSPARENT);
		score1.setPickOnBounds(false);
			
		score1.setOnMouseClicked(event -> {
		    Player currentPlayer = players.get(currentPlayerIndex);
		    currentPlayer.incrementScore(1); // Update the score of the current player
		    
		    roundCounter ++;  // Increase round counter
		    if(players.size() < 2 && roundCounter >= 11) {		// If single player game
		    	gameOver(players.get(0).getScore());
		    }
		    else if (players.size() > 1 && roundCounter >= 21) {		// If 2 player game 
		    	gameOver(players.get(0).getScore(), players.get(1).getScore());
		    }
		    else {
		    	currentPlayerIndex++; // Move to the next player
		    	if (currentPlayerIndex >= players.size()) {
		    		currentPlayerIndex = 0; // Start from the first player again
		    	}
		    }
		    // Update the score display
		    updateScoreDisplay();
		});

		// Create Hit Zone for 2 Points
		Circle score2 = new Circle(0,0,132);
		score2.setStroke(Color.TRANSPARENT);
		score2.setFill(Color.TRANSPARENT);
		score2.setPickOnBounds(false);
			
		score2.setOnMouseClicked(event -> {
		    Player currentPlayer = players.get(currentPlayerIndex);
		    currentPlayer.incrementScore(2); // Update the score of the current player
		    
		    roundCounter ++;  // Increase round counter
		    if(players.size() < 2 && roundCounter >= 11) {		// If single player game
		    	gameOver(players.get(0).getScore());
		    }
		    else if (players.size() > 1 && roundCounter >= 21) {		// If 2 player game 
		    	gameOver(players.get(0).getScore(), players.get(1).getScore());
		    }
		    else {
		    	currentPlayerIndex++; // Move to the next player
		    	if (currentPlayerIndex >= players.size()) {
		    		currentPlayerIndex = 0; // Start from the first player again
		    	}
		    }
		    // Update the score display
		    updateScoreDisplay();
		});
			
		// Create Hit Zone for 3 Points
		Circle score3 = new Circle(0,0,92);
		score3.setStroke(Color.TRANSPARENT);
		score3.setFill(Color.TRANSPARENT);
		score3.setPickOnBounds(false);
			
		score3.setOnMouseClicked(event -> {
		    Player currentPlayer = players.get(currentPlayerIndex);
		    currentPlayer.incrementScore(3); // Update the score of the current player
		    
		    roundCounter ++;  // Increase round counter
		    if(players.size() < 2 && roundCounter >= 11) {		// If single player game
		    	gameOver(players.get(0).getScore());
		    }
		    else if (players.size() > 1 && roundCounter >= 21) {		// If 2 player game 
		    	gameOver(players.get(0).getScore(), players.get(1).getScore());
		    }
		    else {
		    	currentPlayerIndex++; // Move to the next player
		    	if (currentPlayerIndex >= players.size()) {
		    		currentPlayerIndex = 0; // Start from the first player again
		    	}
		    }
		    // Update the score display
		    updateScoreDisplay();
		});
			
		// Create Hit Zone for 4 Points
		Circle score4 = new Circle(0,0,54);
		score4.setStroke(Color.TRANSPARENT);
		score4.setFill(Color.TRANSPARENT);
		score4.setPickOnBounds(false);
			
		score4.setOnMouseClicked(event -> {
		    Player currentPlayer = players.get(currentPlayerIndex);
		    currentPlayer.incrementScore(4); // Update the score of the current player
		    
		    roundCounter ++;  // Increase round counter
		    if(players.size() < 2 && roundCounter >= 11) {		// If single player game
		    	gameOver(players.get(0).getScore());
		    }
		    else if (players.size() > 1 && roundCounter >= 21) {		// If 2 player game 
		    	gameOver(players.get(0).getScore(), players.get(1).getScore());
		    }
		    else {
		    	currentPlayerIndex++; // Move to the next player
		    	if (currentPlayerIndex >= players.size()) {
		    		currentPlayerIndex = 0; // Start from the first player again
		    	}
		    }
		    // Update the score display
		    updateScoreDisplay();
		});
			
		// Create Hit Zone for 6 Points (Bullseye)
		Circle score6 = new Circle(0,0,25);
		score6.setStroke(Color.TRANSPARENT);
		score6.setFill(Color.TRANSPARENT);
		score6.setPickOnBounds(false);
			
		score6.setOnMouseClicked(event -> {
		    Player currentPlayer = players.get(currentPlayerIndex);
		    currentPlayer.incrementScore(6); // Update the score of the current player
		    roundCounter ++;  // Increase round counter
		    System.out.println(roundCounter);
		    if(players.size() < 2 && roundCounter >= 11) {		// If single player game
		    	gameOver(players.get(0).getScore());
		    }
		    else if (players.size() > 1 && roundCounter >= 21) {		// If 2 player game 
		    	gameOver(players.get(0).getScore(), players.get(1).getScore());
		    }
		    else {
		    	currentPlayerIndex++; // Move to the next player
		    	if (currentPlayerIndex >= players.size()) {
		    		currentPlayerIndex = 0; // Start from the first player again
		    	}
		    }
		    // Update the score display
		    updateScoreDisplay();
		});
			
		// Create Hit Zone for Killshot (right)
		Circle killshotRight = new Circle(0,0,13);
		killshotRight.setStroke(Color.TRANSPARENT);
		killshotRight.setFill(Color.TRANSPARENT);
			
		killshotRight.setOnMouseClicked(event -> {
		    Player currentPlayer = players.get(currentPlayerIndex);
		    currentPlayer.incrementScore(8); // Update the score of the current player
		    
		    roundCounter ++;  // Increase round counter
		    if(players.size() < 2 && roundCounter >= 11) {		// If single player game
		    	gameOver(players.get(0).getScore());
		    }
		    else if (players.size() > 1 && roundCounter >= 21) {		// If 2 player game 
		    	gameOver(players.get(0).getScore(), players.get(1).getScore());
		    }
		    else {
		    	currentPlayerIndex++; // Move to the next player
		    	if (currentPlayerIndex >= players.size()) {
		    		currentPlayerIndex = 0; // Start from the first player again
		    	}
		    }
		    // Update the score display
		    updateScoreDisplay();
		});
			
		// Create Hit Zone for Killshot (left)
		Circle killshotLeft = new Circle(0,0,13);
		killshotLeft.setStroke(Color.TRANSPARENT);
		killshotLeft.setFill(Color.TRANSPARENT);
			
		killshotLeft.setOnMouseClicked(event -> {
		    Player currentPlayer = players.get(currentPlayerIndex);
		    currentPlayer.incrementScore(8); // Update the score of the current player
		    
		    roundCounter ++;  // Increase round counter
		    if(players.size() < 2 && roundCounter >= 11) {		// If single player game
		    	gameOver(players.get(0).getScore());
		    }
		    else if (players.size() > 1 && roundCounter >= 21) {		// If 2 player game 
		    	gameOver(players.get(0).getScore(), players.get(1).getScore());
		    }
		    else {
		    	currentPlayerIndex++; // Move to the next player
		    	if (currentPlayerIndex >= players.size()) {
		    		currentPlayerIndex = 0; // Start from the first player again
		    	}
		    }
		    // Update the score display
		    updateScoreDisplay();
		});
			
		// Add hit zones to stack pane and realign with target art
		targetStackPane.getChildren().addAll(score1, score2, score3, score4, score6, killshotLeft, killshotRight);
		targetStackPane.setMargin(score1, new Insets(-8,30,0,0));
		targetStackPane.setMargin(score2, new Insets(-8,30,0,0));
		targetStackPane.setMargin(score3, new Insets(-8,30,0,0));
		targetStackPane.setMargin(score4, new Insets(-8,30,0,0));
		targetStackPane.setMargin(score6, new Insets(-8,30,0,0));
		targetStackPane.setMargin(killshotRight, new Insets(-220, 0, 0, 185));
		targetStackPane.setMargin(killshotLeft, new Insets(-220, 0, 0, -240));
			
		pane.add(targetStackPane, 0, 0);
		
		scoreBox = new VBox();
		scoreBox.setAlignment(Pos.CENTER);
		scoreBox.setSpacing(10);

		// Add score labels and text fields for each player
		for (Player player : players) {
		    Label playerLabel = new Label(player.getPlayerName());
		    TextField playerScoreField = new TextField();
		    playerScoreField.setEditable(false);
		    playerScoreField.setText(Integer.toString(player.getScore()));
		    playerScoreField.setPrefWidth(60);

		    scoreBox.getChildren().addAll(playerLabel, playerScoreField);
		}
		// Add the score box to the grid pane
		gridPane.add(scoreBox, 0, 0);
	}
	
	/** Update score display with current scores */
	private void updateScoreDisplay() {
	    for (int i = 0; i < players.size(); i++) {
	        Player player = players.get(i);
	        TextField playerScoreField = (TextField) scoreBox.getChildren().get(i * 2 + 1);
	        playerScoreField.setText(Integer.toString(player.getScore()));
	    }
	}
	
	/** Return pane */
	public GridPane getPane() {
        return pane;
    }
	
	/** When a game ends, display winner name and scores to screen and save player scores to a file */
	public void gameOver(int player1Score, int player2Score) {
	    Label winnerLabel;
	    if(player1Score == player2Score) {
	    	winnerLabel = new Label("Tie Game!");
	        playAgain();
	    }
	    else if (player1Score > player2Score) {
	        winnerLabel = new Label(players.get(0).getPlayerName() + " Wins!");
	        playAgain();
	    } else {
	    	winnerLabel = new Label(players.get(1).getPlayerName() + " Wins!");
	    	playAgain();
	    }

	    winnerLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");

	    pane.getChildren().remove(targetStackPane);
	    pane.add(winnerLabel, 0, 0);
	    
	    // Print player score results to a file
	    printScoreResultsToFile(player1Score, player2Score);
	}
	
	/** When a SINGLE PLAYER game ends, display player score*/
	public void gameOver(int player1Score) {
		Label winnerLabel;
	    winnerLabel = new Label("Good Game!");
	    playAgain();
	    
	    winnerLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");

	    pane.getChildren().remove(targetStackPane);
	    pane.add(winnerLabel, 0, 0);
	    
	    // Print player score results to a file
	    printScoreResultsToFile(player1Score);
	}
	
	/** Print player score results to a file */
	public void printScoreResultsToFile(int player1Score, int player2Score) {
		try (BufferedWriter writer = new BufferedWriter (new FileWriter("score_results.txt", true))) {
			// Apend the player score results to the file
			writer.write("Player 1 Score: " + player1Score);
			writer.newLine();
			writer.write("Player 2 Score: " + player2Score);
			writer.newLine();
			writer.newLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Print player score results to a file for single player games */
	public void printScoreResultsToFile(int player1Score) {
		try (BufferedWriter writer = new BufferedWriter (new FileWriter("score_results.txt", true))) {
			// Apend the player score results to the file
			writer.write("Player 1 Score: " + player1Score);
			writer.newLine();
			writer.newLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Ask players if they want to play another game */
	public void playAgain() {
		Label playAgain = new Label("Would you like to play again?");
		playAgain.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
		pane.add(playAgain, 0, 1);
	}

}