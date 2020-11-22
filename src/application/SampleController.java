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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


public class SampleController implements Initializable {
	
	@FXML
	private Button closeButton;
	
	@FXML
	public Label label1;
	@FXML 
	public Button addButton;
	
	public Backend bk;
	
	@FXML
	private TextField usrn;
	
	@FXML
	private TextField passw;
	
	@FXML
	private Button confirm;

	

	public void buttonPushed() {
		//String m = "mess";
		this.label1.setText("asdasdasdasdad");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		bk = new Backend();
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
	
	@FXML
	public void addAcc(ActionEvent event) throws IOException {
		
		//bk = new Backend();
		
		boolean sw = false;
		
		if (usrn.getText() != null && passw.getText() != null) {
			
			if (bk.addUser(usrn.getText(), passw.getText())) {
				sw = true;
			} else {
				sw = false;
			}
			
		}
		
		FXMLLoader ld = new FXMLLoader();
		ld.setLocation(getClass().getResource("Confirmation.fxml"));
		
		Parent popParent = ld.load();
		
		Stage popup = new Stage();
		
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Add Account");
		popup.setMinWidth(300);
		
		
		Scene scene = new Scene(popParent);
		
		
		popup.setScene(scene);
		ConfirmController ctr = ld.getController();
		
		if (sw == true) {
			
			ctr.setLabel("User added successfully");
		} else {
			ctr.setLabel("Error adding user");
		}
		
		popup.showAndWait();
		
		((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
		
		bk = new Backend();
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
