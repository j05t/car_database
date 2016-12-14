package autoboerse;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import autoboerse.Address;
import autoboerse.AddressPK;
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
	final private String location = "Kapfenberg";
	final private String street = "Werkstrasse 6";
	final private int postalcode = 1234;

	final private AddressPK addressPk = new AddressPK(street, postalcode);

	static AddressRepository addrRepository;
	Address address;

	@BeforeClass
	public static void setup() {
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
		address = addrRepository.createAddress(addressPk, location);
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
