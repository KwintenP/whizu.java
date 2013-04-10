/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the �EUPL�) version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an �AS IS� basis and 
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
package org.whizu.jquery.mobile;

import org.whizu.html.Html;
import org.whizu.html.NonVoid;
import org.whizu.runtime.AbstractComponent;

/**
 * The jQuery Mobile framework provides a simple way to build CSS-based columns
 * that can also be responsive.
 * 
 * @author Rudy D'hauwe
 */
public class Grid extends AbstractComponent {

	public enum Type {
		// @formatter:off
		TWO_COLUMNS("ui-grid-a"),
		THREE_COLUMNS("ui-grid-b"),
		FOUR_COLUMNS("ui-grid-c"),
		FIVE_COLUMNS("ui-grid-d"); 
		// @formatter:on

		protected String value;

		Type(String value) {
			this.value = value;
		}
	}

	private static final Type DEFAULT_GRID_TYPE = Type.TWO_COLUMNS;;

	private Type type = DEFAULT_GRID_TYPE;

	public Grid() {
		this(DEFAULT_GRID_TYPE);
	}

	public Grid(Type type) {
		this.type = type;
	}

	@Override
	public Html create() {
		NonVoid grid = div(this).css(type.value);
		return grid;
	}

	@Override
	public Grid css(String clazz) {
		setStyleName(clazz);
		return this;
	}
}
