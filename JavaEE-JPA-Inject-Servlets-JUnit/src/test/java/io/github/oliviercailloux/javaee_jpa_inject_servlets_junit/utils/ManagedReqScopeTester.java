package io.github.oliviercailloux.javaee_jpa_inject_servlets_junit.utils;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.context.activator.ActivateRequestContext;

@ApplicationScoped
public class ManagedReqScopeTester {
	@ActivateRequestContext
	public String getHi(ReqScoped reqScoped) {
		return reqScoped.getHi();
	}
}
