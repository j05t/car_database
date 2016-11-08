package autoboerse;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Category {

	@SequenceGenerator(name = "CategoryIdGenerator", sequenceName = "Category_Id_Sequence", allocationSize = 1)

	@Id
	@GeneratedValue(generator = "CategoryIdGenerator")
	private int id;
	private String name;

	@OneToMany(mappedBy = "category")
	private Collection<Car> cars = new ArrayList<Car>();

	public Collection<Car> getCars() {
		return cars;
	}

	void add(Car car) {
		cars.add(car);
	}

	protected Category() {

	}

	public Category(int id, String name) {
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
		return "Category [getCars()=" + getCars() + ", getId()=" + getId() + ", getName()=" + getName() + "]";
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Category && ((Category) o).id == id && ((Category) o).name == name;
	}

}