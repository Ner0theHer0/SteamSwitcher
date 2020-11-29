package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;


public class SampleController implements Initializable {
	
	@FXML
	private Button closeButton;
	
	@FXML
	public Label label1;
	@FXML 
	public Button addButton;
	
	@FXML Button exitButton;
	@FXML Button minButton;
	
	public Backend bk;
	
	@FXML
	private TextField usrn;
	
	@FXML
	private TextField passw;
	
	@FXML
	private Button confirm;
	
	@FXML
	private HBox hboxtop;
	
	@FXML
	private AnchorPane basePane;
	
	private double x = 0;
	private double y = 0;
	
	@FXML
	private Stage stage;
	
	@FXML
	private TableView<String> tView;
	@FXML
	private TableColumn<String, String> colName;
	
	ObservableList<String> ls = FXCollections.observableArrayList(
			new String ("something"),
			new String ("red"),
			new String ("rofl")
			
			);

	

	public void buttonPushed() {
		//String m = "mess";
		this.label1.setText("asdasdasdasdad");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		colName.setCellValueFactory(new PropertyValueFactory<String, String>("colName"));
		tView.setItems(ls);
		makeDragable();
		bk = new Backend();
	}
	
	public void makeDragable() {
		
		hboxtop.setOnMousePressed((event) -> {		
			x = event.getSceneX();
			y = event.getSceneY();
		});
		
		hboxtop.setOnMouseDragged((event) -> {
			stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.setX(event.getScreenX() - x);
			stage.setY(event.getScreenY() - y);
		});
	}
	
	public void minimiseWindow(ActionEvent event) {
		stage = (Stage)basePane.getScene().getWindow();
		stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		
		stage.setIconified(true);
	}
	
	public void addAccountButtonPushed(ActionEvent event) throws IOException {
		Parent popParent = FXMLLoader.load(getClass().getResource("Popup.fxml"));
		
		
		Stage popup = new Stage();
		
		popup.initStyle(StageStyle.TRANSPARENT);
		
		
		
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Add Account");
		popup.setMinWidth(300);
		
		
		Scene scene = new Scene(popParent);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		popup.setScene(scene);
		popup.showAndWait();
		
	}
	
	@FXML
	public void addAcc(ActionEvent event) throws IOException {
		
		String str = bk.addUser(usrn.getText(), passw.getText());
		
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
		
		
		if (str.equals("Successfully created new user.")) {
			((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
		}
		
		
		bk = new Backend();
	}
	
	public void changeScreenButtonPushed(ActionEvent event) throws IOException {
		
		Parent SecViewParent = FXMLLoader.load(getClass().getResource("SampleStateChange.fxml"));
		Scene SecViewScene = new Scene(SecViewParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(SecViewScene);
		window.show();
	}
	
	@FXML 
	public void handleCloseButtonAction(ActionEvent event) {
		
		((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
	}
}
