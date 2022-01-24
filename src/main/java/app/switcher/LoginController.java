package app.switcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class LoginController {

    private Preferences p;
    private Backend bk;

    @FXML
    private Button login;

    @FXML
    private PasswordField passw;

    public void handleCloseButtonAction(ActionEvent event) {

        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
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

    public void handleLoginPressed(ActionEvent event) {
        bk.setKey(passw.getText());
        bk.readFromFile();
        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
    }

}
