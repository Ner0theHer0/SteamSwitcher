module app.switcher {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;

    opens app.switcher to javafx.fxml;
    exports app.switcher;
}