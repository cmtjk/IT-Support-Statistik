package de.r3r57.itsupport.statistik;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Starter extends Application {

	private String myFilePath;

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Hauptfenstertitel
		primaryStage.setTitle("IT-Support: Statistik");
		primaryStage.setResizable(false);

		// Icon
		myFilePath = new File("").getAbsolutePath();
		File icon = new File(myFilePath + "/config/icon.png");
		primaryStage.getIcons().add(
				new Image("file:///"
						+ icon.getAbsolutePath().replace("\\", "/")));

		// Richtiges schließen der Anwendung
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				// System.exit(0); laggt beim Beenden
			}
		});
		primaryStage.setScene(new Record_GUI());
		primaryStage.show();

	}

	public static void main(String[] args) {
		try {

			launch(args);
		} catch (Exception e) {
			System.out.println("Alles kaputt");
			e.printStackTrace();
		}
	}

}
