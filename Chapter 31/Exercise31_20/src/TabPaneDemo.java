/**
 * @author Jason Beach
 * @Date 06/21/2023
 * CSCI 1175 - Industry Projects
 * Exercise 31_20:Tab Pane Demo - This program is a basic demo of a tab pane. The tab pane contains 4 tabs, 
 * each displaying a different shape. When the radio buttons are selected, the tab pane is realigned to the
 * corresponding sides of the pane.
 * */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Side;
import javafx.scene.control.Control;
import javafx.scene.layout.StackPane;

public class TabPaneDemo extends Application {   
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {  
	  
	GridPane gridPane = new GridPane();
    TabPane tabPane = new TabPane();
    HBox radioButtonPane = new HBox();
    StackPane shapePane1 = new StackPane();
    StackPane shapePane2 = new StackPane();
    StackPane shapePane3 = new StackPane();
    StackPane shapePane4 = new StackPane();
    
    Tab tab1 = new Tab("Line");
    Line line = new Line(10, 10, 80, 80);
    line.setStrokeWidth(3);
    shapePane1.getChildren().add(line);
    shapePane1.setAlignment(Pos.CENTER);
    shapePane1.setPrefSize(2500, 250);
    tab1.setContent(shapePane1);
    
    Tab tab2 = new Tab("Rectangle");
    Rectangle rectangle = new Rectangle(10, 10, 80, 80);
    shapePane2.getChildren().add(rectangle);
    shapePane2.setAlignment(Pos.CENTER);
    shapePane2.setPrefSize(250, 250);
    tab2.setContent(shapePane2);
    
    Tab tab3 = new Tab("Circle");
    Circle circle = new Circle(50, 50, 40);
    shapePane3.getChildren().add(circle);
    shapePane3.setAlignment(Pos.CENTER);
    shapePane3.setPrefSize(250, 250);
    tab3.setContent(shapePane3);   
    
    Tab tab4 = new Tab("Ellipse");
    Ellipse ellipse = new Ellipse(10, 10, 60, 40);
    shapePane4.getChildren().add(ellipse);
    shapePane4.setAlignment(Pos.CENTER);
    shapePane4.setPrefSize(250, 250);
    tab4.setContent(shapePane4);
    
    tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
    
    // Create toggle group for radio buttons
    ToggleGroup toggleGroup = new ToggleGroup();
    
    // Create radio buttons
    RadioButton rbTop = new RadioButton("Top");
    RadioButton rbBottom = new RadioButton("Bottom");
    RadioButton rbLeft = new RadioButton("Left");
    RadioButton rbRight = new RadioButton("Right");
    
    // Set toggle group for radio buttons
    rbTop.setToggleGroup(toggleGroup);
    rbBottom.setToggleGroup(toggleGroup);
    rbLeft.setToggleGroup(toggleGroup);
    rbRight.setToggleGroup(toggleGroup);
    
    // Format and style radio button pane
    radioButtonPane.getChildren().addAll(rbTop, rbBottom, rbLeft, rbRight);
    radioButtonPane.setSpacing(25);
    radioButtonPane.setAlignment(Pos.BASELINE_CENTER);
    radioButtonPane.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
    
    // Format and style Root Pane
    gridPane.add(tabPane, 0, 0);
    gridPane.add(radioButtonPane, 0, 1);
    gridPane.setStyle("-fx-border-color: lightgray; -fx-border-width: 8px;");
    
    Scene scene = new Scene(gridPane, 350, 280);  
    primaryStage.setTitle("Tab Pane Demo"); // Set the window title
    primaryStage.setScene(scene); // Place the scene in the window
    primaryStage.show(); // Display the window
    
    // EVENT HANDLERS FOR RADIO BUTTONS
    // If Radio Button (TOP) is selected, align Tabs to top of pane
    rbTop.setOnAction( e -> {
    	tabPane.setSide(Side.TOP);
    	tabPane.setTabMinHeight(Control.USE_PREF_SIZE);
    	tabPane.setMaxHeight(Control.USE_PREF_SIZE);
    	GridPane.setRowIndex(tabPane,  0);
    	GridPane.setColumnIndex(tabPane, 0);
    	GridPane.setRowIndex(radioButtonPane,  1);
    	GridPane.setColumnIndex(radioButtonPane, 0);
    });
    
    // If Radio Button (BOTTOM) is selected, align Tabs to top of pane
    rbBottom.setOnAction( e -> {
    	tabPane.setSide(Side.BOTTOM);
    	tabPane.setTabMinHeight(Control.USE_PREF_SIZE);
    	tabPane.setMaxHeight(Control.USE_PREF_SIZE);
    	GridPane.setRowIndex(tabPane,  0);
    	GridPane.setColumnIndex(tabPane, 0);
    	GridPane.setRowIndex(radioButtonPane,  1);
    	GridPane.setColumnIndex(radioButtonPane, 0);
    });
    
    // If Radio Button (LEFT) is selected, align Tabs to top of pane
    rbLeft.setOnAction( e -> {
    	tabPane.setSide(Side.LEFT);
    	tabPane.setTabMinHeight(Control.USE_PREF_SIZE);
    	tabPane.setMaxHeight(Control.USE_PREF_SIZE);
    	GridPane.setRowIndex(tabPane,  0);
    	GridPane.setColumnIndex(tabPane, 0);
    	GridPane.setRowIndex(radioButtonPane,  1);
    	GridPane.setColumnIndex(radioButtonPane, 0);
    });
    
    // If Radio Button (RIGHT) is selected, align Tabs to top of pane
    rbRight.setOnAction( e -> {
    	tabPane.setSide(Side.RIGHT);
    	tabPane.setTabMinHeight(Control.USE_PREF_SIZE);
    	tabPane.setMaxHeight(Control.USE_PREF_SIZE);
    	GridPane.setRowIndex(tabPane,  0);
    	GridPane.setColumnIndex(tabPane, 1);
    	GridPane.setRowIndex(radioButtonPane,  1);
    	GridPane.setColumnIndex(radioButtonPane, 1);
    });
  }
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   * line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}