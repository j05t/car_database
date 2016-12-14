package autoboerse;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import autoboerse.Buyer;
import autoboerse.BuyerRepository;
import autoboerse.Person;
import autoboerse.PersonRepository;
import autoboerse.Printer;
import autoboerse.Seller;
import autoboerse.SellerRepository;
import spize.persistence.Persistence;
import spize.persistence.Transaction;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class PersonTest {
	static final int florian_id = 1;
	static final String florian_firstName = "Florian";
	static final String florian_lastName = "Gumhold";

	static final int josef_id = 2;
	static final String josef_firstName = "Josef";
	static final String josef_lastName = "Steppan";

	static Person josef;
	static Person florian;

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
		josef = persRepository.createSeller(josef_id, josef_firstName, josef_lastName);
		florian = persRepository.createBuyer(florian_id, florian_firstName, florian_lastName);
		assertNotNull(florian);
		assertNotNull(josef);
		Transaction.commit();
	}

	@Test
	public void find() {
		List<Person> people = persRepository.findAll();
		Printer.print(people, "all People");
		assertEquals(2, people.size());

		Person person = persRepository.find(florian.getId());
		assertTrue(person instanceof Buyer);

		person = persRepository.find(josef.getId());
		assertTrue(person instanceof Seller);
	}
}
