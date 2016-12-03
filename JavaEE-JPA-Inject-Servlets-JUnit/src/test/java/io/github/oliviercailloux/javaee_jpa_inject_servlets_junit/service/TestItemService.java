package io.github.oliviercailloux.javaee_jpa_inject_servlets_junit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

import io.github.oliviercailloux.javaee_jpa_inject_servlets_junit.model.Item;

public class TestItemService {

	@Test
	public void testPersistGetAllItems() {
		final ItemService svc = new ItemService();
		final EntityManager em = Persistence.createEntityManagerFactory("JavaEE-JPA-Inject-Servlets-JUnit-Test")
				.createEntityManager();
		svc.setEm(em);
		final List<Item> list1 = svc.getAll();
		assertTrue(list1.isEmpty());

		em.getTransaction().begin();
		svc.persist(new Item());
		em.getTransaction().commit();

		final List<Item> list2 = svc.getAll();
		assertEquals(1, list2.size());
	}

}
