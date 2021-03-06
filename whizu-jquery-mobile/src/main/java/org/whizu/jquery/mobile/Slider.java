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

import org.whizu.content.Content;
import org.whizu.content.Element;
import org.whizu.html.Html;
import org.whizu.widget.Widget;

/**
 * @author Rudy D'hauwe
 */
public class Slider extends Widget {

	private String max;

	private String min;
	
	private DataMini mini;

	private String step;

	private Theme theme;

	private Theme track;

	public Slider(int min, int max) {
		this.min = "" + min;
		this.max = "" + max;
	}

	public Slider(int min, int max, Theme theme) {
		this(min, max);
		this.theme = theme;
	}

	@Override
	public Content build() {
		// @formatter:off
		Element field = Html.input(this)
				.attr("type", "range")
				.attr("name", "label")
				.attr("value", min)
				.attr("min", min)
				.attr("max", max)
				.attr("step", step)
//				.attr("data-track-theme", track.value())
				.decorate(theme)
				.decorate(mini);
		Element label = Html.tag("label")
				.attr("for", field.id())
				.add("label");
		// @formatter:on
		return field.after(label);
	}

	public String getMax() {
		return max;
	}

	public String getMin() {
		return min;
	}

	public DataMini getMini() {
		return mini;
	}

	public String getStep() {
		return step;
	}
	
	public Theme getTheme() {
		return theme;
	}

	public Theme getTrack() {
		return track;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public void setMini(DataMini mini) {
		this.mini = mini;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public void setTrack(Theme track) {
		this.track = track;
	}
}
