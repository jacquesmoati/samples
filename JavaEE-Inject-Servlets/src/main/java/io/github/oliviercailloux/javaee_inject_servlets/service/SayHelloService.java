package io.github.oliviercailloux.javaee_inject_servlets.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SayHelloService {
	public String getHello() {
		return "Hello, world.";
	}
}
