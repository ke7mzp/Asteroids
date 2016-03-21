/**
 * 
 */
package com.byui.casey.astroids;

/**
 * @author Casey Moffett
 * @Date Oct 1, 2015
 *
 * @Copywright 2015
 */
public class Point {
	public double x, y;
	public static double xMax, xMin, yMax, yMin;

	Point() {
		Point.xMax = 400;
		Point.xMin = 0;
		Point.yMax = 400;
		Point.yMin = 0;
	}

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}