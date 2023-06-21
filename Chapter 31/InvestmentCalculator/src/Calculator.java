/**
 * @author Jason Beach
 * @Date 06/20/2023
 * CSCI 1175: Industry Projects
 * Exercise 31-17 Investment Calculator
 * Write a program that calculates the future value of an investment at a given interest rate 
 * for a specified number of years. Use text fields for interest rate, investment amount, and 
 * years. Display the future amount in a text field when the user clicks the Calculate button 
 * or chooses Calculate from the Operation menu. Click the Exit menu to exit the program.
 * */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import java.text.DecimalFormat;
public class Calculator extends Application{
		
	  // Text field for receiving loan info
	  private TextField tfInvestmentAmount = new TextField();
	  private TextField tfNumOfYears = new TextField();
	  private TextField tfAnnualInterestRate = new TextField();
	  private TextField tfFutureValue = new TextField();
	  private Button btCalculate= new Button("Calculate");
	  
	  @Override // Override the start method in the Application class
	  public void start(Stage primaryStage) {
	   
	    GridPane gridPane = new GridPane();
	    gridPane.setStyle("-fx-border-color: lightgray; -fx-border-width: 8px;");
	    gridPane.setAlignment(Pos.CENTER);
	    gridPane.add(new Label("Investment Amount: "), 0, 0);
	    gridPane.add(new Label("Number Of Years: "), 0, 1);
	    gridPane.add(new Label("Annual Interest Rate: "), 0, 2);
	    gridPane.add(new Label("Future Value: "), 0, 3);
	    gridPane.add(tfInvestmentAmount, 1, 0);
	    gridPane.add(tfNumOfYears, 1, 1);
	    gridPane.add(tfAnnualInterestRate, 1, 2);
	    gridPane.add(tfFutureValue, 1, 3);
	    gridPane.add(btCalculate, 1, 4);
	    
	    tfInvestmentAmount.setAlignment(Pos.BASELINE_RIGHT);
	    tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
	    tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
	    btCalculate.setAlignment(Pos.BASELINE_RIGHT);
	    
	    tfInvestmentAmount.setPrefColumnCount(5);
	    tfNumOfYears.setPrefColumnCount(5);
	    tfAnnualInterestRate.setPrefColumnCount(5);
	    
	    // Create Menu Bar and Menus
	    MenuBar menuBar = new MenuBar();
	    Menu menuOperation = new Menu("Operation");
	    menuBar.getMenus().addAll(menuOperation);
	    MenuItem miCalculate = new MenuItem("Calculate");
	    MenuItem miExit = new MenuItem("Exit");
	    menuOperation.getItems().addAll(miCalculate, miExit);
	    BorderPane pane = new BorderPane();
	    pane.setCenter(gridPane);
	    pane.setTop(menuBar);
	    
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(pane, 350, 250);
	    primaryStage.setTitle("Investment Calculator"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	
	    // Set action for clicking Calculate Button
	    btCalculate.setOnAction(e-> {
	    	double investmentAmount = Double.parseDouble(tfInvestmentAmount.getText());
	    	double numOfYears = Double.parseDouble(tfNumOfYears.getText());
	    	double annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText());
	  
	    	// Format decimal and calculate future investment value
	    	DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	    	String futureValue = decimalFormat.format(calculateInvestment(investmentAmount, numOfYears, annualInterestRate));
	    	tfFutureValue.setText("$" + futureValue);
	    	tfFutureValue.setAlignment(Pos.BASELINE_RIGHT);
	    });
	    
	    // Set action for clicking Calculate in Menu
	    miCalculate.setOnAction(e-> {
	    	double investmentAmount = Double.parseDouble(tfInvestmentAmount.getText());
	    	double numOfYears = Double.parseDouble(tfNumOfYears.getText());
	    	double annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText());
	  
	    	// Format decimal and calculate future investment value
	    	DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	    	String futureValue = decimalFormat.format(calculateInvestment(investmentAmount, numOfYears, annualInterestRate));
	    	tfFutureValue.setText("$" + futureValue);
	    	tfFutureValue.setAlignment(Pos.BASELINE_RIGHT);
	    });
	    
	    // Set Action for clicking Exit in Menu
	    miExit.setOnAction(e-> {
	    	System.exit(0);
	    });
	  }
	  
	  /** Calculate Method */
	  public static double calculateInvestment(double investmentAmount, double numOfYears, double annualInterestRate) {
		  // Convert Annual Interest Rate to Monthly Interest Rate
		  double monthlyInterestRate = ((annualInterestRate / 12) / 100);
		  // Calculate futureValue of investment
		  double futureValue = investmentAmount * (Math.pow((1+ monthlyInterestRate), (numOfYears*12)));
		  return futureValue;
	  }
	  
	  /**
	   * The main method is only needed for the IDE with limited
	   * JavaFX support. Not needed for running from the command line.
	   */
	  public static void main(String[] args) {
		    launch(args);
	  }
}
