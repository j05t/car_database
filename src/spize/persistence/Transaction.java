package spize.persistence;

/**
* This class was taken from persistence-with-jpa-examples
* 
* @author  Harald Habiger
* @version 1.0
*/

public class Transaction {

	public static void begin() {
		Persistence.getTransaction().begin();
	}

	public static void commit() {
		Persistence.getTransaction().commit();
	}

	public static void rollback() {
		Persistence.getTransaction().rollback();
	}
}
