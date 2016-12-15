package autoboerse;

import spize.persistence.Persistence;

public class FeatureRepository extends Repository<Feature> implements IRepository<Feature> {

	public FeatureRepository() {
		super(Feature.class);
	}

	public Feature createCarFeature(int id, String name) {
		Feature feature = new Feature(id, name);
		entityManager.persist(feature);
		return feature;
	}

	static void reset() {
		Persistence.resetTable(schema, table);
	}

	static final String schema = "public";
	static final String table = "FEATURE";
}