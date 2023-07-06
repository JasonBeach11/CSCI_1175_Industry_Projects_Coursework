/**
 * @author Jason Beach
 * Date: 07/05/2023
 * CSCI 1175 FINAL PROJECT
 * Iron Axe Throwing Game - This is designed to be a game used in conjunction with axe throwing.
 * Players throw physical axes at a physical target and then use this program to input and keep
 * track of their scores. Upon completion of a game, the player's scores are output to a file
 * that can be printed and/or digitally sent to the customers.
 * */

// Import required libraries
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
public class GameManager extends Application{
	
	private int numOfPlayers;
	private List<Player> players = new ArrayList<>();
	private int currentPlayerIndex = 0;
	private Player currentPlayer;
	private GridPane gridPane;
	
	// "Correct" login credentials for login screen
	final String ADMINUSERNAME = "admin";
	final String ADMINPASSWORD = "password";
		
	// Define start method for the Login Page
	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Iron Axe");
		// Create grid pane layout
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25,25,25,25));
		gridPane.setBackground(new Background(new BackgroundFill(Color.rgb(245, 245, 245), CornerRadii.EMPTY, Insets.EMPTY)));
			
		displayLoginScreen();
        
		// Set the scene and set it in the stage
        Scene scene = new Scene(gridPane, 800, 700);
        scene.setFill(Color.rgb(245, 245, 245));
        primaryStage.setScene(scene);
        primaryStage.show();	
	}
	
	/** Display the login screen. (login credentials are auto-filled for testing purposes) */
	private void displayLoginScreen() {
	
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25,25,25,25));
		gridPane.setBackground(new Background(new BackgroundFill(Color.rgb(245, 245, 245), CornerRadii.EMPTY, Insets.EMPTY)));
			
		Text sceneTitle = new Text("Iron Grill Inventory Management");
		sceneTitle.setFont(Font.font("Veranda", FontWeight.BOLD, 24));
		sceneTitle.setFill(Color.rgb(102, 102,102));
		gridPane.add(sceneTitle, 0, 0,2,1);
			
		// Add Username label and text field
		TextField userTextField = new TextField();
		Label userName = new Label("Username:");
		userName.setFont(Font.font("Veranda", FontWeight.NORMAL, 14));
		userName.setTextFill(Color.rgb(102, 102, 102));
		gridPane.add(userName, 0, 1);
		gridPane.add(userTextField, 1, 1);
			
		// Add Password label and text field
		PasswordField pwBox = new PasswordField();
		Label userPassword = new Label("Password:");
		userPassword.setFont(Font.font("Veranda", FontWeight.NORMAL, 14));
		userPassword.setTextFill(Color.rgb(102, 102, 102));
		gridPane.add(userPassword, 0, 2);
		gridPane.add(pwBox, 1, 2);
		
		//Auto fill Username and Password text fields for testing purposes
		userTextField.setText(ADMINUSERNAME);
		pwBox.setText(ADMINPASSWORD);
			
		// Add Login button
		Button loginBtn = new Button("Login");
		loginBtn.setFont(Font.font("Veranda", FontWeight.NORMAL, 14));
		loginBtn.setTextFill(Color.WHITE);
		loginBtn.setStyle("-fx-background-color: #4CAF50; -fx-border-radius: 5px; -fx-background-radius: 5px;");
		gridPane.add(loginBtn, 1, 4);
			
		// Add "Incorrect username or password" text
		final Text incorrectLogin = new Text();
		incorrectLogin.setFill(Color.rgb(190, 10, 10));
		gridPane.add(incorrectLogin, 1, 6);
			
		// Add "Successful Login" text
		final Text loginSuccess = new Text();
		loginSuccess.setFill(Color.rgb(102, 102, 102));
		gridPane.add(loginSuccess, 1, 6);
			
		// Add Iron Axe logo to Login page
		Image logo = new Image("https://static.wixstatic.com/media/616172_d518f0c53597411c9579b4cfad99f812~mv2.png"
	        	+ "/v1/fill/w_221,h_222,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/Iron%252520Axe_edited_edited.png");
		ImageView imageView = new ImageView(logo);
		HBox hBox = new HBox(imageView);
		imageView.setFitHeight(90);
		imageView.setPreserveRatio(true);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.setPadding(new Insets(0,0,10,0));
		gridPane.add(hBox, 1, 7);
			
			// Set action for the Login button
			loginBtn.setOnAction(e ->{
				// Retrieve user-entered username and password from text fields
				String enteredUserName = userTextField.getText();
				String enteredPassword = pwBox.getText();
				
				// Check if username and password are correct
				if(validate(enteredUserName, enteredPassword)) {
					// Login Success - Redirect user to main page
					incorrectLogin.setText("");
					loginSuccess.setText("Login Success");
					
					// OPEN NEXT WINDOW
					displayNumberOfPlayersSelection(gridPane);
				}
				else {
					// Display login error
					incorrectLogin.setText("Incorrect username or password");
				}
			});
			
			// Set action when user presses ENTER button
			gridPane.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.ENTER) {
					String enteredUserName = userTextField.getText();
					String enteredPassword = pwBox.getText();
				
					if(validate(enteredUserName, enteredPassword)) {
						// Login Success - Redirect user to main page
						incorrectLogin.setText("");
						loginSuccess.setText("Login Success");
						
						// OPEN NEXT WINDOW
						displayNumberOfPlayersSelection(gridPane);
					}
					else {
						// Display login error
						incorrectLogin.setText("Incorrect username or password");
					}
				}
			});
	}

	/** Validate login credentials */
	public boolean validate(String username, String password) {
		
		if(username.equals(ADMINUSERNAME) && password.equals(ADMINPASSWORD)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/** Display screen for selecting the number of players playing a game */
	private void displayNumberOfPlayersSelection(GridPane gridPane) {
	    // Clear the grid pane
	    gridPane.getChildren().clear();

	    // Create a title for the number of players selection
	    Text numPlayersTitle = new Text("Select Number of Players");
	    numPlayersTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	    GridPane.setHalignment(numPlayersTitle, HPos.CENTER);

	    // Create buttons for selecting the number of players
	    Button onePlayerButton = createButton("1 Player");
	    onePlayerButton.setOnAction(event -> {
	        numOfPlayers = 1;
	        displayPlayerNameInput();
	        System.out.println("Selected 1 Player");
	    });

	    Button twoPlayersButton = createButton("2 Players");
	    twoPlayersButton.setOnAction(event -> {
	        numOfPlayers = 2;
	        displayPlayerNameInput();
	        System.out.println("Selected 2 Players");
	    });

	    // Add the components to the grid pane
	    gridPane.add(numPlayersTitle, 0, 0, 2, 1);
	    GridPane.setMargin(numPlayersTitle, new Insets(10, 0, 20, 0));
	    GridPane.setHalignment(numPlayersTitle, HPos.CENTER);

	    gridPane.add(onePlayerButton, 0, 1);
	    GridPane.setMargin(onePlayerButton, new Insets(10, 0, 10, 0));

	    gridPane.add(twoPlayersButton, 1, 1);
	    GridPane.setMargin(twoPlayersButton, new Insets(10, 0, 10, 0));
	}
	
	/** Creates a button. Receives a String argument to be used as the button text */
	private Button createButton(String text) {
		Button button = new Button(text);
		button.setStyle("-fx-background-color: black; -fx-text-fill: gold; -fx-font-size: 16px;");
        button.setPrefWidth(120);
        button.setPrefHeight(40);
        return button;
    }
	
	/** Display screen for players to input their name(s) */
	private void displayPlayerNameInput() {
	    // Clear the grid pane
	    gridPane.getChildren().clear();

	    // Create a title for the player name input
	    Text playerNameTitle = new Text("Enter Player Names");
	    playerNameTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	    GridPane.setHalignment(playerNameTitle, HPos.CENTER);

	    // Create a container for the player name text fields
	    VBox textBoxContainer = new VBox(10);
	    textBoxContainer.setAlignment(Pos.CENTER);
	    textBoxContainer.setPadding(new Insets(20));
	    textBoxContainer.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #999999; -fx-border-width: 2px;");

	    // Create text fields for player names and add them to the container
	    for (int i = 0; i < numOfPlayers; i++) {
	        TextField playerNameTextField = new TextField();
	        playerNameTextField.setPromptText("Player " + (i + 1) + " Name");
	        playerNameTextField.setMaxWidth(200);
	        playerNameTextField.setMaxHeight(30);
	        textBoxContainer.getChildren().add(playerNameTextField);
	    }

	    // Create a confirm button
	    Button confirmButton = createButton("Confirm");
	    confirmButton.setOnAction(event -> {
	    	// Process the entered player names
	        for (int i = 0; i < numOfPlayers; i++) {
	            String playerName = ((TextField) textBoxContainer.getChildren().get(i)).getText();
	            System.out.println("Player " + (i + 1) + " Name: " + playerName);

	            // Create a Player object and add it to the players list
	            Player player = new Player(playerName);
	            players.add(player);
	        }

	        // Set the current player
	        currentPlayer = players.get(currentPlayerIndex);

	        // Perform any necessary actions with the player names
	        displayGameModeSelection();
	    });

	    // Create a back button
	    Button backButton = createButton("< Back");
	    backButton.setOnAction(event -> {
	        // Go back to the number of players selection
	        displayNumberOfPlayersSelection(gridPane);
	    });

	    // Add the components to the grid pane
	    gridPane.add(playerNameTitle, 0, 0, 2, 1);
	    GridPane.setMargin(playerNameTitle, new Insets(10, 0, 20, 0));
	    GridPane.setHalignment(playerNameTitle, HPos.CENTER);

	    gridPane.add(textBoxContainer, 0, 1, 2, 1);
	    GridPane.setMargin(textBoxContainer, new Insets(10, 0, 20, 0));

	    gridPane.add(confirmButton, 1, 2);
	    GridPane.setHalignment(confirmButton, HPos.CENTER);

	    gridPane.add(backButton, 0, 2);
	    GridPane.setHalignment(backButton, HPos.CENTER);
	}

	/** Display screen for game mode selection. (1 game mode available for now) */
	private void displayGameModeSelection() {
	    // Clear the gridPane
	    gridPane.getChildren().clear();

	    // Create a Text object for the title
	    Text title = new Text("Select Game Mode");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
	    gridPane.add(title, 0, 0, 2, 1);
	    GridPane.setMargin(title, new Insets(-50, 0, 0, 0));
	    GridPane.setHalignment(title, HPos.CENTER);

	    // Create buttons for selecting the game mode
	    Button standardModeButton = createButton("Standard Game");
	    standardModeButton.setPrefWidth(200);
	    standardModeButton.setOnAction(event -> {
	    	displayStandardGame();
	        System.out.println("Selected Standard Mode");
	    });

	    // Add buttons to the gridPane
	    gridPane.add(standardModeButton, 0, 1);
	    GridPane.setMargin(standardModeButton, new Insets(10, 0, 0, 0));
	    
	    // Create a Back button
	    Button backButton = createButton("< Back");
	    backButton.setOnAction(event -> displayNumberOfPlayersSelection(gridPane));
	    gridPane.add(backButton, 0, 4);
	    GridPane.setMargin(backButton, new Insets(10, 0, 0, 0));
	}

	/** Display screen for Standard Game Mode */
	private void displayStandardGame() {
	    // Clear the gridPane
	    gridPane.getChildren().clear();

	    // Create a box to display player names and scores
	    VBox scoreBox = new VBox(10);
	    scoreBox.setAlignment(Pos.CENTER);
	    scoreBox.setPadding(new Insets(20));
	    scoreBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #999999; -fx-border-width: 2px;");

	    // Create text fields for player names and scores and add them to the box
	    for (Player player : players) {
	        TextField playerNameField = new TextField(player.getPlayerName());
	        playerNameField.setEditable(false);
	        TextField playerScoreField = new TextField(Integer.toString(player.getScore()));
	        playerScoreField.setEditable(false);

	        // Add the player name and score fields to the score box
	        scoreBox.getChildren().addAll(playerNameField, playerScoreField);
	    }

	    // Create a ThrowingTarget object and add it to the gridPane
	    ThrowingTarget target = new ThrowingTarget(players, gridPane, scoreBox);

	    gridPane.add(target.getPane(), 0, 1);
	    GridPane.setHalignment(target.getPane(), HPos.CENTER);

	    // Add the score box above the target object
	    gridPane.add(scoreBox, 0, 0);
	}

	/** Update player score display */
	private void updateScoreDisplay(GridPane gridPane) {
	    VBox scoreBox = (VBox) gridPane.getChildren().get(0);
	    for (int i = 0; i < players.size(); i++) {
	        Player player = players.get(i);
	        TextField playerScoreField = (TextField) scoreBox.getChildren().get(i * 2 + 1);
	        playerScoreField.setText(Integer.toString(player.getScore()));
	    }
	}

	/** Return players List */
	public List<Player> getPlayers() {
	    return players;
	}

	/** Increment player score based on points earned */
	private void incrementScore(Player player, int points) {
	    Player currentPlayer = players.get(currentPlayerIndex);
	    currentPlayer.incrementScore(points);
	    updateScoreDisplay(gridPane); // Update the score display directly in the GameManager class
	}

	/** Set current player index */
	private void setCurrentPlayerIndex(int index) {
	    currentPlayerIndex = index;
	}
	
	// Define the main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}
