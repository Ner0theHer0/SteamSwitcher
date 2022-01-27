package com.switcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class CreateEncryptController {

    private Preferences p;
    private Backend bk;

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

    public void handConfirmAction(ActionEvent event) {

        bk.setKey(passw.getText());
        bk.encryptFile();
        p.encryptEnabled = true;
        p.writeToFile();
        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
    }


}
