// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.net.*;
import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  
  
  //CREATE SERVER
  new Thread (() -> {
	  try {
		  // Create a server socket
		  ServerSocket serverSocket = new ServerSocket(8000);
		  Platform.runLater(() ->
		  ta.appendText("Server started at " + new Date() + '\n'));
		  
		  // Listen for a connection request
		  Socket socket = serverSocket.accept();
		  
		  // If client connects to server, display connection time and client address
		  if(socket.isBound()) {
			  ta.appendText("Connected to a client at " + new Date() + '\n');
			  ta.appendText(socket.toString() + '\n');
		  }
		  
		  while(true) {
			  // Create data input and output streams
			  DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			  DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
		  
			  // Receive data from client
			  double annualInterestRate = inputFromClient.readDouble();
			  int numOfYears = inputFromClient.readInt();
			  double loanAmount = inputFromClient.readDouble();
			  
			  // Create Loan object
			  Loan loan1 = new Loan(annualInterestRate, numOfYears, loanAmount);
			  
			  //Send monthly payment and total payment back to client
			  outputToClient.writeDouble(loan1.getMonthlyPayment());
			  outputToClient.writeDouble(loan1.getTotalPayment());
			  
			  Platform.runLater(() -> {
				  ta.appendText("\nAnnual Interest Rate recieved from the client: " + annualInterestRate + "\n");
				  ta.appendText("Number of Years recieved from the client: " + numOfYears + '\n');
				  ta.appendText("Loan Amount recieved from the client: " + loanAmount + '\n');
				  ta.appendText("Monthly Payment sent to client: " + loan1.getMonthlyPayment() + '\n');
				  ta.appendText("Total Payment sent to client: " + loan1.getTotalPayment() + '\n');
			  });
		  }
			  
	  }
	    catch(EOFException e) {
	    	ta.appendText("Client has closed the connection");
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