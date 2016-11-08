package autoboerse;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import autoboerse.CarRepository;
import autoboerse.Category;
import autoboerse.CategoryRepository;
import spize.persistence.Transaction;

/**
 * Tests creation, modification and deletion of a category.
 * 
 * @author js
 * 
 */

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class CategoryTest {

	final private int id = 42;
	final private String name = "Sportwagen";
	static final boolean verbose = true;

	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;

	static CategoryRepository categoryRepository;
	Category category;

	@BeforeClass
	public static void setup() {
		categoryRepository = new CategoryRepository();

		Transaction.begin();
		CategoryRepository.reset();
		CarRepository.reset();
		Transaction.commit();
	}

	@AfterClass
	public static void teardown() {
		Transaction.begin();
		CategoryRepository.reset();
		CarRepository.reset();
		Transaction.commit();
	}

	@Test
	public void create() {

		Transaction.begin();

		category = categoryRepository.createCategory(id, name);
		assertNotNull(category);

		if (verbose)
			System.out.println("Persisted " + category);
		Transaction.commit();
	}

	@Test
	public void modify() {

		Category findCategory = categoryRepository.find(id);
		assertNotNull(findCategory);
		assertEquals("Sportwagen", findCategory.getName());
		Transaction.begin();
		findCategory.setName("Pickup");
		Transaction.commit();
		assertEquals("Pickup", findCategory.getName());

	}

}