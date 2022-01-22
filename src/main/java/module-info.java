module app.switcher {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.switcher to javafx.fxml;
    exports app.switcher;
}