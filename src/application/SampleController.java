package application;

import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;


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
}
