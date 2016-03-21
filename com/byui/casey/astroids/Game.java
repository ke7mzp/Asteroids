/**
 * 
 */
package com.byui.casey.astroids;

import java.util.ArrayList;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Casey Moffett
 * @copywrite Sep 13, 2015
 *
 */
public class Game extends Application {
	private Timeline animation;
	private ArrayList<MovablePolygon> display;
	private Pane outPane;
	public static final int HARD = 15;
	public static final int MEDIUM = 10;
	public static final int EASY = 5;
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int SPACE = 4;
	private boolean[] activeKeys = { false, false, false, false, false, };
	private int currentDifficulty;
	private Label numObjects;

	Ship ship;

	/**
	 * 
	 */
	public Game() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		numObjects = new Label();
		numObjects.setText(0 + "");
		display = new ArrayList<MovablePolygon>();
		ship = new Ship();
		outPane = new Pane();
		outPane.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP)
				activeKeys[UP] = true;
			if (e.getCode() == KeyCode.DOWN)
				activeKeys[DOWN] = true;
			if (e.getCode() == KeyCode.RIGHT)
				activeKeys[RIGHT] = true;
			if (e.getCode() == KeyCode.LEFT)
				activeKeys[LEFT] = true;
			if (e.getCode() == KeyCode.SPACE) {
				MovablePolygon b = ((Ship) display.get(display.indexOf(ship)))
						.shoot();
				b.update();
				outPane.getChildren().add(b);
				display.add(b);
			}
		});
		outPane.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.UP)
				activeKeys[UP] = false;
			if (e.getCode() == KeyCode.DOWN)
				activeKeys[DOWN] = false;
			if (e.getCode() == KeyCode.RIGHT)
				activeKeys[RIGHT] = false;
			if (e.getCode() == KeyCode.LEFT)
				activeKeys[LEFT] = false;
			// if (e.getCode() == KeyCode.SPACE)
			// activeKeys[SPACE] = false;
		});
		display.add(ship);
		populate(EASY);
		currentDifficulty = 0;
		animation = new Timeline(new KeyFrame(Duration.millis(50.0),
				e -> move()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		Scene scene = new Scene(outPane, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();

		outPane.requestFocus();

	}

	private void populate(int dificulty) {
		outPane.getChildren().removeAll(display);
		for (int i = 0; i < dificulty; i++)
			display.add(Astroid.randomAstroid());
		outPane.getChildren().addAll(display);
	}

	public void move() {
		// ArrayList<MovablePolygon> display = new
		// ArrayList<>(outPane.getChildren());
		if (activeKeys[UP])
			ship.increaseSpeed();
		if (activeKeys[DOWN])
			ship.decreaseUp();
		if (activeKeys[RIGHT])
			ship.turnRight();
		if (activeKeys[LEFT])
			ship.turnLeft();
		// if (activeKeys[SPACE])
		for (int i = 0; i < outPane.getChildren().size(); i++) {
			if (display.get(i).update())
				for (int j = i + 1; j < display.size(); j++) {
					if (!(display.get(i).getType().equals(display.get(j)
							.getType())))
						if (checkCollision(display.get(i), display.get(j))) {
							ArrayList<Astroid> addValues = display.get(i).hit();
							if (addValues != null) {
								outPane.getChildren().addAll(addValues);
								display.addAll(addValues);
							}
							addValues = display.get(j).hit();
							if (addValues != null) {
								outPane.getChildren().addAll(addValues);
								display.addAll(addValues);
							}
							outPane.getChildren().remove(display.get(i));
							outPane.getChildren().remove(display.get(j));
							display.remove(j);
							display.remove(i);
						}
				}
			else {
				outPane.getChildren().remove(display.get(i));
				display.remove(i);
			}
		}
		if (display.size() == 1 && display.indexOf(ship) >= 0) {
			int d = 0;
			switch (currentDifficulty) {
			case 0:
				d = MEDIUM;
				currentDifficulty++;
				break;
			case 1:
				d = HARD;
				currentDifficulty++;
				break;
			}
			populate(d);
		}
	}

	public static boolean checkCollision(MovablePolygon a, MovablePolygon b) {
		double distanceX = b.getX() - a.getX();
		double distanceY = b.getY() - a.getY();
		double distance = Math.sqrt(distanceX * distanceX + distanceY
				* distanceY);
		if (distance < (b.getSize() + a.getSize()))
			return true;
		return false;
	}

}
