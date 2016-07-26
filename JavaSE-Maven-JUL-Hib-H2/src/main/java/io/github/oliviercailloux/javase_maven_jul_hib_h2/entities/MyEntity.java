package io.github.oliviercailloux.javase_maven_jul_hib_h2.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MyEntity implements Serializable {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	public MyEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
