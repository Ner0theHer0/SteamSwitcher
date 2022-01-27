package com.switcher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PreferencesController {

    private Preferences p;
    private Backend bk;

    ObservableList<String> themeList = FXCollections.
            observableArrayList("dark", "light", "high contrast");

    @FXML
    private CheckBox encryptBox;

    @FXML
    private CheckBox launchBox;

    @FXML
    private ChoiceBox<String> themeChoice;

    @FXML
    private void initialize() {
        themeChoice.setItems(themeList);
    }

    public void initSettings(Preferences p, Backend bk) {
        this.p = p;
        this.bk = bk;
        encryptBox.setSelected(p.encryptEnabled);
        launchBox.setSelected(p.closeBeforeLaunch);
        themeChoice.setValue(p.theme);
    }

    public void handleEncryptToggle() throws Exception {
        if (encryptBox.isSelected()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EnablePrompt.fxml"));

            Parent addView = loader.load();
            Stage popup = new Stage();

            popup.initStyle(StageStyle.TRANSPARENT);

            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Add Account");
            popup.setMinWidth(300);
            Scene addViewScene = new Scene(addView);
            addViewScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            popup.setScene(addViewScene);

            EnablePromptController ctr = loader.getController();
            ctr.initSettings(this.p, bk);

            popup.showAndWait();
            if (!p.encryptEnabled) {
                encryptBox.setSelected(false);
            }
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Disable.fxml"));

            Parent addView = loader.load();
            Stage popup = new Stage();

            popup.initStyle(StageStyle.TRANSPARENT);

            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Add Account");
            popup.setMinWidth(300);
            Scene addViewScene = new Scene(addView);
            addViewScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            popup.setScene(addViewScene);

            DisableController ctr = loader.getController();
            ctr.initSettings(this.p, bk);

            popup.showAndWait();
            if (p.encryptEnabled) {
                encryptBox.setSelected(true);
            }
        }
    }

    public void handleResetButton(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Reset.fxml"));

        Parent addView = loader.load();
        Stage popup = new Stage();

        popup.initStyle(StageStyle.TRANSPARENT);

        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Add Account");
        popup.setMinWidth(300);
        Scene addViewScene = new Scene(addView);
        addViewScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        popup.setScene(addViewScene);

        ResetController ctr = loader.getController();
        ctr.initSettings(this.p, bk);

        popup.showAndWait();
    }

    public void handleApplyButton(ActionEvent event) {
        p.encryptEnabled = encryptBox.isSelected();
        p.closeBeforeLaunch = launchBox.isSelected();
        p.theme = themeChoice.getValue().toString();
        p.writeToFile();
        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
    }

    public void handleCloseButtonAction(ActionEvent event) {

        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
    }

}
