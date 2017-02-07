package org.abego.treelayout.demo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import static javafx.scene.paint.Color.BLACK;

public class ItemToDisplay<T> implements IItemToDisplay {

	public static final int RADIUS = 25;

	public final Color text;
	public final int height;
	public final int width;

	private final Circle circle = new Circle(RADIUS);

	public ItemToDisplay(Color color, int width, int height) {
		this.text = color;
		this.width = width;
		this.height = height;
		circle.setFill(color);
		circle.setStroke(BLACK);
	}

	public Shape getItem() {
		return circle;
	}

	public double getWidth() {
		return width;
	}

	public double getLength() {
		return height;
	}
}