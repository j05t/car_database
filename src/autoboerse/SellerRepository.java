package autoboerse;

import spize.persistence.Persistence;

public class SellerRepository

		extends Repository<Seller> implements IRepository<Seller> {

	public SellerRepository() {
		super(Seller.class);
	}

	public Seller createSeller(int id, String firstName, String lastName) {

		Seller seller = new Seller(id, firstName, firstName);

		entityManager.persist(seller);

		return seller;
	}

	static void reset() {
		Persistence.resetTable(schema, table);
	}

	static final String schema = "public";
	static final String table = "SELLER";

}
