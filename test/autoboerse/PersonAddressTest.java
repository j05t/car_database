package autoboerse;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import autoboerse.Address;
import autoboerse.AddressRepository;
import autoboerse.BuyerRepository;
import autoboerse.Person;
import autoboerse.PersonRepository;
import autoboerse.SellerRepository;
import autoboerse.TransactionRepository;
import spize.persistence.Persistence;
import spize.persistence.Transaction;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class PersonAddressTest {
	static final int florian_id = 1;
	static final Address florians_address = new Address(florian_id, "Feldweg", 8055, "Graz");
	
	static final String florian_firstName = "Florian";
	static final String florian_lastName = "Gumhold";

	static final int josef_id = 1;
	static final String josef_firstName = "Josef";
	static final String josef_lastName = "Steppan";
	static final String ort = "Graz";

	static PersonRepository persRepository;
	static AddressRepository addrRepository;
	static BuyerRepository buyerRepository;
	static SellerRepository sellerRepository;
	static TransactionRepository transRepository;

	static Person florian;

	@BeforeClass
	public static void setup() {
		persRepository = new PersonRepository();
		addrRepository = new AddressRepository();
		buyerRepository = new BuyerRepository();
		sellerRepository = new SellerRepository();
		transRepository = new TransactionRepository();
		Transaction.begin();
		AddressRepository.reset();
		TransactionRepository.reset();
		BuyerRepository.reset();
		SellerRepository.reset();
		PersonRepository.reset();
		Transaction.commit();
	}

	@AfterClass
	public static void teardown() {
		Transaction.begin();
		AddressRepository.reset();
		TransactionRepository.reset();
		BuyerRepository.reset();
		SellerRepository.reset();
		PersonRepository.reset();
		Transaction.commit();
		Persistence.close();
	}

	@Test
	public void create() {
		Transaction.begin();
		florian = persRepository.createPerson(florian_id, florian_firstName, florian_lastName);
		florian.setAddress(florians_address);
		assertNotNull(florian);
		assertNotNull(florians_address);
		Transaction.commit();
	}

	@Test
	public void join() {
		Transaction.begin();
		florians_address.setPerson(florian);
		Transaction.commit();
	}

	@Test
	public void verify() {
		List<Person> people = persRepository.findAll();
		assertEquals(1, people.size());

		florian = persRepository.find(florian_id);
		assertNotNull(florian);

		assertEquals(florian, people.get(0));
		assertEquals(florian, florians_address.getPerson());

		List<Address> addresses = addrRepository.findAll();
		assertEquals(1, addresses.size());

		assertEquals(florians_address, addresses.get(0));
		assertEquals(florian, florians_address.getPerson());

		Transaction.begin();
		Person josef = persRepository.createPerson(2, josef_firstName, josef_lastName);

		// MUST FAIL!
		florians_address.setPerson(josef);

		try {
			Transaction.commit();

			fail("commit successful");
		} catch (Exception exc) {
		}
	}
}
