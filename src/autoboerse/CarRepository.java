package autoboerse;

import spize.persistence.Persistence;

public class CarRepository extends Repository<Car> implements IRepository<Car> {

	public CarRepository() {
		super(Car.class);
	}

	public Car createCar(int id, Manufacturer manufacturer, Category category, String name, int km,
			String registration_year, double price, String description) {

		Car car = new Car(id, manufacturer, category, name, km, registration_year, price, description);

		entityManager.persist(car);

		return car;
	}

	static void reset() {
		Persistence.resetTable(schema, table);
	}

	static final String schema = "public";
	static final String table = "CAR";

}