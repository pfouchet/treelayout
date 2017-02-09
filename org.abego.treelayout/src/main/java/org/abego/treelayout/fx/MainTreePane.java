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
package org.abego.treelayout.fx;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import org.abego.treelayout.TreeForTreeLayout;
import org.abego.treelayout.TreeLayout;

public class MainTreePane extends Pane {
	private static final int Y_OFFSET = 20;
	private final TreeLayout<IItemToDisplay> treeLayout;


	/**
	 * Specifies the tree to be displayed by passing in a {@link TreeLayout} for
	 * that tree.
	 *
	 * @param treeLayout the {@link TreeLayout} to be displayed
	 */
	public MainTreePane(TreeLayout<IItemToDisplay> treeLayout) {
		this.treeLayout = treeLayout;

		paintEdges(getTree().getRoot());

		// paint the boxes
		for (IItemToDisplay itemToDisplay : treeLayout.getNodeBounds().keySet()) {
			paintBox(itemToDisplay);
		}
	}

	private TreeForTreeLayout<IItemToDisplay> getTree() {
		return treeLayout.getTree();
	}

	private Iterable<IItemToDisplay> getChildren(IItemToDisplay parent) {
		return getTree().getChildren(parent);
	}

	private TreeLayout.Rectangle2DCustom getBoundsOfNode(IItemToDisplay node) {
		return treeLayout.getNodeBounds().get(node);
	}

	private void paintEdges(IItemToDisplay parent) {

		if (!getTree().isLeaf(parent)) {
			TreeLayout.Rectangle2DCustom b1 = getBoundsOfNode(parent);
			double x1 = b1.getCenterX();
			double y1 = b1.getCenterY();
			for (IItemToDisplay child : getChildren(parent)) {
				TreeLayout.Rectangle2DCustom b2 = getBoundsOfNode(child);
				getChildren().add(new Line((int) x1 + getXOffset(), (int) y1 + getYOffset(), (int) b2.getCenterX() + getXOffset(),
						(int) b2.getCenterY() + getYOffset()));

				paintEdges(child);
			}
		}
	}

	private int getYOffset() {
		return Y_OFFSET;
	}

	private int getXOffset() {
		return 20;
	}

	private void paintBox(IItemToDisplay itemToDisplay) {
		TreeLayout.Rectangle2DCustom box = getBoundsOfNode(itemToDisplay);
		getChildren().add(itemToDisplay.getItem());

		itemToDisplay.getItem().setLayoutX(box.getCenterX() + getXOffset());
		itemToDisplay.getItem().setLayoutY(box.getCenterY() + getYOffset());

	}
}