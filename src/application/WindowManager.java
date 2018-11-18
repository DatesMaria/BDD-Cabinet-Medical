package application;

import javafx.stage.Stage;

public class WindowManager {
	
	private static Stage currentStage;
	
	public static Stage getCurrentStage() {
		return currentStage;
	}
	
	public static void setCurrentStage(Stage stage) {
		if(currentStage != null) {
			currentStage.close();
		}
		WindowManager.currentStage = stage;
	}

}
