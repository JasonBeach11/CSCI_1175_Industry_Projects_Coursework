/**
 * @author Jason
 * @Date 06/07/2023
 * CSCI 1175: Industry Projects
 * Exercise 32-3: (Raise flags) Rewrite Listing 15.13 using a thread to animate a flag being raised
 * */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.application.Platform;

public class FlagRisingAnimation extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        ImageView imageView = new ImageView("image/us.gif");
        pane.getChildren().add(imageView);

        Line line = new Line(45, 250, 45, 0);  // Create a line for the flag to travel on
        
        imageView.setX(line.getStartX());
        imageView.setY(line.getStartY());

        Thread animationThread = new Thread(() -> {
        	
            double changeY = -1; // Change in y-coordinate per iteration
            double y = line.getStartY();
            
            while (y > line.getEndY()) {
                y += changeY;
                double newY = y;
                
                Platform.runLater(() -> imageView.setY(newY));
                
                try {
                    Thread.sleep(15); // Pause between each iteration
                } 
                catch 
                	(InterruptedException e) {
                    	e.printStackTrace();
                }
                
            }
        });

        Scene scene = new Scene(pane, 250, 200);  // Create a scene
        primaryStage.setTitle("FlagRisingAnimation");  // Set a title for the stage
        primaryStage.setScene(scene);  // Place the scene in the stage
        primaryStage.show();  // Display the stage

        animationThread.start();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
