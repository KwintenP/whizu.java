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

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.whizu.annotation.AnnotationUtils;
import org.whizu.annotation.Page;
import org.whizu.annotation.Title;
import org.whizu.jquery.EventHandler;
import org.whizu.jquery.Input;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Session;
import org.whizu.resource.ClassPathResource;
import org.whizu.resource.Resource;
import org.whizu.ui.Application;
import org.whizu.ui.WhizuUI;

/**
 * @author Rudy D'hauwe
 */
public class WhizuServlet extends HttpServlet {

	private Log log = LogFactory.getLog(WhizuServlet.class);

	private static final String WHIZU_SESSION = "whizu-session";

	private Configuration config = new Configuration();

	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("Starting the WhizuServlet");
		RequestContext.setInstance(new RequestContextImpl());
		AnnotationUtils.scan(Page.class, this.config);
		log.info("WhizuServlet started");
	}

	private Session assureUserSession(HttpSession session) {
		Session userSession = (Session) session.getAttribute(WHIZU_SESSION);
		if (userSession == null) {
			userSession = new SessionImpl();
			session.setAttribute(WHIZU_SESSION, userSession);
		}
		RequestImpl.get().setSession(userSession);
		return userSession;
	}

	// replace by a class StylesheetResource?
	private String handleCss(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uri = request.getRequestURI();
		String servletPath = request.getServletPath();
		// String contextPath = request.getContextPath();
		// debug(uri);
		// debug(servletPath);
		// debug(contextPath);
		String path = uri.substring(servletPath.length());
		Resource resource = new ClassPathResource(path);
		return resource.getString();
	}

	// serve a new page request to an application,
	// replace this method by a class PageResource?
	private String servePageRequest(HttpServletRequest request) {
		// getPageResource(uri).stream(response.getWriter());
		String uri = request.getRequestURI();
		final PageFactory factory = config.getFactory(uri);
		if (factory != null) {
			final Application app = factory.createInstance();
			Class<? extends Application> clazz = app.getClass();
			Resource resource = new ClassPathResource(factory.getTemplate());
			try {
				String content = resource.getString();
				final String id = clazz.getName();
				content = content.replace("${id}", id);
				RequestImpl.get().getSession().addClickListener(new EventHandler() {

					@Override
					public String getId() {
						return id;
					}

					@Override
					public void handleEvent() {
						app.init(new WhizuUI());
					}
				});

				String ann = factory.getStylesheet();
				if (ann != null) {
					String link = "<link rel='stylesheet' type='text/css' href='" + ann + "' />";
					content = content.replace("${css}", link);
				} else {
					content = content.replace("${css}", "");
				}

				String title = factory.getTitle();
				if (title != null) {
					content = content.replace("${title}", title);
				} else {
					content = content.replace("${title}", Title.DEFAULT_TITLE);
				}

				return content;
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		} else {
			throw new IllegalArgumentException("No @Page has been defined for " + uri);
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		long start = System.currentTimeMillis();
		String content = "--";
		try {
			Session session = startRequest(request);
			String id = request.getParameter("id");
			if (id == null) {
				if (request.getRequestURI().endsWith(".css")) {
					content = handleCss(request, response);
				} else {
					// even better:
					// getResource(uri).stream(response.getWriter());
					content = this.servePageRequest(request);
					// Resource resource = this.servePageRequest(request);
				}
			} else {
				session.handleEvent(id);
				content = RequestImpl.get().finish();
			}
		} finally {
			long end = System.currentTimeMillis();
			if (log.isDebugEnabled()) {
				log.debug("Server side completed in " + (end - start) + "ms");
				log.debug("Sending script " + content);
			}
			response.getWriter().print(content);
			response.getWriter().close();
		}
	}

	private Session startRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Session userSession = assureUserSession(session);
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<String> keys = parameterMap.keySet();
		for (String key : keys) {
			Input editable = userSession.getInput(key);
			if (editable != null) {
				editable.parseString(parameterMap.get(key)[0]);
			}
		}
		return userSession;
	}
}
