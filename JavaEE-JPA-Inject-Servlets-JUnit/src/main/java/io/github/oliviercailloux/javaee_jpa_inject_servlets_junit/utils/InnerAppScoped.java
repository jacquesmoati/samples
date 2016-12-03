package io.github.oliviercailloux.javaee_jpa_inject_servlets_junit.utils;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InnerAppScoped {
	public String getHello() {
		return "Hello.";
	}
}
