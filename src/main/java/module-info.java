module com.switcher.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;

    opens com.switcher to javafx.fxml;
    exports com.switcher;
}