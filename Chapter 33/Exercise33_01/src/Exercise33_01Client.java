/**
 * @author Jason Beach
 * @Date 06/13/2023
 * CSCI 1175: Industry Projects
 * Exercise 33-01 (Loan Server) Write a server for a client. The client sends loan 
 * information (annual interest rate, number of years, and loan amount) to the 
 * server. The server creates a Loan object to compute monthly payment and total 
 * payment, and sends those numbers back to the client.
 * */
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise33_01Client extends Application {
	
	// Create input/output streams for server/client communication
	DataOutputStream toServer = null;
	DataInputStream fromServer = null;
	
  // Text field for receiving loan info
  private TextField tfAnnualInterestRate = new TextField();
  private TextField tfNumOfYears = new TextField();
  private TextField tfLoanAmount = new TextField();
  private Button btSubmit= new Button("Submit");

  // Text area to display contents
  private TextArea ta = new TextArea();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Annual Interest Rate"), 0, 0);
    gridPane.add(new Label("Number Of Years"), 0, 1);
    gridPane.add(new Label("Loan Amount"), 0, 2);
    gridPane.add(tfAnnualInterestRate, 1, 0);
    gridPane.add(tfNumOfYears, 1, 1);
    gridPane.add(tfLoanAmount, 1, 2);
    gridPane.add(btSubmit, 2, 1);
    
    tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
    tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
    tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);
    
    tfLoanAmount.setPrefColumnCount(5);
    tfNumOfYears.setPrefColumnCount(5);
    tfLoanAmount.setPrefColumnCount(5);
            
    BorderPane pane = new BorderPane();
    pane.setCenter(new ScrollPane(ta));
    pane.setTop(gridPane);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise31_01Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    
    // CREATE SERVER COMMUNICATION
    btSubmit.setOnAction(e-> {
    	try {
    		// Get text field info from text fields
    		double annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText().trim());
    		int numOfYears = Integer.parseInt(tfNumOfYears.getText().trim());
    		double loanAmount = Double.parseDouble(tfLoanAmount.getText().trim());
    		
        	// Send loan info to the server
        	toServer.writeDouble(annualInterestRate);
        	toServer.writeInt(numOfYears);
        	toServer.writeDouble(loanAmount);
        	toServer.flush();
        	
        	//Get calculated loan payments from the server
        	double monthlyPayment = fromServer.readDouble();
        	double totalPayment = fromServer.readDouble();
        	
        	// Display all loan information
        	ta.appendText("Annual Interest Rate: " + annualInterestRate + '\n');
        	ta.appendText("Number of Years: " + numOfYears + '\n');
        	ta.appendText("Loan Amount: " + loanAmount + '\n');
        	ta.appendText("Monthly Payment: " + monthlyPayment + '\n');
        	ta.appendText("Total Payment: " + totalPayment + '\n');
    	}
    	catch (NumberFormatException ex) {
    		System.out.println("Invalid input for annual interest rate: " + ex.getMessage());
    	}
    	catch (IOException ex) {
    		System.out.println(ex);
    	}
    	
    });
    
    try {
    	// Create a socket to connect to the server
    	Socket socket = new Socket("localhost", 8000);
    	
    	// Create an input stream to receive data from the server
    	fromServer = new DataInputStream(socket.getInputStream());
    	
    	// Create an output stream to send data to the server
    	toServer = new DataOutputStream(socket.getOutputStream());
    }
    catch(IOException ex){
    	ta.appendText(ex.toString() + '\n');
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
