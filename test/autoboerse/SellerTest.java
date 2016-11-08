package autoboerse;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import autoboerse.BuyerRepository;
import autoboerse.Person;
import autoboerse.PersonRepository;
import autoboerse.Seller;
import autoboerse.SellerRepository;
import spize.persistence.Transaction;
import spize.persistence.*;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class SellerTest {

	static final boolean verbose = true;
	final private int id = 2;
	final private String firstName = "Josef";
	final private String lastName = "Steppan";

	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;

	static PersonRepository persRepository;
	Seller seller;

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

		seller = persRepository.createSeller(id, firstName, lastName);
		assertNotNull(seller);

		if (verbose)
			System.out.println("Persisted " + seller);

		Transaction.commit();
	}

	@Test
	public void modify() {

		Person findSeller = persRepository.find(id);
		assertNotNull(findSeller);
		assertEquals("Josef", findSeller.getFirstName());
		assertEquals("Steppan", findSeller.getLastName());
		Transaction.begin();
		findSeller.setFirstName("Florian");
		findSeller.setLastName("Gumhold");
		Transaction.commit();
		assertEquals("Florian", findSeller.getFirstName());
		assertEquals("Gumhold", findSeller.getLastName());

	}

}
