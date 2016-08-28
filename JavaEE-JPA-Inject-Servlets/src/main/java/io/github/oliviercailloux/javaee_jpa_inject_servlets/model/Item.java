package io.github.oliviercailloux.javaee_jpa_inject_servlets.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;

	public Item() {
		/** Default constructor for instantiation by the container. */
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
