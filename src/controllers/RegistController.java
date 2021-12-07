package controllers;

import application.Main;
import java.lang.Thread;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import models.RegistModel;

public class RegistController {

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;
	
	@FXML
	private CheckBox isAdmin;

	@FXML
	private Label lblError;

	private RegistModel model;

	public RegistController() {
		model = new RegistModel();
	}

	public void regist() {

		lblError.setText("");
		String username = this.txtUsername.getText();
		String password = this.txtPassword.getText();
	    // is regist admin ?
	    Boolean isAdmin = this.isAdmin.isSelected();

		// Validations
		if (username == null || username.trim().equals("")) {
			lblError.setText("Username Cannot be empty or spaces");
			return;
		}
		if (password == null || password.trim().equals("")) {
			lblError.setText("Password Cannot be empty or spaces");
			return;
		}
		if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			lblError.setText("User name / Password Cannot be empty or spaces");
			return;
		}

		// authentication check
		registUser(username, password, isAdmin);

	}

	public void registUser(String username, String password, Boolean isAdmin) {
		Boolean isValid = model.getCredentials(username, password);
		
		if (!isValid) {
			lblError.setText("User does not exist!");
			return;
		}
		
		Boolean isRigist = model.registUser(username, password, isAdmin);
		if (isRigist) {
			lblError.setText("Regist success!");
			
			try {
				Thread.sleep(3000);
				AnchorPane root;
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
				Main.stage.setTitle("Login View");

				Scene scene = new Scene(root);
				Main.stage.setScene(scene);

			} catch (Exception e) {
				System.out.println("Error occured while inflating view: " + e);
			}
		}
		
		

	}
	
	public void initialTable () {
		model.initialTable();
	}
}