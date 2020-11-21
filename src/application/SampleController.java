package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class SampleController implements Initializable {
	
	@FXML
	private Label label;
	
	public Button button;
	private Button button1;
	private Button button2;
	private Button button3;
	
	public Backend bk;
	

	public void buttonPushed() {
		
		
		String m = "mess";
		this.label.setText(m);
		//this.button.setStyle("-fx-background-color: #152238;");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		bk = new Backend();
		label.setText("someyhing");
	}
	
	public void changeScreenButtonPushed1(ActionEvent event) throws IOException {
		
		bk.addUser("Ner0", "her0");
		
		Parent FirViewParent = FXMLLoader.load(getClass().getResource("PaneOne.fxml"));
		Scene FirViewScene = new Scene(FirViewParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		
		
		window.setScene(FirViewScene);
		window.show();
	}
	
	public void changeScreenButtonPushed2(ActionEvent event) throws IOException {
		
		Parent SecViewParent = FXMLLoader.load(getClass().getResource("PaneTwo.fxml"));
		Scene SecViewScene = new Scene(SecViewParent);
		//SecViewScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		
		window.setScene(SecViewScene);
		window.show();
	}
	
	public void changeScreenButtonPushed3(ActionEvent event) throws IOException {
		
		Parent ThirViewParent = FXMLLoader.load(getClass().getResource("PaneThree.fxml"));
		Scene ThirViewScene = new Scene(ThirViewParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		
		window.setScene(ThirViewScene);
		window.show();
	}

	public void changeScreenButtonPushed4(ActionEvent event) throws IOException {
	
	Parent ForViewParent = FXMLLoader.load(getClass().getResource("PaneFour.fxml"));
	Scene ForViewScene = new Scene(ForViewParent);
	
	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	
	
	window.setScene(ForViewScene);
	window.show();
	}
}
