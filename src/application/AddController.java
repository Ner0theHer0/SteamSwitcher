package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AddController {
	
	@FXML
	private Label label6;
	
	public void setLabel(String str) {
		this.label6.setText(str);
	}
	
	@FXML 
	public void handleCloseButtonAction(ActionEvent event) {
		
		((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
	}

}
