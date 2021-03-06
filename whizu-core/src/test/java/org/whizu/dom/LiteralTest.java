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
package org.whizu.dom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.whizu.content.Literal;

/**
 * @author Rudy D'hauwe
 */
public class LiteralTest {

	/**
	 * Test method for {@link org.whizu.content.Literal#render()}.
	 */
	@Test
	public void testRender() {
		Literal obj = new Literal("myText");
		assertEquals("myText", obj.render());
	}

	/**
	 * Test method for {@link org.whizu.content.Literal#Literal(java.lang.String)}.
	 */
	@Test
	public void testLiteral() {
		Literal obj = new Literal("myText");
		assertEquals("myText", obj.text());
	}

	/**
	 * Test method for {@link org.whizu.content.Literal#text()}.
	 */
	@Test
	public void testGetText() {
		Literal obj = new Literal("myText");
		assertEquals("myText", obj.text());
	}
}
