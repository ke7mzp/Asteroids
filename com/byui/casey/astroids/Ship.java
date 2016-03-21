package com.byui.casey.astroids;

import javafx.scene.paint.Color;

public class Ship extends MovablePolygon {

	private static final double[][] shape = new double[][] { { 0, 0, },
			{ 5, 15, }, { 0, 10, }, { -5, 15, }, };

	public Ship() {
		super(new Point(200.0, 200.0), Ship.shape, 5);
		angle = 0.01;
		dx = 0;
		dy = -.5;
		Point.xMax = 400;
		Point.yMax = 400;
		Point.xMin = 0;
		Point.yMin = 0;
		this.setFill(Color.RED);
		this.setStroke(Color.BLACK);
	}

	public void increaseSpeed() {
		double changeDy = Math.cos(deg2rad(angle)) * .1;
		double changeDx = Math.sin(deg2rad(angle)) * .1;
		dy -= changeDy;
		dx += changeDx;

	}

	public void decreaseUp() {
		// dy += .1;
	}

	public void turnRight() {
		angle += 3;
		if (angle > 360)
			angle -= 360;
	}

	public void turnLeft() {
		angle -= 3;
		if (angle <= 0)
			angle += 360;
	}

	public MovablePolygon shoot() {
		return new Bullet(new Point(this.x, this.y),this.angle, this.getSpeed());
	}

	public String getType() {
		return "SHIP";
	}
}
