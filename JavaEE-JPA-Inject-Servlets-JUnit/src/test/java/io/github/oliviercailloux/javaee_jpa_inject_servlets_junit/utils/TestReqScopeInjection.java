package io.github.oliviercailloux.javaee_jpa_inject_servlets_junit.utils;

import static org.junit.Assert.assertEquals;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.inject.WeldInstance;
import org.junit.Test;

public class TestReqScopeInjection {

	@Test
	public void testReqScopeInjection() {
		final Weld weld = new Weld();
		try (WeldContainer container = weld.initialize()) {
			final WeldInstance<ReqScoped> instanceReqScoped = container.select(ReqScoped.class);
			final ReqScoped req = instanceReqScoped.get();
			final WeldInstance<ManagedReqScopeTester> instanceTester = container.select(ManagedReqScopeTester.class);
			final ManagedReqScopeTester tester = instanceTester.get();
			assertEquals("Hi.", tester.getHi(req));
		}
	}

}
