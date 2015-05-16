
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

public class Avaleht extends Application {
	public void start(Stage primaryStage) throws Exception {
		StackPane juur = new StackPane();
		juur.setPrefSize(720, 340);
		
		InputStream is = Files.newInputStream(Paths.get("pildid/miljon_menu.jpg"));
		Image pilt = new Image(is);
		is.close();
		
		ImageView imgv = new ImageView(pilt);
		juur.getChildren().addAll(imgv);
		
		Scene stseen = new Scene(juur);
		primaryStage.setScene(stseen);
		
//		juur.maxWidthProperty().bind(stseen.widthProperty().divide(2));
//	    juur.minWidthProperty().bind(stseen.widthProperty().divide(2));
//	    juur.maxHeightProperty().bind(stseen.heightProperty().divide(2));
//	    juur.minHeightProperty().bind(stseen.heightProperty().divide(2));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
