package io.github.oliviercailloux.javase_maven_jul_hib_h2.launch;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import io.github.oliviercailloux.javase_maven_jul_hib_h2.entities.MyEntity;

public class Launcher {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Launcher.class.getCanonicalName());

	public static void main(String[] args) {
		LOGGER.info("Starting.");
		new Launcher().launch();
	}

	@SuppressWarnings("boxing")
	public void launch() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyUnit");
		EntityManager em1 = factory.createEntityManager();
		EntityTransaction tx = em1.getTransaction();
		tx.begin();
		MyEntity myEntity = new MyEntity();
		em1.persist(myEntity);
		LOGGER.info("New entity id: " + myEntity.getId() + ".");
		tx.commit();
		tx.begin();
		myEntity.setName("MyName");
		tx.commit();
		em1.close();
		factory.close();
	}
}
