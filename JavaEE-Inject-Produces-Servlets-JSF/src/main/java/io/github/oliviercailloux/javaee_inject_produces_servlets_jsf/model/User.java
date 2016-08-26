package io.github.oliviercailloux.javaee_inject_produces_servlets_jsf.model;

import java.io.Serializable;

public class User implements Serializable {

	private String name;

	public User() {
		/** Default constructor for instantiation by the container. */
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
