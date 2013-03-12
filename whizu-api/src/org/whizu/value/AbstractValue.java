/**
 * Copyright (c) 2012-2013 Whiz'UI @ www.whizui.com.
 * Licensed under the EUPL V.1.1.
 *  
 * This Software is provided to You under the terms of the European 
 * Union Public License (the �EUPL�) version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software). 
 *
 * This Software is provided under the License on an �AS IS� basis and 
 * without warranties of any kind concerning the Software, including 
 * without limitation merchantability, fitness for a particular purpose, 
 * absence of defects or errors, accuracy, and non-infringement of 
 * intellectual property rights other than copyright. 
 * 
 * This disclaimer of warranty is an essential part of the License and 
 * a condition for the grant of any rights to this Software. For more 
 * details, see <http://joinup.ec.europa.eu/software/page/eupl>.
 */
package org.whizu.value;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author Rudy D'hauwe
 */
public abstract class AbstractValue<T> implements Value<T> {

	// @Transient
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	private boolean isReadOnly;

	// @Transient
	public final String name;

	private T value;

	public AbstractValue(String name) {
		this.name = name;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	protected void firePropertyChange(String propertyName, T oldValue, T newValue) {
		System.out.println("fire change " + oldValue + " wordt " + newValue);
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	@Override
	public String getName() {
		return name;
	}

	public T getValue() {
		return this.value;
	}

	@Override
	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	@Override
	public void setReadOnly(boolean newStatus) {
		this.isReadOnly = newStatus;
	}

	public void setValue(T value) {
		firePropertyChange("value", this.value, this.value = value);
	}
}
