package app.switcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ResetController {

    private Preferences p;
    private Backend bk;

    @FXML
    private PasswordField oldPass;

    @FXML
    private PasswordField passw;

    @FXML
    private PasswordField confirmPassw;

    @FXML
    private Button confirm;

    public void handleCloseButtonAction(ActionEvent event) {

        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
    }

    public void initSettings(Preferences p, Backend bk) {
        this.p = p;
        this.bk = bk;
    }

    public void checkMatching() {
        if (passw.getText().equals(confirmPassw.getText())) {
            if (!passw.getText().equals("")) {
                confirm.setDisable(false);
            }
        } else {
            confirm.setDisable(true);
        }
    }

    public void handConfirmAction(ActionEvent event) throws Exception {
        String msg;
        if (bk.checkKey(oldPass.getText())) {
            bk.decryptFile();
            bk.setKey(passw.getText());
            bk.encryptFile();
            msg = "Successfully updated password";
        } else {
            msg = "Incorrect password";
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

        ctr.setLabel(msg);

        popup.showAndWait();

        if (msg.substring(0, 13).equals("Successfully ")) {
            ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
        }
    }


}
