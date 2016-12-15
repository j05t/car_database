package autoboerse;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Manufacturer {

	@SequenceGenerator(name = "ManufacturerIdGenerator", sequenceName = "Manufacturer_Id_Sequence", allocationSize = 1)

	@Id
	@GeneratedValue(generator = "ManufacturerIdGenerator")
	private int id;
	private String name;

	@OneToMany(mappedBy = "manufacturer")
	private Collection<Car> cars = new ArrayList<Car>();

	public Collection<Car> getCars() {
		return cars;
	}

	void add(Car car) {
		cars.add(car);
	}

	protected Manufacturer() {
	}

	public Manufacturer(int id, String name) {
		setId(id);
		setName(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Manufacturer [getCars()=" + getCars() + ", getId()=" + getId() + ", getName()=" + getName() + "]";
	}
}