/**
 * @author Jason Beach
 * @Date 06/21/2023
 * CSCI 1175: Industry Projects
 * Exercise 31-22: Use Table View
 * This program is a basic demo of TableView and how it works. The table displays a list of
 * countries, their capitals, population, and whether or not they are democratic. New rows
 * can be added to the table by the user. Rows can be deleted using the
 * "Delete Selected Row" button.
 * */
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class AddNewRowDemo extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
	// Create TableView and initialize with data for 5 countries
	TableView<Country> tableView = new TableView<>();
    
	// ObservableList to initialize table with data
	ObservableList<Country> data =
      FXCollections.observableArrayList(
        new Country("USA", "Washington DC", 280, true),
        new Country("Canada", "Ottawa", 32, true),
        new Country("United Kingdom", "London", 60, true),
        new Country("Germany", "Berlin", 83, true),
        new Country("France", "Paris", 60, true));
    tableView.setItems(data);
    
    // Create Country Column
    TableColumn countryColumn = new TableColumn("Country");
    countryColumn.setMinWidth(130);
    countryColumn.setCellValueFactory(
      new PropertyValueFactory<Country, String>("country"));
    
    // Create Capital Column
    TableColumn capitalColumn = new TableColumn("Capital");
    capitalColumn.setMinWidth(130);
    capitalColumn.setCellValueFactory(
      new PropertyValueFactory<Country, String>("capital"));

    // Create Population Column
    TableColumn populationColumn = 
      new TableColumn("Population (million)");
    populationColumn.setMinWidth(130);
    populationColumn.setCellValueFactory(
      new PropertyValueFactory<Country, Double>("population"));

    // Create is Democratic Column
    TableColumn democraticColumn = 
      new TableColumn("Is Democratic?");
    democraticColumn.setMinWidth(100);
    democraticColumn.setCellValueFactory(
      new PropertyValueFactory<Country, Boolean>("democratic"));

    // Add all columns to tableView
    tableView.getColumns().addAll(countryColumn, capitalColumn,
      populationColumn, democraticColumn);

    FlowPane flowPane = new FlowPane(3, 3);
    TextField tfCountry = new TextField();
    TextField tfCapital = new TextField();
    TextField tfPopulation = new TextField();
    CheckBox chkDemocratic = new CheckBox("Is democratic?");
    Button btAddRow = new Button("Add new row");
    tfCountry.setPrefColumnCount(5);
    tfCapital.setPrefColumnCount(5);
    tfPopulation.setPrefColumnCount(5);
    flowPane.getChildren().addAll(new Label("Country: "),
      tfCountry, new Label("Capital"), tfCapital, 
      new Label("Population"), tfPopulation, chkDemocratic, 
      btAddRow);
    
    // Event handler to add new row to table
    btAddRow.setOnAction(e -> {
      data.add(new Country(tfCountry.getText(), tfCapital.getText(), 
        Double.parseDouble(tfPopulation.getText()), 
        chkDemocratic.isSelected()));
      tfCountry.clear();
      tfCapital.clear();
      tfPopulation.clear();
    });
    
    //Create "Delete Selected Row" Button and add to top of pane
    Button btDeleteRow = new Button("Delete Selected Row");
    HBox btBox = new HBox();	// HBox to hold button so button can be aligned to center
    btBox.getChildren().add(btDeleteRow);
    btBox.setAlignment(Pos.CENTER);
    
    BorderPane pane = new BorderPane();
    pane.setTop(btBox);
    pane.setCenter(tableView);
    pane.setBottom(flowPane);
    
    // EVENT HANDLER FOR DELETE BUTTON
    btDeleteRow.setOnAction( e -> {
    	Country selectedCountry = tableView.getSelectionModel().getSelectedItem();
    	if(selectedCountry != null) {
    		tableView.getItems().remove(selectedCountry);
    	}
    });
    
    Scene scene = new Scene(pane, 500, 250);  
    primaryStage.setTitle("AddNewRowDemo"); // Set the window title
    primaryStage.setScene(scene); // Place the scene in the window
    primaryStage.show(); // Display the window
  }

  public static class Country {
    private final SimpleStringProperty country;
    private final SimpleStringProperty capital;
    private final SimpleDoubleProperty population;
    private final SimpleBooleanProperty democratic;

    private Country(String country, String capital,
        double population, boolean democratic) {
      this.country = new SimpleStringProperty(country);
      this.capital = new SimpleStringProperty(capital);
      this.population = new SimpleDoubleProperty(population);
      this.democratic = new SimpleBooleanProperty(democratic);
    }

    public String getCountry() {
      return country.get();
    }

    public void setCountry(String country) {
      this.country.set(country);
    }

    public String getCapital() {
      return capital.get();
    }

    public void setCapital(String capital) {
      this.capital.set(capital);
    }

    public double getPopulation() {
      return population.get();
    }

    public void setPopulation(double population) {
      this.population.set(population);
    }

    public boolean isDemocratic() {
      return democratic.get();
    }

    public void setDemocratic(boolean democratic) {
      this.democratic.set(democratic);
    }
  } // End Country Class
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   * line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}