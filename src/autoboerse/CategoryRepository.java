package autoboerse;

import spize.persistence.Persistence;

public class CategoryRepository extends Repository<Category> implements IRepository<Category> {

	public CategoryRepository() {
		super(Category.class);
	}

	public Category createCategory(int id, String name) {
		Category category = new Category(id, name);
		entityManager.persist(category);
		return category;
	}

	static void reset() {
		Persistence.resetTable(schema, table);
	}

	static final String schema = "public";
	static final String table = "CATEGORY";
}