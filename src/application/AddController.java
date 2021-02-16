package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddController {
	
	@FXML
	private Label label6;
	
	public void setLabel(String str) {
		this.label6.setText(str);
	}
	
	@FXML
	private TextField usrn;
	
	@FXML
	private TextField passw;
	
	@FXML 
	public void handleCloseButtonAction(ActionEvent event) {
		
		((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
	}
	
	@FXML
	public void addAcc(ActionEvent event) throws IOException {
		
		Backend bk = new Backend();
		
		String str = bk.addUser(usrn.getText(), passw.getText());
		
		FXMLLoader ld = new FXMLLoader();
		ld.setLocation(getClass().getResource("Confirmation.fxml"));
		
		Parent popParent = ld.load();
		
		Stage popup = new Stage();
		popup.initStyle(StageStyle.TRANSPARENT);
		
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Add Account");
		popup.setMinWidth(300);
		
		
		Scene scene = new Scene(popParent);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		
		popup.setScene(scene);
		ConfirmController ctr = ld.getController();
		
		ctr.setLabel(str);
		
		popup.showAndWait();
		
		
		if (str.equals("Successfully created new user.")) {
			((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
		}
		
		System.out.println("close");
		//bk = new Backend();
		
		
	}

}
