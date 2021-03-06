package org.whizu.jquery.mobile.list;

import java.beans.PropertyChangeListener;

import org.whizu.content.Content;
import org.whizu.jquery.ClickListener;
import org.whizu.util.ListChangeListener;

/**
 * @author Rudy D'hauwe
 */
public interface ListControl<T> extends BeanControl<T>, Iterable<T> {

	public ClickListener addEvent();

	public void addPropertyChangeListener(PropertyChangeListener listener);
	
	public void addChangeListener(ListChangeListener<T> listener);

	public Content build(T item);

	public T get(int index);

	public void handleAddEvent();

	public void handleClickEvent(T element);

	public String id(T item);

	public boolean isClickable();

	public boolean isClickable(T element);

	public int size();
}
