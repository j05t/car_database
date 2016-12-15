package autoboerse;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Buyer extends Person {

	@PrimaryKeyJoinColumn(name = "ID")
	@OneToMany(mappedBy = "buyer")
	private Collection<Transaction> transactions = new ArrayList<Transaction>();

	protected Buyer() {
	};

	public Buyer(int id, String firstName, String lastName) {
		super(id, firstName, lastName);
	}
}
