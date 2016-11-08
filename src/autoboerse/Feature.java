package autoboerse;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Feature {

	@SequenceGenerator(name = "FeatureIdGenerator", sequenceName = "Feature_Id_Sequence", allocationSize = 1)

	@Id
	@GeneratedValue(generator = "FeatureIdGenerator")
	private int id;
	private String description;

	protected Feature() {

	}

	public Feature(int id, String name) {
		setId(id);
		setName(name);
	}

	@ManyToMany(mappedBy = "features")
	private Collection<Car> cars = new ArrayList<Car>();

	public Collection<Car> getCars() {
		return cars;
	}

	void add(Car car) {
		cars.add(car);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return description;
	}

	public void setName(String name) {
		this.description = name;
	}

	@Override
	public String toString() {
		return "Feature [getCars()=" + getCars() + ", getId()=" + getId() + ", getName()=" + getName() + "]";
	}

}