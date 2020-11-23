package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		 Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		 
		 primaryStage.initStyle(StageStyle.TRANSPARENT);
		 
		 
		 
//		 hboxtop.setOnMousePressed(new EventHandler<MouseEvent>() {
//			 @Override
//	         public void handle(MouseEvent event) {
//				 xOffset = event.getSceneX();
//	             yOffset = event.getSceneY();
//	         }
//	     });
//	        
//	        
//	     hboxtop.setOnMouseDragged(new EventHandler<MouseEvent>() {
//	    	 @Override
//	         public void handle(MouseEvent event) {
//	    		 primaryStage.setX(event.getScreenX() - xOffset);
//	             primaryStage.setY(event.getScreenY() - yOffset);
//	         }
//	     });	
	        
	     Scene scene = new Scene(root);
	     scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        
	     
	     primaryStage.setScene(scene);
	     primaryStage.show();
//		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
