package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	public static Stage primaryStage;
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage=primaryStage;
			primaryStage.setResizable(false);
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("/vue/MenuPrincipal.fxml"));
			Scene scene = new Scene(root,465,628);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Imperial Hotel");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}