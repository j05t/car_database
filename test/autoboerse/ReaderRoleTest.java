package autoboerse;

import static org.junit.Assert.*;

import javax.persistence.RollbackException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spize.persistence.Persistence;
import spize.persistence.Transaction;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class ReaderRoleTest {

	final static String user = "josef";
	final static String password = "js1";

	static ManufacturerRepository manRepository = null;

	final static private int id = 100;
	final private String name = "Suzuki";

	@BeforeClass
	public static void setup() {
		Persistence.connect("carDb", user, password);
		manRepository = new ManufacturerRepository();
	}

	@AfterClass
	public static void teardown() {
		try {
			Transaction.begin();
			ManufacturerRepository.reset();
			Transaction.commit();
			Persistence.close();
		} catch (Exception e) {
			System.out.println("Exception in ReaderRoleTest.tearDown():");
			e.printStackTrace();
		}
	}

	@Test(expected = RollbackException.class)
	public void createEntity() {
		Transaction.begin();
		manRepository.createManufacturer(id, name);
		Transaction.commit();
		fail("Commit successful.");
	}

	@Test(expected = RollbackException.class)
	public void modifyEntity() {
		Transaction.begin();
		manRepository.createManufacturer(id, name).setName("Volvo");
		Transaction.commit();
		fail("Commit successful.");
	}
}