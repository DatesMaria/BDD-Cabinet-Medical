package application;
	
import org.jasypt.util.password.BasicPasswordEncryptor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
			Parent login = loader.load();
	        Scene loginScene = new Scene(login,345,520);
			loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(loginScene);
			primaryStage.setTitle("Centru Medical");
			//primaryStage.getIcons().add(new Image("/images/logo.png"));
			//primaryStage.setResizable(false);
			primaryStage.show();
			
			WindowManager.setCurrentStage(primaryStage);
//			LoginController controller = loader.getController();
//			controller.populateComboBox();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		String encryptedPassword = new BasicPasswordEncryptor().encryptPassword("maria");
		
		if(new BasicPasswordEncryptor().checkPassword("maria", encryptedPassword)) {
			System.out.println("Parola buna");
		}else {
			System.out.println("Parola nebuna");
		}
		System.out.println(encryptedPassword);
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}