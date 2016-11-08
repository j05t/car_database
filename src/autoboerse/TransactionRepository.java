package autoboerse;

import java.util.Date;

import spize.persistence.Persistence;

public class TransactionRepository extends Repository<Transaction> implements IRepository<Transaction>

{

	public TransactionRepository() {
		super(Transaction.class);
	}

	public Transaction createTransaction(Buyer buyer, Seller seller, Car car, Date date) {

		Transaction transaction = new Transaction(buyer, seller, car, date);

		entityManager.persist(transaction);

		return transaction;
	}

	static void reset() {
		Persistence.resetTable(schema, table);
	}

	static final String schema = "public";
	static final String table = "TRANSACTION";

}