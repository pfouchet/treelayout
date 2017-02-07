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
import org.abego.treelayout.demo.TextInBox;

import java.awt.*;

/**
 * A JComponent displaying a tree of TextInBoxes, given by a {@link TreeLayout}.
 *
 * @author Udo Borkowski (ub@abego.org)
 */
public class TextInBoxTreePane extends Pane {

	private static final int Y_OFFSET = 20;

	public static final int RADIUS = 25;
	public final Canvas canvas;
	private final TreeLayout<TextInBox> treeLayout;

	// -------------------------------------------------------------------
	// painting

	/**
	 * Specifies the tree to be displayed by passing in a {@link TreeLayout} for
	 * that tree.
	 *
	 * @param treeLayout the {@link TreeLayout} to be displayed
	 */
	public TextInBoxTreePane(TreeLayout<TextInBox> treeLayout) {
		this.treeLayout = treeLayout;

		Dimension size = treeLayout.getBounds().getBounds().getSize();

		canvas = new Canvas(size.width, size.height);
		GraphicsContext gd = canvas.getGraphicsContext2D();
		getChildren().add(canvas);
		paintEdges(gd, getTree().getRoot());

		// paint the boxes
		for (TextInBox textInBox : treeLayout.getNodeBounds().keySet()) {
			paintBox(textInBox);
		}
	}

	private TreeForTreeLayout<TextInBox> getTree() {
		return treeLayout.getTree();
	}

	private Iterable<TextInBox> getChildren(TextInBox parent) {
		return getTree().getChildren(parent);
	}

	private TreeLayout.Rectangle2DCustom getBoundsOfNode(TextInBox node) {
		return treeLayout.getNodeBounds().get(node);
	}

	private void paintEdges(GraphicsContext g, TextInBox parent) {
		g.setLineWidth(2);
		g.setFill(javafx.scene.paint.Color.GREEN);
		g.setStroke(javafx.scene.paint.Color.BLUE);
		if (!getTree().isLeaf(parent)) {
			TreeLayout.Rectangle2DCustom b1 = getBoundsOfNode(parent);
			double x1 = b1.getCenterX();
			double y1 = b1.getCenterY();
			for (TextInBox child : getChildren(parent)) {
				TreeLayout.Rectangle2DCustom b2 = getBoundsOfNode(child);
				g.strokeLine((int) x1, (int) y1 + Y_OFFSET, (int) b2.getCenterX(),
						(int) b2.getCenterY() + Y_OFFSET);

				paintEdges(g, child);
			}
		}
	}

	private void paintBox(TextInBox textInBox) {

		Circle circle = new Circle(RADIUS);
		TreeLayout.Rectangle2DCustom box = getBoundsOfNode(textInBox);
		circle.setCenterX(box.getX() + box.getWidth()/ 2);
		circle.setCenterY(box.getY() + box.getHeight() / 2 + Y_OFFSET);
		getChildren().add(circle);
		circle.toFront();
	}
}