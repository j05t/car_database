package autoboerse;

import spize.persistence.Persistence;

public class CarFeatureRepository extends Repository<CarFeature> implements IRepository<CarFeature> {

	public CarFeatureRepository() {
		super(CarFeature.class);
	}

	public CarFeature createCarFeature(CarFeaturePK carFeaturePK) {
		CarFeature carFeature = new CarFeature(carFeaturePK);
		entityManager.persist(carFeature);
		return carFeature;
	}

	static void reset() {
		Persistence.resetTable(schema, table);
	}

	static final String schema = "public";
	static final String table = "CAR_FEATURE";
}