package org.whizu.jquery.mobile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ButtonBuilder1Test extends AbstractJqmTest {

	@Test
	public void testButtonBuilder() {
		Page next = PageBuilder.createWithId("next").build();

		// @formatter:off
		/*
		Jqm.createButton("My button")
			.onClick(next)
			.build()
			.appendTo(page);
		*/
		Button button = ButtonBuilder.createWithTitle("My button")
		    .onClickOpen(next)
		    .build();
		
		page.add(button);
		// @formatter:on

		assertEquals(
				"$p = $(\"<div data-role='page' id='next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer);$('#index').find('div[data-role=content]').append(\"<a data-role='button' id='c1' href='#next'>My button</a>\");",
				theRequest.finish());
	}

	@Test
	public void testJqmBuilder() {
		Page next = PageBuilder.createWithId("next").build();

		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("My button")
			.onClickOpen(next)
			.build();
		page.add(button);
		// @formatter:on

		assertEquals(
				"$p = $(\"<div data-role='page' id='next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer);$('#index').find('div[data-role=content]').append(\"<a data-role='button' id='c1' href='#next'>My button</a>\");",
				theRequest.finish());
	}
	
	
}
