package autoboerse;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import autoboerse.Car;
import autoboerse.CarFeatureRepository;
import autoboerse.CarRepository;
import autoboerse.Category;
import autoboerse.CategoryRepository;
import autoboerse.Feature;
import autoboerse.FeatureRepository;
import autoboerse.Manufacturer;
import autoboerse.ManufacturerRepository;
import spize.persistence.Transaction;

/**
 * Tests navigation from/to manufacturer <-> car, category <-> car, person <->
 * address
 * 
 * @author js
 * 
 */

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class BiDirectionalTest {
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

	// Features
	final private String feature1Description = "Klimaanlage";
	final private int feature1Id = 1;
	final private String feature2Description = "Tempomat";
	final private int feature2Id = 2;

	// Address
	final private String location = "Kapfenberg";
	final private String street = "Werkstrasse 6";
	final private int postalcode = 1234;
	private Address address = new Address(1, street, postalcode, location);

	// Person
	static final int person_id = 1;
	static final String person_firstName = "Florian";
	static final String person_lastName = "Gumhold";

	Car car;
	Manufacturer manufacturer;
	Category category;
	Feature feature1;
	Feature feature2;
	Person person;

	static CarRepository carRepository;
	static FeatureRepository featureRepository;
	static CategoryRepository categoryRepository;
	static ManufacturerRepository manufacturerRepository;
	static AddressRepository addressRepository;
	static PersonRepository personRepository;

	@BeforeClass
	public static void setup() {
		carRepository = new CarRepository();
		featureRepository = new FeatureRepository();
		categoryRepository = new CategoryRepository();
		manufacturerRepository = new ManufacturerRepository();
		addressRepository = new AddressRepository();
		personRepository = new PersonRepository();

		Transaction.begin();
		CarFeatureRepository.reset();
		CarRepository.reset();
		ManufacturerRepository.reset();
		FeatureRepository.reset();
		CategoryRepository.reset();
		AddressRepository.reset();
		PersonRepository.reset();
		Transaction.commit();
	}

	@AfterClass
	public static void teardown() {
		Transaction.begin();
		CarFeatureRepository.reset();
		CarRepository.reset();
		ManufacturerRepository.reset();
		FeatureRepository.reset();
		CategoryRepository.reset();
		AddressRepository.reset();
		PersonRepository.reset();
		Transaction.commit();
	}

	@Test
	public void testBidirectionals() {
		Transaction.begin();

		feature1 = featureRepository.createCarFeature(feature1Id, feature1Description);
		feature2 = featureRepository.createCarFeature(feature2Id, feature2Description);
		category = categoryRepository.createCategory(CategoryId, CategoryDescription);
		manufacturer = manufacturerRepository.createManufacturer(manufacturerId, manufacturerDescription);
		car = carRepository.createCar(id, manufacturer, category, name, km, registrationYear, price, description);
		person = personRepository.createPerson(person_id, person_firstName, person_lastName);
		address = addressRepository.createAddress(person_id, street, postalcode, location);
		person.setAddress(address);

		Transaction.commit();

		assertNotNull(feature1);
		assertNotNull(feature2);
		assertNotNull(category);
		assertNotNull(manufacturer);
		assertNotNull(car);
		assertNotNull(address);
		assertNotNull(person);

		person.setAddress(address);

		car.add(feature1);
		car.add(feature2);

		assertTrue(category.getCars().contains(car));
		assertTrue(car.getCategory() == category);

		assertTrue(manufacturer.getCars().contains(car));
		assertTrue(car.getManufacturer() == manufacturer);

		assertTrue(feature1.getCars().contains(car));
		assertTrue(feature2.getCars().contains(car));

		assertTrue(person.getAddress().equals(address));
		assertTrue(address.getPerson().equals(person));
	}
}