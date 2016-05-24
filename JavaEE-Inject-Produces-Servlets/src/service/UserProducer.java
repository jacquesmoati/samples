package service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import ent.User;

@SessionScoped
public class UserProducer implements Serializable {
	private char startName;

	@Produces
	public @SessionScoped User createUser() {
		final User user = new User();
		final String name;
		switch (startName) {
		case 'c':
		case 'C':
			name = "Chomsky";
			break;
		case 'r':
		case 'R':
			name = "Rawls";
			break;
		default:
			throw new IllegalStateException("Unknown start name: " + startName + ".");
		}
		user.setName(name);
		return user;
	}

	public char getStartName() {
		return startName;
	}

	public void setStartName(char startName) {
		this.startName = startName;
	}
}
