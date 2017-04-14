package de.heidelberg.pvs.diego.collections_online_adapter.monitors.sets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import de.heidelberg.pvs.diego.collections_online_adapter.optimizers.sets.SetAllocationOptimizer;

public class HashSetFullMonitor<E> extends HashSet<E> {

	private static final long serialVersionUID = 20170101L;
	
	SetAllocationOptimizer context;

	private int containsOp;
	private int iterateOp;
	private int index ;
	
	public HashSetFullMonitor(int initialCapacity, SetAllocationOptimizer context, int index) {
		super(initialCapacity);
		this.context = context;
		this.index = index;
	}
	
	public HashSetFullMonitor(Collection<? extends E> set, SetAllocationOptimizer context, int index) {
		super(set);
		this.context = context;
		this.index = index;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.context.updateOperationsAndSize(index, containsOp, iterateOp, size());
	}
	
	public boolean contains(Object o) {
		this.containsOp++;
		return super.contains(o);
	}

	public Iterator<E> iterator() {
		this.iterateOp++;
		return super.iterator();
	}

	public boolean containsAll(Collection<?> c) {
		this.containsOp++;
		return super.containsAll(c);
	}

}
