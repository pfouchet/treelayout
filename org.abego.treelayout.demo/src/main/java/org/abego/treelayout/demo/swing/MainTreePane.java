/*
 * [The "BSD license"]
 * Copyright (c) 2011, abego Software GmbH, Germany (http://www.abego.org)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of the abego Software GmbH nor the names of its 
 *    contributors may be used to endorse or promote products derived from this 
 *    software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.abego.treelayout.demo.swing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import org.abego.treelayout.TreeForTreeLayout;
import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.demo.ItemToDisplay;

public class MainTreePane extends Pane {

	public static final int RADIUS = 25;
	private static final int Y_OFFSET = 20;
	public final Canvas canvas;
	private final TreeLayout<ItemToDisplay> treeLayout;

	/**
	 * Specifies the tree to be displayed by passing in a {@link TreeLayout} for
	 * that tree.
	 *
	 * @param treeLayout the {@link TreeLayout} to be displayed
	 */
	public MainTreePane(TreeLayout<ItemToDisplay> treeLayout) {
		this.treeLayout = treeLayout;

		canvas = new Canvas(treeLayout.getBounds().getWidth(), treeLayout.getBounds().getHeight());
		GraphicsContext gd = canvas.getGraphicsContext2D();
		getChildren().add(canvas);
		paintEdges(gd, getTree().getRoot());

		// paint the boxes
		for (ItemToDisplay itemToDisplay : treeLayout.getNodeBounds().keySet()) {
			paintBox(itemToDisplay);
		}
	}

	private TreeForTreeLayout<ItemToDisplay> getTree() {
		return treeLayout.getTree();
	}

	private Iterable<ItemToDisplay> getChildren(ItemToDisplay parent) {
		return getTree().getChildren(parent);
	}

	private TreeLayout.Rectangle2DCustom getBoundsOfNode(ItemToDisplay node) {
		return treeLayout.getNodeBounds().get(node);
	}

	private void paintEdges(GraphicsContext g, ItemToDisplay parent) {
		g.setLineWidth(2);
		g.setFill(javafx.scene.paint.Color.GREEN);
		g.setStroke(javafx.scene.paint.Color.BLUE);
		if (!getTree().isLeaf(parent)) {
			TreeLayout.Rectangle2DCustom b1 = getBoundsOfNode(parent);
			double x1 = b1.getCenterX();
			double y1 = b1.getCenterY();
			for (ItemToDisplay child : getChildren(parent)) {
				TreeLayout.Rectangle2DCustom b2 = getBoundsOfNode(child);
				g.strokeLine((int) x1, (int) y1 + Y_OFFSET, (int) b2.getCenterX(),
						(int) b2.getCenterY() + Y_OFFSET);

				paintEdges(g, child);
			}
		}
	}

	private void paintBox(ItemToDisplay itemToDisplay) {

		Circle circle = new Circle(RADIUS);
		TreeLayout.Rectangle2DCustom box = getBoundsOfNode(itemToDisplay);
		circle.setCenterX(box.getCenterX());
		circle.setCenterY(box.getCenterY() + Y_OFFSET);
		getChildren().add(circle);
		circle.toFront();
	}
}