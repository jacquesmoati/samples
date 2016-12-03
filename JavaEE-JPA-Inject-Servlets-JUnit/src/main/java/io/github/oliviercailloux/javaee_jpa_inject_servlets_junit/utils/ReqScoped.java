package io.github.oliviercailloux.javaee_jpa_inject_servlets_junit.utils;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ReqScoped {
	public String getHi() {
		return "Hi.";
	}
}
