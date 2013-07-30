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
import org.whizu.dom.Element;
import org.whizu.html.Html;
import org.whizu.widget.Widget;

/**
 * @author Rudy D'hauwe
 */
public class Footer extends Widget {

	private Theme theme_ = Theme.E;

	private Element title_;

	public Footer() {
	}

	/**
	 * Creates a header with a title wrapped in an H4 heading element.
	 */
	public Footer(String title) {
		title_ = Html.h4(title);
	}

	@Override
	public Content build() {
		//jQuery(this).closest(":jqmData(role='page')").trigger("pagecreate");
		return Html.div(this).decorate(DataRole.FOOTER, theme_).add(title_);
	}
}
