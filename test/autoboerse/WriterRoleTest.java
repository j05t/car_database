package autoboerse;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spize.persistence.Persistence;
import spize.persistence.Transaction;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class WriterRoleTest {

	   final static String user     = "florian";
	    final static String password = "fg1";
	    
	    static ManufacturerRepository        manRepository = null;
	    
		final static private int id = 10;
		final private String name = "BMW";

		@BeforeClass 
		public static void setup()
		{	        
	        Persistence.connect ("carDb",user, password);
	        manRepository = new ManufacturerRepository();
	    }

		@AfterClass 
		public static void teardown()
		{
			Transaction.begin();
			ManufacturerRepository.reset();
			Transaction.commit();
	    }

	    @Test 
	    public void createEntity ()
	    {
	        Transaction.begin();
	        manRepository.createManufacturer(id, name);
	        
	        Transaction.commit();
	    }

	    @Test 
	    public void modifyEntity ()
	    {
	    	Transaction.begin();
	        Manufacturer newMan = 
	        manRepository.find(id);
	        assertTrue (newMan.getId() != 0);
	        newMan.setName("AUDI");

	        Transaction.commit();
	    }

	   @Test 
	    public void removeEntity ()
	    {
	        	delete(Persistence.getEntityManager());

	    }
 
	    public static void delete (EntityManager em)
	    {
	        Transaction.begin();
	        em.createQuery (
	            "DELETE FROM Manufacturer man WHERE man.id = '"
	            + id + "'")
	                    .executeUpdate();

	        Transaction.commit();
	    }
}
