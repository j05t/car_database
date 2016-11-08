package autoboerse;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import autoboerse.Buyer;
import autoboerse.BuyerRepository;
import autoboerse.Person;
import autoboerse.PersonRepository;
import autoboerse.SellerRepository;
import spize.persistence.Transaction;
import spize.persistence.*;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class BuyerTest {

	static final boolean verbose = true;

	final private int id = 1;
	final private String firstName = "Florian";
	final private String lastName = "Gumhold";

	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;
	Buyer buyer;

	static PersonRepository persRepository;

	@BeforeClass
	public static void setup() {

		persRepository = new PersonRepository();

		Transaction.begin();
		BuyerRepository.reset();
		SellerRepository.reset();
		PersonRepository.reset();
		Transaction.commit();

	}

	@AfterClass
	public static void teardown() {

		Transaction.begin();
		BuyerRepository.reset();
		SellerRepository.reset();
		PersonRepository.reset();
		Transaction.commit();
		Persistence.close();
	}

	@Test
	public void create() {

		Transaction.begin();

		buyer = persRepository.createBuyer(id, firstName, lastName);
		assertNotNull(buyer);

		if (verbose)
			System.out.println("Persisted " + buyer);

		Transaction.commit();

	}

	@Test
	public void modify() {

		Person findBuyer = persRepository.find(id);
		assertNotNull(findBuyer);
		assertEquals("Florian", findBuyer.getFirstName());
		assertEquals("Gumhold", findBuyer.getLastName());
		Transaction.begin();
		findBuyer.setFirstName("Josef");
		findBuyer.setLastName("Steppan");
		Transaction.commit();
		assertEquals("Josef", findBuyer.getFirstName());
		assertEquals("Steppan", findBuyer.getLastName());
	}

}
