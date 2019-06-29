package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Phonebook extends Application {
	//tells the program which fxml to use and what name to display for the window
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("Phonebook.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Phoneboook Editor by Angel");
		stage.setScene(scene);
		stage.show();
	}
	
	//launches the program
	public static void main(String[] args) {
		launch(args);
	}
}