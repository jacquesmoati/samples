package io.github.oliviercailloux.javaee_jpa_inject_servlets_junit.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OuterAppScoped {

	@Inject
	private InnerAppScoped inner;

	public String getHello() {
		return inner.getHello();
	}
}
