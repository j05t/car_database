package autoboerse;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

	@Id
	private int ID;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;

	@OneToOne(mappedBy = "person", cascade = CascadeType.PERSIST)
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
		address.setPerson(this);
	}

	protected Person() {
	}

	public Person(int ID, String firstName, String lastName) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person [getId()=" + getId() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
				+ "]";
	}
}
