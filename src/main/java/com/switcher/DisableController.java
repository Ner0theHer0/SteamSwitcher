package com.switcher;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DisableController {

    private Preferences p;
    private Backend bk;

    public void handleCloseButtonAction(ActionEvent event) {

        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
    }

    public void initSettings(Preferences p, Backend bk) {
        this.p = p;
        this.bk = bk;
    }

    public void confirmDisable(ActionEvent event) {
        p.encryptEnabled = false;
        p.writeToFile();
        bk.decryptFile();
        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
    }

}
