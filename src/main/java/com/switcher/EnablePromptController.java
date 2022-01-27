package com.switcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class EnablePromptController {

    private Preferences p;
    private Backend bk;

    public void handleCloseButtonAction(ActionEvent event) {

        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
    }

    public void initSettings(Preferences p, Backend bk) {
        this.p = p;
        this.bk = bk;
    }

    public void enableButtonPressed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CreateEncrypt.fxml"));

        Parent addView = loader.load();
        Stage popup = new Stage();

        popup.initStyle(StageStyle.TRANSPARENT);

        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Add Account");
        popup.setMinWidth(300);
        Scene addViewScene = new Scene(addView);
        addViewScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        popup.setScene(addViewScene);

		CreateEncryptController ctr = loader.getController();
		ctr.initSettings(this.p, this.bk);

        popup.showAndWait();
        if (p.encryptEnabled) {
            ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
        }
    }

}
