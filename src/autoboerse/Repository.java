package autoboerse;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import spize.persistence.Persistence;

/**
 * This class was taken from persistence-with-jpa-examples
 * 
 * @author Harald Habiger
 * @version 1.0
 */

public abstract class Repository<T> {

	protected EntityManager entityManager;
	private String persistenceUnitName = "carDb";

	protected Class<T> entityClass = null;

	public Repository(Class<T> entityClass) {
		this.entityClass = entityClass;

		entityManager = Persistence.connect(persistenceUnitName);
	}

	public Repository(String user, String password, Class<T> entityClass) {
		this.entityClass = entityClass;

		entityManager = Persistence.connect(persistenceUnitName, user, password);
	}

	public T find(int id) {
		return entityManager.find(entityClass, id);
	}

	public List<T> findAll() {
		TypedQuery<T> query = entityManager.createQuery("SELECT entity FROM " + entityClass.getTypeName() + " entity",
				entityClass);

		return query.getResultList();
	}

	public void printAll() {
		List<T> entities = findAll();

		System.out.println("All " + entityClass.getTypeName() + "s ");

		for (T entity : entities)
			System.out.println(entity);

		System.out.println("End Of List ");
		System.out.println();

	}

	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}
}
