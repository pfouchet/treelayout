package org.abego.treelayout.demo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import static javafx.scene.paint.Color.BLACK;

public class ItemToDisplay implements IItemToDisplay {

	private static final int RADIUS = 25;

	private final Circle circle = new Circle(RADIUS);

	public ItemToDisplay(Color color) {
		circle.setFill(color);
		circle.setStroke(BLACK);
	}

	public Shape getItem() {
		return circle;
	}

	public double getWidth() {
		return circle.getRadius();
	}

	public double getHeight() {
		return circle.getRadius();
	}
}