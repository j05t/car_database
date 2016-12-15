package autoboerse;

import spize.persistence.Persistence;

public class PersonRepository extends Repository<Person> implements IRepository<Person> {

	public PersonRepository() {
		super(Person.class);
	}

	public Seller createSeller(int id, String firstName, String lastName) {
		Seller seller = new Seller(id, firstName, lastName);
		entityManager.persist(seller);
		return seller;
	}

	public Buyer createBuyer(int id, String firstName, String lastName) {
		Buyer buyer = new Buyer(id, firstName, lastName);
		entityManager.persist(buyer);
		return buyer;
	}

	public Person createPerson(int id, String firstName, String lastName) {
		Person person = new Person(id, firstName, lastName);
		entityManager.persist(person);
		return person;
	}

	static void reset() {
		Persistence.resetTable(schema, table);
		Persistence.resetTable(schema, tableSeller);
		Persistence.resetTable(schema, tableBuyer);
	}

	static final String schema = "public";
	static final String table = "Person";
	static final String tableSeller = "Seller";
	static final String tableBuyer = "Buyer";
}
