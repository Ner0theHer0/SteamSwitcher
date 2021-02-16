package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmController {
	
	@FXML
	private Label outputMessage;
	
	public void setLabel(String str) {
		this.outputMessage.setText(str);
	}
	
	@FXML 
	public void handleCloseButtonAction(ActionEvent event) {
		((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
	}

}
