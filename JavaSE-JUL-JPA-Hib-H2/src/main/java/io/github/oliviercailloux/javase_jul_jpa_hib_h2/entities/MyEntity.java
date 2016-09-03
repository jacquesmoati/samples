package io.github.oliviercailloux.javase_jul_jpa_hib_h2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String name;

	public MyEntity() {
		name = "";
	}

	public int getId() {
		return id;
	}

	/**
	 * Returns the item name.
	 *
	 * @return not <code>null</code>.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the item name.
	 *
	 * @param name
	 *            <code>null</code> strings are converted to empty strings.
	 */
	public void setName(String name) {
		this.name = name == null ? "" : name;
	}

}
