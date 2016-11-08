package autoboerse;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Car {

	@SequenceGenerator(name = "CarIdGenerator", sequenceName = "Car_Id_Sequence", allocationSize = 1)

	@Id
	@GeneratedValue(generator = "CarIdGenerator")
	private int id;

	private String name;
	private int km;

	@ManyToOne
	private Manufacturer manufacturer;

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
		manufacturer.add(this);
	}

	public void setRegistrationYear(Date registrationYear) {
		this.registrationYear = registrationYear;
	}

	@ManyToOne
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
		category.add(this);
	}

	@ManyToMany
	private Collection<Feature> features = new ArrayList<Feature>();

	public Collection<Feature> getFeatures() {
		return features;
	}

	public void add(Feature feature) {
		features.add(feature);
		feature.add(this);
	}

	@Column(name = "registration_year")
	private Date registrationYear;

	public Date getRegistrationYear() {
		return registrationYear;
	}

	public void setRegistrationYear(String registrationYear) {
		this.registrationYear = Date.valueOf(registrationYear);
	}

	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private String description;

	protected Car() {
	}

	public Car(int id, Manufacturer manufacturer, Category category, String name, int km, String registration_year,
			double price, String description) {
		setId(id);
		setManufacturer(manufacturer);
		setCategory(category);
		setName(name);
		setKm(km);
		setRegistrationYear(registration_year);
		setPrice(price);
		setDescription(description);
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

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Auto [getId()=" + getId() + ", getName()=" + getName() + ", getKm()=" + getKm()
				+ ", getRegistrationYear()=" + getRegistrationYear() + ", getPrice()=" + getPrice()
				+ ", getDescription()=" + getDescription() + "]";
	}

}