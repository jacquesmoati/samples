package io.github.oliviercailloux.javaee_servlets_jsf.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class VariableUser {

	private int userId;

	public VariableUser() {
		userId = -1;
	}

	/**
	 * Returns the user name.
	 *
	 * @return not <code>null</code>.
	 */
	public String getName() {
		return userId >= 0 ? "Miss Positive" : "Sir Negative";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
