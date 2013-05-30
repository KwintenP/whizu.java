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
package org.whizu.annotation.processing;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.lang.model.element.Element;
import javax.lang.model.element.Name;

import org.pegdown.PegDownProcessor;

/**
 * @author Rudy D'hauwe
 */
public class Support {

	private static Map<String, String> map = new HashMap<String, String>();

	static {
		readHtml("/org/whizu/annotation/processing/html.properties");
		readMarkdown("/org/whizu/annotation/processing/markdown.properties");
	}

	public static void add(Class<?> clazz, String fieldName, String value) {
		map.put(clazz.getName() + "." + fieldName, value);
	}

	private static void readMarkdown(String fileName) {
		InputStream in = null;
		try {
			in = Support.class.getClass().getResourceAsStream(fileName);
			if (in != null) {
				PegDownProcessor processor = new PegDownProcessor();
				Properties props = new Properties();
				props.load(in);
				Enumeration<Object> keys = props.keys();
				while (keys.hasMoreElements()) {
					String key = (String) keys.nextElement();
					String markdown = (String) props.get(key);
					String html = processor.markdownToHtml(markdown.replace("\n ", "\n")).replace('"', '\'');
					add(key, html);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * @param string
	 */
	private static void readHtml(String fileName) {
		InputStream in = null;
		try {
			in = Support.class.getClass().getResourceAsStream(fileName);
			if (in != null) {
				Properties props = new Properties();
				props.load(in);
				Enumeration<Object> keys = props.keys();
				while (keys.hasMoreElements()) {
					String key = (String) keys.nextElement();
					String value = (String) props.get(key);
					String html = value.replace("\n", "").replace('"', '\'');
					add(key, html);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private static void add(String key, String value) {
		map.put(key, value);
	}

	public static String get(Class<?> clazz, String fieldName) {
		String result = map.get(clazz.getName() + "." + fieldName);
		return result;
	}

	public static Name getClassOf(Element field) {
		return field.getEnclosingElement().getSimpleName();
	}

	public static String getKey(Class<?> clazz, String fieldName) {
		String varName = clazz.getName() + "." + fieldName;
		varName = varName.replace('.', '_');
		return varName;
	}

	public static String getValue(Class<?> clazz, String fieldName) {
		String value = map.get(getKey(clazz, fieldName));
		return (value == null) ? "NULL" : value;
	}

	/**
	 * 
	 */
	public static void update() {
		readHtml("/org/whizu/annotation/processing/html.properties");
		readMarkdown("/org/whizu/annotation/processing/markdown.properties");
	}
}