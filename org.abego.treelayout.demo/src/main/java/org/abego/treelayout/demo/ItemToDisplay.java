package org.abego.treelayout.demo;

public class ItemToDisplay<T> implements IItemToDisplay {

	public final T text;
	public final int height;
	public final int width;

	public ItemToDisplay(T text, int width, int height) {
		this.text = text;
		this.width = width;
		this.height = height;
	}

	public T getItem() {
		return text;
	}

	public double getWidth() {
		return width;
	}

	public double getLength() {
		return height;
	}
}