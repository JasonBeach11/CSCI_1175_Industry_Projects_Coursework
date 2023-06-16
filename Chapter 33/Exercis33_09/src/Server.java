import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server extends Application {
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
    primaryStage.setTitle("Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    // Listen for user to press ENTER after inputing text into client text area
    taClient.setOnKeyPressed(event ->{
			if(event.getCode() == KeyCode.ENTER) {
				try {
					String serverChatLine = taClient.getText().trim();		// Get text from chat line
					taClient.clear();
					
					taServer.appendText("S: " + serverChatLine + '\n');			// Append text to server text area
					dataOutputStream.writeUTF(serverChatLine);				// Send chat text to Client
					dataOutputStream.flush();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
	});
    
    new Thread (() -> {
    	try {																// Create a Server Socket
    		ServerSocket serverSocket = new ServerSocket(8000);
    		taServer.appendText("Server started: " + new Date() + '\n');
    		
    		Socket socket = serverSocket.accept();								// Listen for a connection request from client
    		
    		dataInputStream = new DataInputStream (socket.getInputStream());
    		dataOutputStream = new DataOutputStream (socket.getOutputStream());
    		
    		// If client connects to server, display connection time and client name and IP Address
    		if(socket.isBound()) {
    			taServer.appendText("Connected to client: " + new Date() + '\n');
    			InetAddress inetAddress = socket.getInetAddress();
    			taServer.appendText("Client's host name is: " + inetAddress.getHostName() + '\n');
    			taServer.appendText("Client's IP Adress is: " + inetAddress.getHostAddress() + '\n');
    		}
    		
    		while (true) {														// Constantly listen for incoming message from client
    			String message = dataInputStream.readUTF().trim();						// and append server text area when message is received
    			Platform.runLater(()->{
    			taServer.appendText("C: " + message + '\n');
    			});
    		}
    	}
    	catch (EOFException e){
    		e.printStackTrace();
    	}
    	catch (IOException ex) {
    		ex.printStackTrace();
    	}
    }).start();
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
