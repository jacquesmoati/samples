package io.github.oliviercailloux.javaee_jpa_inject_servlets_junit.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import io.github.oliviercailloux.javaee_jpa_inject_servlets_junit.model.Item;

@ApplicationScoped
public class ItemService {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Item> getAll() {
		return em.createQuery(selectAll(Item.class)).getResultList();
	}

	@Transactional
	public void persist(Item item) {
		em.persist(item);
	}

	private <T> CriteriaQuery<T> selectAll(Class<T> type) {
		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		final CriteriaQuery<T> query = criteriaBuilder.createQuery(type);
		final Root<T> from = query.from(type);
		query.select(from);
		return query;
	}

	EntityManager getEm() {
		return em;
	}

	void setEm(EntityManager em) {
		this.em = em;
	}
}
