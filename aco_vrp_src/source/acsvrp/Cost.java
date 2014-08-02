package acsvrp;

public class Cost {

	private double distance;
	private double time;

	public static class Type {
		static final int TYPE_DESTINATION = 0;
		static final int TYPE_TIME = 1;
	}

	public Cost() {
		this.distance = 0;
		this.time = 0;
	}

	public void reset() {
		this.distance = 0;
		this.time = 0;
	}

	public double getValue() {
		double v;
		if (AntColony.costType==Type.TYPE_TIME) {
			v = this.time;
		} else {
			v = this.distance;
		}
		return v;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void incDistance(double distance) {
		this.distance += distance;
	}

	public void incTime(double time) {
		this.time += time;
	}

}
