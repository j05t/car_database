package autoboerse;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class AddressPK implements Serializable {

	private String street;

	private int postalcode;

	public AddressPK() {

	}

	public AddressPK(String street, int postalcode) {
		this.street = street;
		this.postalcode = postalcode;
	}

	public String getStreet() {
		return street;
	}

	public int getPostalcode() {
		return postalcode;
	}

	@Override
	public boolean equals(Object o) {

		return o instanceof AddressPK && ((AddressPK) o).postalcode == postalcode
				&& ((AddressPK) o).street.equals(street);
	}

	@Override
	public int hashCode() {
		return postalcode + street.hashCode();
	}

	@Override
	public String toString() {
		return "AddressPK [getStreet()=" + getStreet() + ", getPostalcode()=" + getPostalcode() + ", hashCode()="
				+ hashCode() + "]";
	}

}
