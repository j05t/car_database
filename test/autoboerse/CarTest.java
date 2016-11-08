package autoboerse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import autoboerse.Car;
import autoboerse.CarRepository;
import autoboerse.Category;
import autoboerse.CategoryRepository;
import autoboerse.Feature;
import autoboerse.FeatureRepository;
import autoboerse.Manufacturer;
import autoboerse.ManufacturerRepository;
import spize.persistence.Transaction;

import static org.junit.Assert.*;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class CarTest {

	static final boolean verbose = true;

	final private int id = 42;
	final private int km = 98000;
	final private float price = 12000;

	final private String registrationYear = "2001-12-24";
	final private String name = "VW Passat 16V 365 PS, Christmas Edition";
	final private String description = "Etliche Kratzer, Lenkrad fehlt.";

	final private String manufacturerDescription = "VW";
	final private int manufacturerId = 1;

	final private String CategoryDescription = "Limousine";
	final private int CategoryId = 1;

	final private String feature1Description = "Klimaanlage";
	final private int feature1Id = 1;

	final private String feature2Description = "Tempomat";
	final private int feature2Id = 2;

	/*
	 * final private Manufacturer manufacturer = new Manufacturer(1, "VW");
	 * final private Category category = new Category(1, "Limousine"); final
	 * private Feature feature1 = new Feature(1, "Klimaanlage"); final private
	 * Feature feature2 = new Feature(2, "Tempomat");
	 */

	Car car;
	Manufacturer manufacturer;
	Category category;
	Feature feature1;
	Feature feature2;

	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;

	static CarRepository carRepository;
	static FeatureRepository featureRepository;
	static CategoryRepository categoryRepository;
	static ManufacturerRepository manufacturerRepository;

	@BeforeClass
	public static void setup() {
		carRepository = new CarRepository();
		featureRepository = new FeatureRepository();
		categoryRepository = new CategoryRepository();
		manufacturerRepository = new ManufacturerRepository();

		Transaction.begin();
		CarRepository.reset();
		ManufacturerRepository.reset();
		FeatureRepository.reset();
		CategoryRepository.reset();

		Transaction.commit();

	}

	@AfterClass
	public static void teardown() {
		Transaction.begin();
		CarRepository.reset();
		ManufacturerRepository.reset();
		FeatureRepository.reset();
		CategoryRepository.reset();

		Transaction.commit();
	}

	@Test
	public void create() {

		Transaction.begin();

		feature1 = featureRepository.createCarFeature(feature1Id, feature1Description);
		feature2 = featureRepository.createCarFeature(feature2Id, feature2Description);
		category = categoryRepository.createCategory(CategoryId, CategoryDescription);
		manufacturer = manufacturerRepository.createManufacturer(manufacturerId, manufacturerDescription);
		car = carRepository.createCar(id, manufacturer, category, name, km, registrationYear, price, description);

		assertNotNull(feature1);
		assertNotNull(feature2);
		assertNotNull(category);
		assertNotNull(manufacturer);
		assertNotNull(car);

		if (verbose)
			System.out.println("Persisted " + feature1);
		if (verbose)
			System.out.println("Persisted " + feature2);
		if (verbose)
			System.out.println("Persisted " + category);
		if (verbose)
			System.out.println("Persisted " + manufacturer);
		if (verbose)
			System.out.println("Persisted " + car);
		Transaction.commit();

	}

	@Test
	public void modify() {

		Car findCar = carRepository.find(id);
		assertNotNull(findCar);
		assertEquals("Etliche Kratzer, Lenkrad fehlt.", findCar.getDescription());
		Transaction.begin();
		findCar.setDescription("Neue Beschreibung");
		Transaction.commit();
		assertEquals("Neue Beschreibung", findCar.getDescription());

	}

}
