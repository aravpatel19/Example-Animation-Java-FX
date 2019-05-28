import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;

import javafx.application.Application;
import javafx.stage.Stage;
public class Example extends Application implements EventHandler<InputEvent>{
	GraphicsContext gc;
	Image trooper;
	int x = 100;
	AnimateObjects animate;
	boolean right = true;
	AudioClip clip;

	public static void main(String[] args){

		launch();
	}

	public void start(Stage stage){

		stage.setTitle("Final Project Title");
		Group root = new Group();
		Canvas canvas = new Canvas(800, 400);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, this);
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

		gc = canvas.getGraphicsContext2D();
		trooper = new Image("trooper.jpg");
		gc.drawImage(trooper, 180+x, 100);

		animate = new AnimateObjects();
		animate.start();

		stage.show();

		URL resource = getClass().getResource("test.wav");
		clip = new AudioClip(resource.toString());
	}
	public void handle(final InputEvent event){
		if(event instanceof KeyEvent){
			if(((KeyEvent)event).getCode() == KeyCode.LEFT)
				x--;
		}
		if(event instanceof MouseEvent){
			System.out.println(((MouseEvent)event).getX());
			System.out.println(((MouseEvent)event).getY());
		}
		if(x<50)
			clip.play();

	}


	public class AnimateObjects extends AnimationTimer{
		public void handle(long now){
			if(x>=50){
				gc.drawImage(trooper, 180+x, 100);
			}
			else{
				gc.setFill(Color.YELLOW);
				gc.setStroke(Color.BLACK);
				gc.setLineWidth(1);
				Font font  = Font.font("Arial", FontWeight.NORMAL, 48);
				gc.setFont(font);
				gc.fillText("Game Over", 100, 50);
				gc.strokeText("Game Over", 100, 50);
			}
		}
	}
}
