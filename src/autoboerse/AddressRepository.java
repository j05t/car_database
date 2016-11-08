package autoboerse;

import spize.persistence.Persistence;

public class AddressRepository extends Repository<Address> implements IRepository<Address>

{

	public AddressRepository() {
		super(Address.class);
	}

	public Address createAddress(AddressPK adressePK, String location) {

		Address address = new Address(adressePK, location);

		entityManager.persist(address);

		return address;
	}

	static void reset() {
		Persistence.resetTable(schema, table);
	}

	static final String schema = "public";
	static final String table = "ADDRESS";

}