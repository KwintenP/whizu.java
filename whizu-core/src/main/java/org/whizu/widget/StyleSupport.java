package org.whizu.widget;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.whizu.dom.Decorator;
import org.whizu.dom.Element;
import org.whizu.dom.Stylable;

public class StyleSupport implements Stylable, Decorator {

	private List<String> cssClassList_ = new ArrayList<String>();
	
	private StringBuffer style_ = new StringBuffer();
	
	@Override
	public void css(String cssClass) {
		cssClassList_.add(cssClass);
	}

	@Override
	public void decorate(Element element) {
		element.css(cssClassList_).style(style_.toString());
	}

	@Override
	public void style(String style) {
		style_.append(style);
		if (!StringUtils.endsWith(style, ";")) {
			style_.append(';');
		}
	}
}
