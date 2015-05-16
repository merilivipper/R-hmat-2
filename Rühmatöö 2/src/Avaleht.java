
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.animation.*;

public class Avaleht extends Application {

	public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(820, 440);

        InputStream is = Files.newInputStream(Paths.get("pildid/miljon_menu.jpg"));
        Image img = new Image(is);
        is.close();

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(820);
        imgView.setFitHeight(440);

        Menu gameMenu = new Menu();
        gameMenu.setVisible(false);

        root.getChildren().addAll(imgView, gameMenu);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (!gameMenu.isVisible()) {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(0);
                    ft.setToValue(1);

                    gameMenu.setVisible(true);
                    ft.play();
                }
                else {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt -> gameMenu.setVisible(false));
                    ft.play();
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        mängiMuusikat();
    }

	
	public static class MenuButton extends StackPane {
		private Text text;
		public MenuButton(String name) {
			text = new Text(name);
			text.setFont(text.getFont().font(20));
			text.setFill(Color.WHITE);
			
			Rectangle back = new Rectangle(100, 40);
			back.setOpacity(0.8);
			back.setFill(Color.BLACK);
			back.setEffect(new GaussianBlur(3.5));
			
			setAlignment(Pos.CENTER_LEFT);
			getChildren().addAll(back, text);
			
			setOnMouseEntered(event -> {
				back.setTranslateX(10);
				text.setTranslateX(10);
				back.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
			});
			
			setOnMouseExited(event ->{
				back.setTranslateX(0);
				text.setTranslateX(0);
				back.setFill(Color.BLACK);
				text.setFill(Color.WHITE);
			});
			
			DropShadow drop = new DropShadow(50, Color.WHITE);
			drop.setInput(new Glow());
			setOnMousePressed(event -> setEffect(drop));
			setOnMouseReleased(event -> setEffect(null));
		}
	}
	
	private class Menu extends Parent {
		public Menu() {
		    VBox menu0 = new VBox(10);
	        VBox menu1 = new VBox(10);
	
	        menu0.setTranslateX(100);
	        menu0.setTranslateY(200);
	        menu1.setTranslateX(100);
	        menu1.setTranslateY(200);
	        
	        //////////
	        final int offset = 400;
	
	        menu1.setTranslateX(offset);
	
	        MenuButton btnResume = new MenuButton("RESUME");
	        btnResume.setOnMouseClicked(event -> {
	            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
	            ft.setFromValue(1);
	            ft.setToValue(0);
	            ft.setOnFinished(evt -> setVisible(false));
	            ft.play();
	        });
	
	
	        MenuButton btnOptions = new MenuButton("OPTIONS");
	        btnOptions.setOnMouseClicked(event -> {
	            getChildren().add(menu1);
	
	            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
	            tt.setToX(menu0.getTranslateX() - offset);
	
	            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
	            tt1.setToX(menu0.getTranslateX());
	
	            tt.play();
	            tt1.play();
	
	            tt.setOnFinished(evt -> {
	                getChildren().remove(menu0);
	            });
	        });
	
	        MenuButton btnExit = new MenuButton("EXIT");
	        btnExit.setOnMouseClicked(event -> {
	            System.exit(0);
	        });
	
	        MenuButton btnBack = new MenuButton("BACK");
	        btnBack.setOnMouseClicked(event -> {
	            getChildren().add(menu0);
	
	            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
	            tt.setToX(menu1.getTranslateX() + offset);
	
	            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
	            tt1.setToX(menu1.getTranslateX());
	
	            tt.play();
	            tt1.play();
	
	            tt.setOnFinished(evt -> {
	                getChildren().remove(menu1);
	            });
	        });
	
	        MenuButton btnSound = new MenuButton("SOUND");
	        MenuButton btnVideo = new MenuButton("VIDEO");
	
	        menu0.getChildren().addAll(btnResume, btnOptions, btnExit);
	        menu1.getChildren().addAll(btnBack, btnSound, btnVideo);
	
	        Rectangle bg = new Rectangle(820, 440);
	        bg.setFill(Color.GREY);
	        bg.setOpacity(0);
	
	        getChildren().addAll(bg, menu0);
	    }
	}
	
	private void mängiMuusikat() {
		String fail = "file:///C:/Users/Oliver/gitR%C3%BChmat%C3%B6%C3%B62/R%C3%BChmat%C3%B6%C3%B6%202/teemalaul.mp3";
		Media laul = new Media(fail);
		MediaPlayer mediaPlayer = new MediaPlayer(laul);
		mediaPlayer.setVolume(0.1);
		mediaPlayer.play();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
