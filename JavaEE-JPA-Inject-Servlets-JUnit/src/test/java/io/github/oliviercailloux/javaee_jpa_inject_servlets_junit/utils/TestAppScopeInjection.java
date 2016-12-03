package io.github.oliviercailloux.javaee_jpa_inject_servlets_junit.utils;

import static org.junit.Assert.assertEquals;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.inject.WeldInstance;
import org.junit.Test;

public class TestAppScopeInjection {

	@Test
	public void testAppScopeInjection() {
		final Weld weld = new Weld();
		try (WeldContainer container = weld.initialize()) {
			final WeldInstance<OuterAppScoped> select = container.select(OuterAppScoped.class);
			final OuterAppScoped outer = select.get();
			assertEquals("Hello.", outer.getHello());
		}
	}

}
