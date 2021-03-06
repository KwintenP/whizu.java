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

import javax.servlet.http.HttpServletRequest;

import org.whizu.jquery.AbstractRequest;
import org.whizu.jquery.Request;

/**
 * @author Rudy D'hauwe
 */
final class RequestImpl extends AbstractRequest implements Request {

	private static ThreadLocal<RequestImpl> request = new ThreadLocal<RequestImpl>() {

		@Override
		protected RequestImpl initialValue() {
			return new RequestImpl();
		}
	};

	static RequestImpl get() {
		return request.get();
	}

	private HttpServletRequest request_;
	
	private RequestImpl() {
	}

    @Override
	public String finish() {
		try {
			return super.finish();
		} finally {
			request.remove();
			request_ = null;
		}
	}

	@Override
	public String getParameter(String name) {
		return request_.getParameter(name);
	}

	public void wrap(HttpServletRequest request) {
		request_ = request;
	}
}
