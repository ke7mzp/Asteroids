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
public interface Movable {
	public boolean update();
	public Point rotate(Point p, Point center);
	public String getType();
}
