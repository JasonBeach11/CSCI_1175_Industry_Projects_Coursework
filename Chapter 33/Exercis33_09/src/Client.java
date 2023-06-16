/**
 * @author Jason Beach
 * @Date 06/15/2023
 * CSCI 1175: Industry Projects
 * Exercise 33-09
 * (Chat) Write a program that enables two users to chat. Implement one user 
 * as the server and the other as the client. The server has two text areas: 
 * one for entering text and the other (non-editable) for displaying the 
 * history of the conversation. When the user presses the Enter key, the 
 * current line is sent to the client and the text area is cleared. The 
 * client has two text areas: one (non-editable) for displaying the history 
 * of the conversation. When the user presses the Enter key, the current 
 * line is sent to the server and the text area is cleared.
 * */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
import java.util.Date;

public class Client extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  DataInputStream dataInputStream = null;
  DataOutputStream dataOutputStream = null;
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 450, 300);
    primaryStage.setTitle("Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    
    // Listen for user to press ENTER after inputing text into client text area
    taClient.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER) {
				try {
					String clientChatLine = taClient.getText().trim();		// Get text from chat line
					taClient.clear();
				
					taServer.appendText("C: " + clientChatLine + '\n');		// Append chat text to server text area
					dataOutputStream.writeUTF(clientChatLine);				// Send chat text to Server
					dataOutputStream.flush();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
		}
	});
    
    	try {
    		// Create a socket to connect to the server
    		Socket socket = new Socket("localhost", 8000);						// Create socket and connect to Server
    		
    		// If a connection is made, display time and Server's name and IP Address
    		if(socket.isBound()) {
    			taServer.appendText("Connected to server: " + new Date() + '\n');
    			InetAddress inetAddress = socket.getInetAddress();
    			taServer.appendText("Server's host name is: " + inetAddress.getHostName() + '\n');
    			taServer.appendText("Server's IP Adress is: " + inetAddress.getHostAddress() + '\n');
    		}
    		
    		dataInputStream = new DataInputStream (socket.getInputStream());	// Create Data I/O Streams to communicate with socket
    		dataOutputStream = new DataOutputStream(socket.getOutputStream());
	
    		new Thread (() -> {
    			try {
    				while (true) {
    					String input =dataInputStream.readUTF();				// Constantly listen for chat text from server 
    					taServer.appendText("S: " + input +'\n');				// and append server text area when chat text
    				}															// is received.
    			}
    			catch(IOException e ) {
    				e.printStackTrace();
    			}
    		}).start();
    	}
    	catch(IOException ex) {
    		taServer.appendText(ex.toString() + '\n');
    	}
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
