/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the "EUPL") version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an "AS IS" basis and 
 * without warranties of any kind concerning the Software, including 
 * without limitation merchantability, fitness for a particular purpose, 
 * absence of defects or errors, accuracy, and non-infringement of 
 * intellectual property rights other than copyright. This disclaimer 
 * of warranty is an essential part of the License and a condition for 
 * the grant of any rights to this Software.
 *   
 * For more  details, see <http://joinup.ec.europa.eu/software/page/eupl>.
 *
 * Contributors:
 *     2013 - Rudy D'hauwe @ Whizu - initial API and implementation
 *******************************************************************************/
package org.whizu.layout;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.whizu.content.Content;
import org.whizu.widget.AbstractTest;

/**
 * @author Rudy D'hauwe
 */
public class LayoutBuilderTest extends AbstractTest {

	/**
	 * Test method for
	 * {@link org.whizu.layout.LayoutBuilder#create(java.lang.String, java.lang.String)}
	 * .
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testCreate() {
		LayoutBuilder<?> parent = new LayoutBuilder();
		LayoutBuilder<?> child1 = new LayoutBuilder();
		parent.add(child1);
		child1.css("myClass");
		LayoutBuilder<?> child2 = new LayoutBuilder();
		parent.add(child2);
		Content markup = parent.create("parent-css", "child-css");
		String expected = "<div id='c0' class='parent-css '><div id='c1' class='myClass child-css '></div><div id='c2' class='child-css '></div></div>";
		assertEquals(expected, markup.render());
	}
}
