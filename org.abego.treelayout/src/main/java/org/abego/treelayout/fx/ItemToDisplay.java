package org.abego.treelayout.fx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ItemToDisplay implements IItemToDisplay {

	private static final int RADIUS = 25;

	private final Circle circle = new Circle(RADIUS);
	private final Pane pane;
	public ItemToDisplay(Color color) {
		circle.setFill(color);
		circle.setStroke(color);
		pane = new Pane(circle);
	}

	public Pane getItem() {
		return pane;
	}

	public double getWidth() {
		return pane.getWidth();
	}

	public double getHeight() {
		return pane.getHeight();
	}
}