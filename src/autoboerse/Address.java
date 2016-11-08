package autoboerse;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Address {

	@EmbeddedId
	private AddressPK addressPK;

	private String location;

	@OneToOne
	@JoinColumn(name = "PERSON_ID")
	private Person person;

	protected Address() {
	}

	public Address(AddressPK addressPK, String location) {
		this.addressPK = addressPK;
		this.location = location;

	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public AddressPK getAddressPK() {
		return addressPK;
	}

	public void setAddressPK(AddressPK addressPK) {
		this.addressPK = addressPK;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Address [getPerson()=" + getPerson() + ", getAddressPK()=" + getAddressPK() + ", getLocation()="
				+ getLocation() + "]";
	}
}
