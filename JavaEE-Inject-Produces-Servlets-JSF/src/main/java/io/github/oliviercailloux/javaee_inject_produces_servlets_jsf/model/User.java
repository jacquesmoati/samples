package io.github.oliviercailloux.javaee_inject_produces_servlets_jsf.model;

import java.io.Serializable;

/**
 * Class needs to be serializable in order to be useable in the session scope.
 *
 * @author olivier
 *
 */
public class User implements Serializable {

	private String name;

	public User() {
		name = "";
	}

	/**
	 * Returns the user name.
	 *
	 * @return not <code>null</code>.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the user name.
	 *
	 * @param name
	 *            <code>null</code> strings are converted to empty strings.
	 */
	public void setName(String name) {
		this.name = name == null ? "" : name;
	}

}
