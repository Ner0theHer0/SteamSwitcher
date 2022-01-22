package app.switcher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import org.apache.commons.lang3.SystemUtils;

public class MainController implements Initializable {
	
	public Backend bk;
	private double x = 0;
	private double y = 0;
	private User us;
	
	// FXML declarations 
	@FXML Button exitButton;
	@FXML Button minButton;
	@FXML Button launchButton;
	@FXML Button editButton;
	@FXML Button delButton;
	
	@FXML
	private Stage stage;
	
	@FXML
	private HBox hboxtop;

	@FXML
	private Label label;
	
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
		tView.setPrefHeight((double) bk.getNumUsers()*24 + 28);
		tView.setMaxHeight((double)297);
		if (us != null) {
			label.setText("Please select a user");
			launchButton.setVisible(false);
			editButton.setVisible(false);
			delButton.setVisible(false);
		}
	}

	/* Initialises a draggable window, refreshes user list
	 * and initialises table
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		makeDragable();
		bk = new Backend();
		setTable();
		launchButton.setVisible(false);
		editButton.setVisible(false);
		delButton.setVisible(false);

		tView.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				launchButton.setVisible(true);
				editButton.setVisible(true);
				delButton.setVisible(true);
				us = tView.getSelectionModel().getSelectedItem();
				label.setText("Selected account: " + us.getUsername());
			}	
		});
	}

	public void openSteam(String u, String pw) {
		ProcessBuilder processBuilder = new ProcessBuilder();

		if (SystemUtils.IS_OS_LINUX) {
			String command = "steam -login " + u + " " + pw;
			System.out.println(command);
			processBuilder.command("bash", "-c", command);
			processBuilder.redirectErrorStream(true);
			try {
				Process p = processBuilder.start();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		if (SystemUtils.IS_OS_WINDOWS) {
			processBuilder.command("cmd.exe", "/c", "steam");
			processBuilder.redirectErrorStream(true);
			try {
				Process p = processBuilder.start();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

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

	public void editAccountButtonPushed(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Popup.fxml"));

		Parent addView = loader.load();
		Stage popup = new Stage();

		popup.initStyle(StageStyle.TRANSPARENT);

		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Add Account");
		popup.setMinWidth(300);
		Scene addViewScene = new Scene(addView);
		addViewScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		popup.setScene(addViewScene);
		AddController ctr = loader.getController();

		ctr.setLabel(us.getUsername());

		popup.showAndWait();
		bk = new Backend();
		setTable();
	}

	public void deleteAccountButtonPushed(ActionEvent event) throws IOException {
		System.out.println("delete");
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(getClass().getResource("Popup.fxml"));
//
//		Parent addView = loader.load();
//		Stage popup = new Stage();
//
//		popup.initStyle(StageStyle.TRANSPARENT);
//
//		popup.initModality(Modality.APPLICATION_MODAL);
//		popup.setTitle("Add Account");
//		popup.setMinWidth(300);
//		Scene addViewScene = new Scene(addView);
//		addViewScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		popup.setScene(addViewScene);
//		AddController ctr = loader.getController();
//
//		ctr.setLabel(us.getUsername());
//
//		popup.showAndWait();
//		bk = new Backend();
//		setTable();
	}

	public void launchButtonPushed(ActionEvent event) throws IOException {
		openSteam(us.getUsername(), us.getPassword());
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
