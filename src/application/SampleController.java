package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class SampleController implements Initializable {
	
	@FXML
	private Label label;
	

	public void buttonPushed() {
		String m = "mess";
		this.label.setText(m);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		label.setText("someyhing");
	}
	
	public void changeScreenButtonPushed(ActionEvent event) throws IOException {
		
		Parent SecViewParent = FXMLLoader.load(getClass().getResource("SampleStateChange.fxml"));
		Scene SecViewScene = new Scene(SecViewParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(SecViewScene);
		window.show();
	}
}
