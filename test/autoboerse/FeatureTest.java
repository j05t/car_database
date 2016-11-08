package autoboerse;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import autoboerse.CarRepository;
import autoboerse.Feature;
import autoboerse.FeatureRepository;
import spize.persistence.Transaction;

/**
 * Tests creation, modification and deletion of a feature.
 * 
 * @author js
 * 
 */

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class FeatureTest {

	final private int id = 42;
	final private String description = "Klimaanlage";
	static final boolean verbose = true;

	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;

	static FeatureRepository featureRepository;
	Feature feature;

	@BeforeClass
	public static void setup() {
		featureRepository = new FeatureRepository();

		Transaction.begin();
		FeatureRepository.reset();
		CarRepository.reset();
		Transaction.commit();
	}

	@AfterClass
	public static void teardown() {
		Transaction.begin();
		FeatureRepository.reset();
		CarRepository.reset();
		Transaction.commit();
	}

	@Test
	public void create() {
		Transaction.begin();

		feature = featureRepository.createCarFeature(id, description);
		assertNotNull(feature);

		if (verbose)
			System.out.println("Persisted " + feature);
		Transaction.commit();
	}

	@Test
	public void modify() {

		Feature findFeature = featureRepository.find(id);
		assertNotNull(findFeature);
		assertEquals("Klimaanlage", findFeature.getName());
		Transaction.begin();
		findFeature.setName("Airconditioning");
		Transaction.commit();
		assertEquals("Airconditioning", findFeature.getName());

	}

}