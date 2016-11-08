package autoboerse;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TransactionPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "SELLER_ID")
	private int sellerId;
	@Column(name = "BUYER_ID")
	private int buyerId;
	@Column(name = "CAR_ID")
	private int carId;
	private Date date;

	public TransactionPK() {
	}

	public TransactionPK(int buyerId, int sellerId, int carId, Date date) {
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.carId = carId;
		this.date = date;
	}

	public int getSellerId() {
		return sellerId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public int getCarId() {
		return carId;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public boolean equals(Object o) {

		return o instanceof TransactionPK && ((TransactionPK) o).sellerId == sellerId
				&& ((TransactionPK) o).buyerId == buyerId && ((TransactionPK) o).carId == carId
				&& ((TransactionPK) o).date.equals(date);

	}

	@Override
	public int hashCode() {

		return sellerId + buyerId + carId + date.hashCode();

	}

	@Override
	public String toString() {
		return "TransactionPK [getSellerId()=" + getSellerId() + ", getBuyerId()=" + getBuyerId() + ", getCarId()="
				+ getCarId() + ", getDate()=" + getDate() + ", hashCode()=" + hashCode() + "]";
	}

}
