package br.com.sistemaShopping.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.provider.ConnectionFactory;
import br.com.sistemaShopping.model.GenericEntity;
import br.com.sistemaShopping.model.Shop;

public class ShopDAO<T extends GenericEntity> {

	private static EntityManager manager = ConnectionFactory.getEntityManager();

	public Shop findById(Class<Shop> shop, Long id) {
		return manager.find(shop, id);
	}

	public List<Shop> findALL() {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Shop> cq = cb.createQuery(Shop.class);
		Root<Shop> rootEntry = cq.from(Shop.class);
		CriteriaQuery<Shop> all = cq.select(rootEntry);
		TypedQuery<Shop> allQuery = manager.createQuery(all);
		return allQuery.getResultList();
	}

	public void saveOrUpdate(Shop shop) {
		try {
			manager.getTransaction().begin();
			if (shop.getId() == null) {
				manager.persist(shop);
			} else {
				manager.merge(shop);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	public void remove(Class<Shop> shop, Long id) {
		Shop s = findById(shop, id);
		try {
			manager.getTransaction().begin();
			manager.remove(s);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}
}
