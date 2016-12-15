package autoboerse;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import autoboerse.Address;
import autoboerse.AddressRepository;
import autoboerse.PersonRepository;
import spize.persistence.Transaction;
import spize.persistence.*;

/**
 * Tests creation, modification and deletion of an address.
 * 
 * @author js
 * 
 */

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class AddressTest {
	static final int florian_id = 1;
	static final String florian_firstName = "Florian";
	static final String florian_lastName = "Gumhold";
	
	static PersonRepository persRepository;
	static Person florian = new Person(florian_id, florian_firstName, florian_lastName);
	
	final private String location = "Kapfenberg";
	final private String street = "Werkstrasse 6";
	final private int postalcode = 1234;

	private Address address = new Address(florian_id, street, postalcode, location);

	static AddressRepository addrRepository;


	@BeforeClass
	public static void setup() {
		persRepository = new PersonRepository();
		addrRepository = new AddressRepository();
		Transaction.begin();
		AddressRepository.reset();
		PersonRepository.reset();
		Transaction.commit();
	}

	@AfterClass
	public static void teardown() {
		Transaction.begin();
		AddressRepository.reset();
		PersonRepository.reset();
		Transaction.commit();
		Persistence.close();
	}

	@Test
	public void modify() {
		Transaction.begin();
		assertNotNull(address);

		Transaction.commit();

		assertNotNull(address);
		assertEquals("Kapfenberg", address.getLocation());

		Transaction.begin();
		address.setLocation("Wien");
		Transaction.commit();
		assertEquals("Wien", address.getLocation());
	}
}
