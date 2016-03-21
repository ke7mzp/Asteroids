/**
 * 
 */
package com.byui.casey.astroids;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;

/**
 * @author Casey Moffett
 * @Date Oct 1, 2015
 *
 * @Copywright 2015
 */
public abstract class MovablePolygon extends Polygon implements Movable {
	protected double x, y, dx, dy;
	protected double angle;
	private double[][] shape;
	private int size;
	private boolean alive = true;
	private double floor = 999999;
	private boolean bounce;

	public MovablePolygon() {
		double[][] s = { { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 }, };
		this.shape = s;
		this.bounce = false;
	}

	public MovablePolygon(Point center, double[][] shape) {
		this.shape = shape;
		this.x = center.x;
		this.y = center.y;
		this.size = 20;
		this.bounce = false;
	}

	public MovablePolygon(Point center, double[][] shape, int size) {
		this(center, shape);
		this.size = size;
	}

	public double getSpeed() {
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

	public double getFloor() {
		if (this.floor == 999999) {
			double low = this.shape[0][1];
			for (int i = 1; i < shape.length; i++)
				if (this.shape[i][1] > low)
					low = this.shape[i][1];
			this.floor = low;
		}
		return this.floor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.byui.casey.astroids.Movable#update()
	 */
	@Override
	public boolean update() {
		
		  //bounce 
		if(bounce) {
		  if (x <15 || x > Point.xMax - 15) dx *= -1.5; 
		  if (y <20 || y > Point.yMax - 20) dy *= -1.5;
		}
		// loop
		if (alive) {
			if (y < -5)
				y = Point.yMax + 3;
			else if (y > Point.yMax + 5)
				y = -3;
			if (x < -5)
				x = Point.xMax + 3;
			else if (x > Point.xMax + 5)
				x = -3;
			x += dx;
			y += dy;
			ArrayList<Double> l = new ArrayList<Double>();
			Point center = new Point(x, y + 7.5);
			for (int i = 0; i < shape.length; i++) {
				Point p = new Point(shape[i][0] + center.getX(), shape[i][1]
						+ center.getY());
				p = rotate(p, center);
				l.add(p.getX());
				l.add(p.getY());
			}
			ObservableList<Double> list = FXCollections.observableList(l);
			this.getPoints().setAll(list);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.byui.casey.astroids.Movable#rotate(com.byui.casey.astroids.Point,
	 * com.byui.casey.astroids.Point)
	 */
	@Override
	public Point rotate(Point p, Point center) {
		double cosA = Math.cos(deg2rad(angle));
		double sinA = Math.sin(deg2rad(angle));

		Point temp = new Point();
		temp.setX(p.getX() - center.getX());
		temp.setY(p.getY() - center.getY());

		p.setX((temp.getX() * cosA) - (temp.getY() * sinA) + center.getX());
		p.setY((temp.getX() * sinA) + (temp.getY() * cosA) + center.getY());
		return p;
	}

	public double deg2rad(double in) {
		return Math.PI / 180 * in;
	}

	public ArrayList<Astroid> hit() {
		this.setAlive(false);
		return null;
	}

	public void increaseSpeed(double speed) {

		double changeDy = Math.cos(deg2rad(angle)) * (this.getSpeed() + speed);
		double changeDx = Math.sin(deg2rad(angle)) * (this.getSpeed() + speed);

		dy -= changeDy;
		dx += changeDx;

	}

	public int getSize() {
		return this.size;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the dx
	 */
	public double getDx() {
		return dx;
	}

	/**
	 * @param dx
	 *            the dx to set
	 */
	public void setDx(double dx) {
		this.dx = dx;
	}

	/**
	 * @return the dy
	 */
	public double getDy() {
		return dy;
	}

	/**
	 * @param dy
	 *            the dy to set
	 */
	public void setDy(double dy) {
		this.dy = dy;
	}

	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive
	 *            the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public double getMovingAngle() {
		return Math.atan2(dy, dx) * 180.0 / Math.PI;
	}

}
