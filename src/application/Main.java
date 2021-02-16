package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		 Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		 
		 primaryStage.initStyle(StageStyle.TRANSPARENT);
	        
	     Scene scene = new Scene(root);
	     scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        
	     
	     primaryStage.setScene(scene);
	     primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
