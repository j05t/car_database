package autoboerse;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Address {

	@Id
	@Column(name = "PERSON_ID")
	private int personId;

	private String location;
	private String street;
	private int postalcode;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId	// tells JPA to directly map to id in source; the id column serves as both Primary Key and FK
	private Person person;

	protected Address() {
	}

	public Address(int personId, String street, int postalCode, String location) {
		this.personId = personId;
		this.location = location;
		this.postalcode = postalCode;
		this.street = street;
	}

	public String getStreet() {
		return street;
	}

	public int getPostalcode() {
		return postalcode;
	}

	public Person getPerson() {
		return person;
	}

	void setPerson(Person person) {
		this.person = person;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Address && ((Address) o).personId == personId;
	}

	@Override
	public int hashCode() {
		return postalcode + street.hashCode() + personId;
	}

	@Override
	public String toString() {
		return "Address [getPerson()=" + getPerson() + ", getAddressPK()=" + getPersonId() + ", getLocation()="
				+ getLocation() + "]";
	}
}
