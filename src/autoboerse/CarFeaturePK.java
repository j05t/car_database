package autoboerse;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CarFeaturePK implements Serializable {

	@Column(name = "FEATURES_ID")
	private int featureId;
	@Column(name = "CARS_ID")
	private int carId;

	public CarFeaturePK() {

	}

	public CarFeaturePK(int featureId, int carId) {
		this.featureId = featureId;
		this.carId = carId;
	}

	public int getFeatureId() {
		return featureId;
	}

	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	@Override
	public boolean equals(Object o) {

		return o instanceof CarFeaturePK && ((CarFeaturePK) o).featureId == featureId
				&& ((CarFeaturePK) o).carId == carId;

	}

	@Override
	public int hashCode() {

		return featureId + carId;
	}

	@Override
	public String toString() {
		return "CarFeaturePK [featureId=" + featureId + ", carId=" + carId + "]";
	}

}
