package app.switcher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class PreferencesController {

    private Preferences p;

    ObservableList<String> themeList = FXCollections.
            observableArrayList("dark", "light", "high contrast");

    @FXML
    private CheckBox encryptBox;

    @FXML
    private CheckBox launchBox;

    @FXML
    private ChoiceBox themeChoice;

    @FXML
    private void initialize() {
        //themeChoice.setValue(p.theme);
        themeChoice.setItems(themeList);
    }

    public void initSettings(Preferences p) {
        this.p = p;
        encryptBox.setSelected(p.encryptEnabled);
        launchBox.setSelected(p.closeBeforeLaunch);
        themeChoice.setValue(p.theme);
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
