package autoboerse;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import autoboerse.CarRepository;
import autoboerse.Feature;
import autoboerse.FeatureRepository;
import spize.persistence.Transaction;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class FeatureTest {
	final private int id = 42;
	final private String description = "Klimaanlage";

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