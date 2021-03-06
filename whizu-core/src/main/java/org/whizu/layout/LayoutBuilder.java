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

import org.whizu.content.Component;
import org.whizu.content.Content;
import org.whizu.content.Element;
import org.whizu.content.Foreach;
import org.whizu.html.Html;
import org.whizu.widget.Container;
import org.whizu.widget.Widget;

/**
 * @author Rudy D'hauwe
 */
class LayoutBuilder<T extends LayoutBuilder<T>> extends Container implements Layout {

	@Override
	public T add(Content impl) {
		return getThis(super.add(impl));
	}

	protected Content create(String css, final String itemClass) {
		return Html.div(this).decorate(this).css(css).add(new Foreach<Content>(componentList) {

			/*
			 * @Override public Content compile(Component item) { return
			 * item.css(itemClass); }
			 */
 
			@Override
			public Content build(Content item) {
				if (item instanceof Element) {
					return ((Element) item).css(itemClass);
				}
				if (item instanceof Widget) {
					return ((Widget) item).css(itemClass);
				}
				//TODO do nothing or throw exception/warning ?
				return item;
			}
		});
	}

	@Override
	public T css(String clazz) {
		return getThis(super.css(clazz));
	}

	@SuppressWarnings("unchecked")
	private T getThis(Component component) {
		return (T) component;
	}

	private T getThis() {
		return getThis(this);
	}

	/*
	 * public T add(Value value) { Component view = compile(value); if
	 * (isRendered()) { jQuery(this).append(view); } else { add(view); } return
	 * getThis(); }
	 */
}
