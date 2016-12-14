package autoboerse;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CAR_FEATURE")
public class CarFeature {

	@EmbeddedId
	private CarFeaturePK carFeaturePK;

	@ManyToOne(optional = false)
	@JoinColumn(insertable = false, updatable = false)
	private Feature feature;

	@ManyToOne(optional = false)
	@JoinColumn(insertable = false, updatable = false)
	private Car car;

	public CarFeature() {

	}

	public CarFeature(CarFeaturePK carFeaturePK) {
		this.carFeaturePK = new CarFeaturePK(feature.getId(), car.getId());
	}

	@Override
	public String toString() {
		return "CarFeature [carFeaturePK=" + carFeaturePK + ", feature=" + feature + ", car=" + car + "]";
	}
}