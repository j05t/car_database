package autoboerse;

import spize.persistence.Persistence;

public class ManufacturerRepository 
	extends Repository<Manufacturer> implements IRepository<Manufacturer>
	{

	public ManufacturerRepository()
	{
	super (Manufacturer.class);
	}

		public Manufacturer createManufacturer(int id, String name) {

			Manufacturer manufacturer = new Manufacturer(id, name);

			entityManager.persist(manufacturer);
			return manufacturer;
		}

		static void reset() {
			Persistence.resetTable(schema, table);
			// Persistence.resetSequence (schema, sequence);
		}

		static final String schema = "public";
		static final String table = "MANUFACTURER";
		// static final String sequence = "employee_sequence";

	}