package io.github.oliviercailloux.javaee_servlets_jsf.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class ConstantUser {

	/**
	 * Returns the user name (a constant).
	 *
	 * @return not <code>null</code>.
	 */
	public String getName() {
		return "The constant name";
	}

}
