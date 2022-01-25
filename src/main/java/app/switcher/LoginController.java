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

public class LoginController {

    private Preferences p;
    private Backend bk;

    @FXML
    private Button login;

    @FXML
    private PasswordField passw;

    public void handleCloseButtonAction(ActionEvent event) {

        System.exit(0);
    }

    public void checkBlank() {
        if (passw.getText().equals("")) {
            login.setDisable(true);
        } else {
            login.setDisable(false);
        }
    }

    public void initSettings(Preferences p, Backend bk) {
        this.p = p;
        this.bk = bk;
    }

    public void handleLoginPressed(ActionEvent event) throws Exception {

        if (bk.checkKey(passw.getText())) {
            bk.setKey(passw.getText());
            bk.readFromFile();
            ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
        } else {

            String msg = "Incorrect password";
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
        }

    }

}
