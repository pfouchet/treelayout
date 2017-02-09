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
package org.abego.treelayout.demo;

import javafx.scene.paint.Color;
import org.abego.treelayout.TreeForTreeLayout;
import org.abego.treelayout.fx.IItemToDisplay;
import org.abego.treelayout.fx.ItemToDisplay;
import org.abego.treelayout.util.DefaultTreeForTreeLayout;

/**
 * Creates "Sample" trees, e.g. to be used in demonstrations.
 *
 * @author Udo Borkowski (ub@abego.org)
 */
public class SampleTreeFactory {

	/**
	 * @return a "Sample" tree with {@link ItemToDisplay} items as nodes.
	 */
	public static TreeForTreeLayout<IItemToDisplay> createSampleTree2() {
		ItemToDisplay root = new ItemToDisplay(Color.BLACK);
		ItemToDisplay n1 = new ItemToDisplay(Color.BLUE);
		ItemToDisplay n1_3 = new ItemToDisplay(Color.GREEN);
		ItemToDisplay n1_4 = new ItemToDisplay(Color.YELLOW);
		ItemToDisplay n1_5 = new ItemToDisplay(Color.RED);
		ItemToDisplay n1_5_1 = new ItemToDisplay(Color.GREY);
		ItemToDisplay n1_6 = new ItemToDisplay(Color.GREENYELLOW);
		ItemToDisplay n1_6_1 = new ItemToDisplay(Color.PINK);


		DefaultTreeForTreeLayout<IItemToDisplay> tree = new DefaultTreeForTreeLayout<IItemToDisplay>(
				root);
		tree.addChild(root, n1);
		tree.addChild(n1, n1_3);
		tree.addChild(n1, n1_4);
		tree.addChild(n1, n1_5);
		tree.addChild(n1_5, n1_5_1);
		tree.addChild(n1, n1_6);
		tree.addChild(n1_6, n1_6_1);
		return tree;
	}
}
