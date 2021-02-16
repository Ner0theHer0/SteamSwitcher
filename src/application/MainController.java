package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;


public class MainController implements Initializable {
	
	public Backend bk;
	private double x = 0;
	private double y = 0;
	
	// FXML declarations 
	@FXML Button exitButton;
	@FXML Button minButton;
	
	@FXML
	private Stage stage;
	
	@FXML
	private HBox hboxtop;
	
	@FXML
	private AnchorPane basePane;
	
	// User list table
	@FXML
	private TableView<User> tView;
	@FXML
	private TableColumn<User, String> userCol;
	
	private ObservableList<User> ls = FXCollections.observableArrayList();
	
	// Pulls data from the user list and adds it to the ObservableList
	public void setTable() {
		ls.clear();
		for (HashMap.Entry<String, User> entry : bk.map.entrySet()) {
		    ls.add(entry.getValue());
		}
		
		userCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		tView.setItems(ls);
		tView.setPrefHeight((double) bk.getNumUsers()*24 + 30);
	}

	/* Initialises a draggable window, refreshes user list
	 * and initialises table
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		makeDragable();
		bk = new Backend();
		setTable();
		tView.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println(tView.getSelectionModel().getSelectedItem().getUsername());
			}	
		});
	}
	
	/* Makes the window draggable by the top bar, since the
	 * transparent stage style hides the default draggable
	 * window outline.
	 */
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
	
	/* Opens the window to capture new user data when the
	 * add account button is clicked
	 */
	
	public void addAccountButtonPushed(ActionEvent event) throws IOException {
		
		Parent addView = FXMLLoader.load(getClass().getResource("Popup.fxml"));	
		
		Stage popup = new Stage();
		
		popup.initStyle(StageStyle.TRANSPARENT);
		
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Add Account");
		popup.setMinWidth(300);
		Scene addViewScene = new Scene(addView);
		addViewScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		popup.setScene(addViewScene);
		
		popup.showAndWait();
		bk = new Backend();
		setTable();
	}
	
	/* Minimise action since the default minimise button is
	 * hidden by the transparent stage style
	 */
	public void minimiseWindow(ActionEvent event) {
		stage = (Stage)basePane.getScene().getWindow();
		stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		
		stage.setIconified(true);
	}
	
	
	@FXML 
	public void handleCloseButtonAction(ActionEvent event) {
		
		((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
	}
}
