package app.switcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Main.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);

        Preferences p = new Preferences();
        p.readFromFile();

        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        MainController ctr = loader.getController();
        ctr.setPreferences(p);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
