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
package org.whizu.server;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.jquery.EventHandler;
import org.whizu.jquery.Input;
import org.whizu.jquery.Session;

/**
 * @author Rudy D'hauwe
 */
class SessionImpl implements Session {

	private Logger log = LoggerFactory.getLogger(SessionImpl.class);

	private static int sessionCount = 0;

	private int componentCount = 0;

	private Map<String, Object> attrMap = new HashMap<String, Object>();

	private Map<String, EventHandler> eventHandlerMap = new HashMap<String, EventHandler>();

	private Map<String, Input> inputMap = new HashMap<String, Input>();

	SessionImpl() {
		sessionCount++;
	}

	@Override
	public void addClickListener(EventHandler listener) {
		eventHandlerMap.put(listener.id(), listener);
		log.debug("Session.EventHandlers #{}", eventHandlerMap.size());
	}

	@Override
	public void addInput(Input input) {
		inputMap.put(input.id(), input);
	}

	@Override
	public Object attribute(String name) {
		return attrMap.get(name);
	}

	@Override
	public EventHandler getEventHandler(String id) {
		return eventHandlerMap.get(id);
	}

	@Override
	public Input getInput(String id) {
		return inputMap.get(id);
	}

	@Override
	public int getSessionCount() {
		return sessionCount;
	}

	@Override
	public boolean handleEvent(String id) {
		EventHandler listener = getEventHandler(id);
		if (listener != null) {
			listener.handleEvent();
			return true;
		}
		
		return false;
	}

	@Override
	public String next() {
		return "c" + componentCount++;
	}

	@Override
	public void attribute(String name, Object value) {
		attrMap.put(name, value);
	}
}
