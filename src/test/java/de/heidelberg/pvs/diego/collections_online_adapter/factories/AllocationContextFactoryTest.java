package de.heidelberg.pvs.diego.collections_online_adapter.factories;

import static org.junit.Assert.assertNotNull;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import de.heidelberg.pvs.diego.collections_online_adapter.context.CollectionTypeEnum;
import de.heidelberg.pvs.diego.collections_online_adapter.context.MapAllocationContext;
import de.heidelberg.pvs.diego.collections_online_adapter.context.SetAllocationContext;
import de.heidelberg.pvs.diego.collections_online_adapter.factories.AllocationContextFactory.AllocationContextBuilder;
import jlibs.core.lang.RuntimeUtil;

public class AllocationContextFactoryTest {
	
	@Test
	public void sanitySetTest() throws Exception {
		
		AllocationContextBuilder builder = new AllocationContextBuilder(CollectionTypeEnum.HASH, "SetSanityTest");
		builder.withLog("data/data-test");
		SetAllocationContext context = AllocationContextFactory.buildSetContext(builder);
		
		for (int i = 0; i < 200; i++) {
			Set<Object> createSet = context.createSet();
			assertNotNull(createSet);
			createSet.add(i);
		}

		RuntimeUtil.gc();
		RuntimeUtil.gc();
		Thread.sleep(1000);
		
	}
	
	@Test
	public void sanityMapTest() throws Exception {
		
		AllocationContextBuilder builder = new AllocationContextBuilder(CollectionTypeEnum.HASH, "MapSanityTest");
		builder.withLog("data/data-test");
		MapAllocationContext context = AllocationContextFactory.buildMapContext(builder);
		
		for (int i = 0; i < 200; i++) {
			Map<Integer, Integer> createMap = context.createMap();
			assertNotNull(createMap);
			createMap.put(i, i);
		}

		RuntimeUtil.gc();
		RuntimeUtil.gc();
		Thread.sleep(1000);
		
	}

}