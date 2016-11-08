package autoboerse;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

	@EmbeddedId
	private TransactionPK transkationPK;

	@ManyToOne(optional = false)
	@JoinColumn(name = "BUYER_ID", insertable = false, updatable = false)
	private Buyer buyer;

	@ManyToOne(optional = false)
	@JoinColumn(name = "SELLER_ID", insertable = false, updatable = false)
	private Seller seller;

	@ManyToOne(optional = false)
	@JoinColumn(insertable = false, updatable = false)
	private Car car;
	
	

	protected Transaction() {
	}

	public Transaction(Buyer buyer, Seller seller, Car car, Date date) {
		this.transkationPK = new TransactionPK(buyer.getId(), seller.getId(), car.getId(), date);
		this.setBuyer(buyer);
		this.setSeller(seller);
		this.setCar(car);
		
	}

	public TransactionPK getTranskationPK() {
		return transkationPK;
	}

	public void setTranskationPK(TransactionPK transkationPK) {
		this.transkationPK = transkationPK;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Transaction [transkationPK=" + transkationPK + ", buyer=" + buyer + ", seller=" + seller + ", car="
				+ car + "]";
	}

}
