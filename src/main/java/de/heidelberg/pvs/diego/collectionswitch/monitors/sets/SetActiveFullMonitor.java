package de.heidelberg.pvs.diego.collectionswitch.monitors.sets;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetActiveFullMonitor<E> implements Set<E> {

	private Set<E> set;
	
	private SetMetrics state;
	
	public SetActiveFullMonitor(Set<E> set, SetMetrics state) {
		super();
		this.set = set;
		this.state = state;
		this.state.updateSize(set.size()); // first record
	}

	/**
	 * MONITORED OPERATIONS
	 */
	
	public boolean contains(Object o) {
		state.updateContainsOp(1);
		return set.contains(o);
	}

	public Iterator<E> iterator() {
		state.updateIteration(1);
		return set.iterator();
	}

	public boolean add(E e) {
		state.updateSize(1);
		return set.add(e);
	}

	public boolean containsAll(Collection<?> c) {
		state.updateContainsOp(c.size());
		return set.containsAll(c);
	}

	public boolean addAll(Collection<? extends E> c) {
		state.updateSize(c.size());
		return set.addAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		state.updateContainsOp(c.size());
		return set.retainAll(c);
	}
	
	public boolean remove(Object o) {
		state.updateSize(-1);
		return set.remove(o);
	}
	
	public boolean removeAll(Collection<?> c) {
		state.updateSize(-c.size());
		return set.removeAll(c);
	}
	
	public void clear() {
		state.updateSize(-size());
		set.clear();
	}

	/**
	 * NON-MONITORED OPERATIONS
	 */
	
	
	public int size() {
		return set.size();
	}

	public boolean isEmpty() {
		return set.isEmpty();
	}

	
	public Object[] toArray() {
		return set.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return set.toArray(a);
	}

	public boolean equals(Object o) {
		return set.equals(o);
	}

	public int hashCode() {
		return set.hashCode();
	}
	
}
