package autoboerse;

import spize.persistence.Persistence;

public class BuyerRepository extends Repository<Buyer> implements IRepository<Buyer> {

	public BuyerRepository() {
		super(Buyer.class);
	}

	public Buyer createBuyer(int id, String firstName, String lastName) {
		Buyer buyer = new Buyer(id, firstName, lastName);
		entityManager.persist(buyer);
		return buyer;
	}

	static void reset() {
		Persistence.resetTable(schema, table);
	}

	static final String schema = "public";
	static final String table = "BUYER";
}