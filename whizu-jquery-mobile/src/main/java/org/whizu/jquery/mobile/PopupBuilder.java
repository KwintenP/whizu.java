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
package org.whizu.jquery.mobile;

import org.whizu.dom.Content;
import org.whizu.html.Html;

/**
 * @author Rudy D'hauwe
 */
public class PopupBuilder implements Builder<Popup> {

	public static PopupBuilder createWithId(String id) {
		return new PopupBuilder(id);
	}

	private Popup build_;

	public PopupBuilder(String id) {
		build_ = new Popup(id);
	}

	public PopupBuilder append(Content content) {
		build_.add(content);
		return this;
	}

	@Override
	public Popup build() {
		Jqm.addPopup(build_);
		return build_;
	}

	public PopupBuilder p(String text) {
		build_.add(Html.p(text));
		return this;
	}

	public PopupBuilder center() {
		build_.positionToWindow();
		return this;
	}

	public PopupBuilder padding(String px) {
		build_.style("padding:"+px);
		return this;
	}

	public PopupBuilder h3(String title) {
		build_.add(Html.h3(title));
		return this;
	}
	
	public PopupBuilder theme(Theme theme) {
		build_.theme(theme);
		return this;
	}
}