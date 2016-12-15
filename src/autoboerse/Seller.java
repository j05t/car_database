package autoboerse;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Seller extends Person {

	@PrimaryKeyJoinColumn(name = "ID")
	@OneToMany(mappedBy = "seller")
	private Collection<Transaction> transactions = new ArrayList<Transaction>();

	protected Seller() {
	};

	public Seller(int id, String firstName, String lastName) {
		super(id, firstName, lastName);
	}
}