/**
 * 
 */
package com.byui.casey.astroids;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

/**
 * @author Casey Moffett
 * @Date Oct 1, 2015
 *
 * @Copywright 2015
 */
public class Astroid extends MovablePolygon {
	protected static final double[][] LARGE_ASTROID = new double[][] {
			{ 0, 12 }, { 8, 20 }, { 16, 14 }, { 10, 12 }, { 20, 0 },
			{ 0, -20 }, { -18, -10 }, { -20, -2 }, { -20, 14 }, { -10, 20 },
			{ 0, 12 } };
	protected static final double[][] MEDIUM_ASTROID = { { 2, 8 }, { 8, 15 },
			{ 12, 8 }, { 6, 2 }, { 12, -6 }, { 2, -15 }, { -6, -15 },
			{ -14, -10 }, { -15, 0 }, { -4, 15 }, { 2, 8 } };
	protected static final double[][] SMALL_ASTROID = { { -5, 9 }, { 4, 8 },
			{ 8, 4 }, { 8, -5 }, { -2, -8 }, { -2, -3 }, { -8, -4 }, { -8, 4 },
			{ -5, 10 } };

	static Random rand = new Random();

	public Astroid() {
		super(new Point(100.0, 100.0), LARGE_ASTROID, 15);
		dy = Math.random();
		dx = Math.random();
		this.setFill(Color.GRAY);
		this.setStroke(Color.BLACK);
	}

	public Astroid(Point center) {
		super(center, LARGE_ASTROID, 15);
		dy = Math.random();
		dx = Math.random();
		this.setFill(Color.GRAY);
		this.setStroke(Color.BLACK);
	}

	public Astroid(double[][] shape, int size) {
		super(new Point(rand.nextInt((int) Point.xMax),
				rand.nextInt((int) Point.yMax)), shape, size);
		setUp();
	}

	public Astroid(Point center, double[][] shape, int size) {
		super(center, shape, size);
		setUp();
	}

	private void setUp() {
		dy = Math.random();
		dx = Math.random();
		this.setFill(Color.GRAY);
		this.setStroke(Color.BLACK);
	}
	
	protected void increase(){
		this.angle = getMovingAngle();
		this.increaseSpeed(.5);
	}

	public static Astroid randomAstroid() {
		Astroid rAstroid;
		int sValue = rand.nextInt(90) % 3;
		switch (sValue) {
		case 0:
			rAstroid = new LargeAstroid(new Point(
					rand.nextInt((int) Point.xMax),
					rand.nextInt((int) Point.yMax)));
			break;
		case 1:
			rAstroid = new MediumAstroid(new Point(
					rand.nextInt((int) Point.xMax),
					rand.nextInt((int) Point.yMax)));
			break;
		case 2:
			rAstroid = new SmallAstroid(new Point(
					rand.nextInt((int) Point.xMax),
					rand.nextInt((int) Point.yMax)));
			break;
		default:
			rAstroid = new LargeAstroid();
		}
		return rAstroid;
	}

	
	public String getType(){
		return "ASTROID";
	}
}

class LargeAstroid extends Astroid {

	public LargeAstroid() {

	}

	public LargeAstroid(Point center) {
		super(center, Astroid.LARGE_ASTROID, 15);
	}

	@Override
	public ArrayList<Astroid> hit() {
		this.setAlive(false);
		ArrayList<Astroid> rValue = new ArrayList<>();
		rValue.add(new MediumAstroid(new Point(this.x,this.y)));
		rValue.add(new MediumAstroid(new Point(this.x,this.y)));
		rValue.add(new SmallAstroid(new Point(this.x,this.y)));
		for (int i = 0; i < rValue.size(); i++) {
			rValue.get(i).increase();
		}
		return rValue;
	}
}

class MediumAstroid extends Astroid {
	public MediumAstroid() {
		super(Astroid.MEDIUM_ASTROID, 10);
	}

	public MediumAstroid(Point center) {
		super(center, Astroid.MEDIUM_ASTROID, 10);
	}

	@Override
	public ArrayList<Astroid> hit() {
		this.setAlive(false);
		ArrayList<Astroid> rValue = new ArrayList<>();
		rValue.add(new SmallAstroid(new Point(this.x,this.y)));
		rValue.add(new SmallAstroid(new Point(this.x,this.y)));
		for (int i = 0; i < rValue.size(); i++) {
			rValue.get(i).increase();
		}
		return rValue;
	}
}

class SmallAstroid extends Astroid {
	public SmallAstroid() {
		super(Astroid.SMALL_ASTROID, 5);
	}

	public SmallAstroid(Point center) {
		super(center, Astroid.SMALL_ASTROID, 5);
	}

	
}
