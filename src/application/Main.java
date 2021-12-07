package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage stage; // set global stage object!!!

	@Override
	public void start(Stage primaryStage) {
	
		//primaryStage.hide();

		try {
			stage = primaryStage;
			System.out.println("1");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			System.out.println("2");
			Scene scene = new Scene(root);
			System.out.println("3");
			scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
			System.out.println("4");
			stage.setTitle("Login View");
			System.out.println("5");
			stage.setScene(scene);
			System.out.println("6");
			stage.show();
			System.out.println("7");
		} catch (Exception e) {
			System.out.println("show1: ");
			System.out.println("Error occured while inflating view: " + e);
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}
