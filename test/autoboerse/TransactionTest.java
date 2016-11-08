package autoboerse;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import autoboerse.Buyer;
import autoboerse.BuyerRepository;
import autoboerse.Car;
import autoboerse.CarRepository;
import autoboerse.Category;
import autoboerse.CategoryRepository;
import autoboerse.Manufacturer;
import autoboerse.ManufacturerRepository;
import autoboerse.PersonRepository;
import autoboerse.Seller;
import autoboerse.SellerRepository;
import autoboerse.TransactionRepository;
import spize.persistence.Persistence;
import spize.persistence.Transaction;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class TransactionTest {
	// Car
	static final int bmwId = 1;
	static final String bmwName = "BMW";
	static final int bmwKm = 1;
	static final String bmwRegistrationYear = "1990-12-24";
	static final double bmwPrice = 10000.00;
	static final String bmwDescription = "X1";
	static final int bmwManufacturerId = 1;
	static final int bmwFeatureId = 1;

	static final int bmw2Id = 2;
	static final String bmw2Name = "BMW";
	static final int bmw2Km = 1;
	static final String bmw2RegistrationYear = "1995-12-24";
	static final double bmw2Price = 10000.00;
	static final String bmw2Description = "X3";
	static final int bmw2ManufacturerId = 1;
	static final int bmw2FeatureId = 1;

	// Buyer
	static final int florianID = 1;
	static final String florianFirstName = "Florian";
	static final String florianLastName = "Gumhold";

	static final int josefID = 2;
	static final String josefFirstName = "Josef";
	static final String josefLastName = "Steppan";

	// Seller
	static final int maxID = 3;
	static final String maxFirstName = "Max";
	static final String maxLastName = "Muster";

	static final int markusID = 4;
	static final String markusFirstName = "Markus";
	static final String markusLastName = "Maier";

	// Manufacturer
	static final String manufacturerName = "BMW";
	static final int manufacturerManufacturerId = 1;

	// Category
	static final int categoryId = 1;
	static final String categoryName = "Pickup";

	Manufacturer m1;
	Category c1;

	static Buyer florian;
	static Buyer josef;
	static Seller max;
	static Seller markus;

	static Car car1;
	static Car car2;

	static autoboerse.Transaction t1;
	static Transaction t2;

	Date date1;
	Date date2;

	static CarRepository carRepository;
	static TransactionRepository transRepository;
	static ManufacturerRepository manRepository;
	static CategoryRepository catRepository;
	static BuyerRepository buyerRepository;
	static SellerRepository sellerRepository;
	static PersonRepository persRepository;

	@BeforeClass
	public static void setup() {
		carRepository = new CarRepository();
		transRepository = new TransactionRepository();
		manRepository = new ManufacturerRepository();
		catRepository = new CategoryRepository();
		buyerRepository = new BuyerRepository();
		sellerRepository = new SellerRepository();
		persRepository = new PersonRepository();

		Transaction.begin();
		TransactionRepository.reset();
		CarRepository.reset();
		ManufacturerRepository.reset();
		CategoryRepository.reset();
		BuyerRepository.reset();
		SellerRepository.reset();
		PersonRepository.reset();
		Transaction.commit();
	}

	@AfterClass
	public static void teardown() {
		Transaction.begin();
		TransactionRepository.reset();
		CarRepository.reset();
		ManufacturerRepository.reset();
		CategoryRepository.reset();
		BuyerRepository.reset();
		SellerRepository.reset();
		PersonRepository.reset();
		Transaction.commit();
		Persistence.close();
	}

	@Test
	public void create() {
		// Make a single transaction for buyer and seller otherwise an exception
		// is raised
		// Maybe change inheritance.joined to table_per_class
		Transaction.begin();

		florian = buyerRepository.createBuyer(florianID, florianFirstName, florianLastName);
		max = sellerRepository.createSeller(maxID, maxFirstName, maxLastName);

		josef = buyerRepository.createBuyer(josefID, josefFirstName, josefLastName);
		markus = sellerRepository.createSeller(markusID, markusFirstName, markusLastName);

		Transaction.commit();

		Transaction.begin();
		assertNotNull(florian);
		assertNotNull(max);

		c1 = catRepository.createCategory(categoryId, categoryName);
		assertNotNull(c1);

		m1 = manRepository.createManufacturer(manufacturerManufacturerId, manufacturerName);
		assertNotNull(m1);

		car1 = carRepository.createCar(bmwId, m1, c1, bmwName, bmwKm, bmwRegistrationYear, bmwPrice, bmwDescription);
		assertNotNull(car1);

		car2 = carRepository.createCar(bmw2Id, m1, c1, bmw2Name, bmw2Km, bmw2RegistrationYear, bmw2Price,
				bmw2Description);
		assertNotNull(car2);

		date1 = java.sql.Date.valueOf("2011-09-04");
		date2 = java.sql.Date.valueOf("2020-09-04");

		// create transaction

		t1 = transRepository.createTransaction(florian, max, car1, date1);

		assertNotNull(t1);

		Transaction.commit();
	}

	@Test
	public void verify() {
		// Transaction
		List<autoboerse.Transaction> trans = transRepository.findAll();
		assertEquals(1, trans.size());
		assertTrue(trans.contains(t1));

		// CAR-TRANSACTION
		assertEquals(car1, t1.getCar());
		List<Car> cars = carRepository.findAll();
		assertEquals(2, cars.size());
		assertTrue(cars.contains(car1));

		// TRANSACTION-BUYER
		List<Buyer> buyer = buyerRepository.findAll();
		assertEquals(2, buyer.size());
		assertEquals(florian, t1.getBuyer());

		// TRANSACTION-SELLER
		List<Seller> seller = sellerRepository.findAll();
		assertEquals(2, seller.size());
		assertEquals(max, t1.getSeller());
	}
}
