package com.byui.casey.astroids;

import javafx.scene.paint.Color;

public class Bullet extends MovablePolygon {
	public static double[][] SHAPE = new double[][] {{-2,2},{2,2},{2,-2},{1,-3},{-1,-3},{-2,-2}};
	public static final int LIFE = 100;
	private int framesLived = 0;

	public Bullet() {
		// TODO Auto-generated constructor stub
	}

	public Bullet(Point center, double[][] shape) {
		super(center, shape);
		// TODO Auto-generated constructor stub
	}
	
	Bullet(Point center){
		super(center,Bullet.SHAPE);
		this.setFill(Color.BLACK);
		this.setStroke(Color.BLACK);
	}
	
	Bullet(Point center, double angle, double initSpeed){
		super(center,Bullet.SHAPE, 1);
		this.dx =Math.sin(deg2rad(angle))*(((initSpeed < 1)? 1:initSpeed)+ 2.5);
        this.dy =-Math.cos(deg2rad(angle))*(((initSpeed < 1)? 1:initSpeed) + 2.5);
//		this.increaseSpeed(1.4);

		this.update();
		this.setFill(Color.BLACK);
		this.setStroke(Color.BLACK);
	}

	@Override
	public boolean update(){
		++framesLived;
		super.update();
		if (framesLived > LIFE)
			return false;
		return true;
	}

	@Override
	public String getType() {
		return "BULLET";
	}

}
