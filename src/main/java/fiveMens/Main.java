package fiveMens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends  Application {

	public static void main( String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(getClass().getResource("/fxml/test.fxml"));
		
		VBox vBox = loader.load();
		
		Scene scene = new Scene(vBox);
		
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("pierwsze okno");
		primaryStage.show();
		
	}
		
	
}
