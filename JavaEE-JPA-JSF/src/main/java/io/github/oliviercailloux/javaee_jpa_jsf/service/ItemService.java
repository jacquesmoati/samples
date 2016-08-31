package io.github.oliviercailloux.javaee_jpa_jsf.service;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import io.github.oliviercailloux.javaee_jpa_jsf.model.Item;
import io.github.oliviercailloux.javaee_jpa_jsf.model.Item_;
import io.github.oliviercailloux.javaee_jpa_jsf.utils.QueryHelper;

@RequestScoped
@Named
public class ItemService {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private QueryHelper helper;

	@Transactional
	public List<Item> getAll() {
		return em.createQuery(helper.selectAll(em.getCriteriaBuilder(), Item.class)).getResultList();
	}

	@Transactional
	public List<String> getAllNames() {
		final CriteriaQuery<String> query = em.getCriteriaBuilder().createQuery(String.class);
		final Root<Item> from = query.from(Item.class);
		query.select(from.get(Item_.name));
		return em.createQuery(query).getResultList();
	}

	/**
	 * <p>
	 * Retrieves a subset of item names, ordered by name (compared
	 * lexicographically), from start to end, inclusive.
	 * </p>
	 * <p>
	 * Start must be ≤ end.
	 * </p>
	 *
	 * @param start
	 *            the lower bound, numbered from zero.
	 * @param end
	 *            use {@link Integer#MAX_VALUE} for no upper bound.
	 * @return the subset of names.
	 */
	public List<String> getSubsetNames(int start, int end) {
		checkArgument(end == Integer.MAX_VALUE || end + 1 >= start);
		checkArgument(start >= 0);

		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		final CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
		final Root<Item> from = query.from(Item.class);
		query.select(from.get(Item_.name));
		query.orderBy(criteriaBuilder.asc(from.get(Item_.name)));

		final TypedQuery<String> typedQuery = em.createQuery(query);
		typedQuery.setFirstResult(start);
		if (end != Integer.MAX_VALUE) {
			final int maxR = end + 1 - start;
			assert (maxR >= 0);
			typedQuery.setMaxResults(maxR);
		}
		return typedQuery.getResultList();
	}

	@Transactional
	public void persist(Item item) {
		em.persist(item);
	}
}
