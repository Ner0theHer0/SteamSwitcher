package app.switcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AddController {

	public String edit;
	
	@FXML
	private TextField usrn;
	
	@FXML
	private TextField passw;

	@FXML
	private Button confirm;
	
	@FXML 
	public void handleCloseButtonAction(ActionEvent event) {
		
		((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
	}

	public void setLabel(String str) {
		this.usrn.setText(str);
		this.edit = str;
		this.passw.setPromptText("(unchanged)");
		this.confirm.setText("Confirm");
		this.confirm.setPrefWidth(104);
	}
	
	@FXML
	public void addAcc(ActionEvent event) throws IOException {
		
		Backend bk = new Backend();
		String str;
		if (edit != null) {
			str = bk.editUser(edit, usrn.getText(), passw.getText());
		} else {
			str = bk.addUser(usrn.getText(), passw.getText());
		}
		
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

		if (str.substring(0, 13).equals("Successfully ")) {
			((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
		}
	}

}
