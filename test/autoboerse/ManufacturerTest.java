package autoboerse;

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
	final private int id = 42;
	final private String name = "Toyota";

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
