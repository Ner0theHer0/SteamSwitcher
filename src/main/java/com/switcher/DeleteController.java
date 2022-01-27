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

public class DeleteController {

    private String user;

    private Backend bk;

    public void handleCloseButtonAction(ActionEvent event) {

        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
    }

    public void setLabel(String u) {
        this.user = u;
    }

    public void setBk(Backend bk) {
        this.bk = bk;
    }

    public void delAcc(ActionEvent event) throws IOException {

        String str = bk.removeUser(user);

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

        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();

    }

}
