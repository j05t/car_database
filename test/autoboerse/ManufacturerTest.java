package autoboerse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import autoboerse.CarRepository;
import autoboerse.Manufacturer;
import autoboerse.ManufacturerRepository;
import spize.persistence.Transaction;

import static org.junit.Assert.*;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class ManufacturerTest {

	static final boolean verbose = true;

	final private int id = 42;
	final private String name = "Toyota";

	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;

	static ManufacturerRepository manufacturerRepository;
	Manufacturer manufacturer;

	@BeforeClass
	public static void setup() {
		manufacturerRepository = new ManufacturerRepository();

		Transaction.begin();
		ManufacturerRepository.reset();
		CarRepository.reset();
		Transaction.commit();
	}

	@AfterClass
	public static void teardown() {

		Transaction.begin();
		ManufacturerRepository.reset();
		CarRepository.reset();
		Transaction.commit();
	}

	@Test
	public void create() {

		Transaction.begin();

		manufacturer = manufacturerRepository.createManufacturer(id, name);

		assertNotNull(manufacturer);

		if (verbose)
			System.out.println("Persisted " + manufacturer);
		Transaction.commit();
	}

	@Test
	public void modify() {
		Manufacturer findManufacturer = manufacturerRepository.find(id);
		assertNotNull(findManufacturer);
		assertEquals("Toyota", findManufacturer.getName());
		Transaction.begin();
		findManufacturer.setName("Ford");
		Transaction.commit();
		assertEquals("Ford", findManufacturer.getName());
	}

}
