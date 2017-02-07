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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.abego.treelayout.TreeForTreeLayout;
import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.demo.SampleTreeFactory;
import org.abego.treelayout.demo.TextInBox;
import org.abego.treelayout.demo.TextInBoxNodeExtentProvider;
import org.abego.treelayout.util.DefaultConfiguration;

/**
 * Demonstrates how to use the {@link TreeLayout} to render a tree in a Swing
 * application.
 * <p>
 * Intentionally the sample code is kept simple. I.e. it does not include stuff
 * like anti-aliasing and other stuff one would add to make the output look
 * nice.
 * <p>
 * Screenshot:
 * <p>
 * <img src="doc-files/swingdemo.png" alt="A tree rendered using Swing">
 *
 * @author Udo Borkowski (ub@abego.org)
 */
public class SwingDemo extends Application {

	public void start(Stage primaryStage) throws Exception {
		TreeForTreeLayout<TextInBox> tree = SampleTreeFactory.createSampleTree2();

		// setup the tree layout configuration
		double gapBetweenLevels = 150;
		double gapBetweenNodes = 50;
		DefaultConfiguration<TextInBox> configuration = new DefaultConfiguration<TextInBox>(
				gapBetweenLevels, gapBetweenNodes);

		// create the NodeExtentProvider for TextInBox nodes
		TextInBoxNodeExtentProvider nodeExtentProvider = new TextInBoxNodeExtentProvider();

		// create the layout
		TreeLayout<TextInBox> treeLayout = new TreeLayout<TextInBox>(tree,
				nodeExtentProvider, configuration);

		// Create a panel that draws the nodes and edges and show the panel
		TextInBoxTreePane panel = new TextInBoxTreePane(treeLayout);

		primaryStage.setMinHeight(800);
		primaryStage.setMinWidth(800);

		primaryStage.setScene(new Scene(panel));

		primaryStage.centerOnScreen();
		primaryStage.show();
	}
}
