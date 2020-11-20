package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


public class SampleController implements Initializable {
	
	@FXML
	private Button closeButton;
	private Label label;
	public Button addButton;
	

	public void buttonPushed() {
		String m = "mess";
		this.label.setText(m);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	public void addAccountButtonPushed(ActionEvent event) throws IOException {
		Parent popParent = FXMLLoader.load(getClass().getResource("Popup.fxml"));
		Stage popup = new Stage();
		
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Add Account");
		popup.setMinWidth(300);
		
		
		Scene scene = new Scene(popParent);
		popup.setScene(scene);
		
		popup.showAndWait();
	}
	
	public void changeScreenButtonPushed(ActionEvent event) throws IOException {
		
		Parent SecViewParent = FXMLLoader.load(getClass().getResource("SampleStateChange.fxml"));
		Scene SecViewScene = new Scene(SecViewParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(SecViewScene);
		window.show();
	}
	
	@FXML 
	public void handleCloseButtonAction(ActionEvent event) {
		((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
	}
}
