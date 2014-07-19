package acsvrp;

public class Cost {
	
	private double value;
	private double distance;
    private double time;
    
    public static class Type {
    	static final int TYPE_DESTINATION = 0;
    	static final int TYPE_TIME = 1;
    }
    
    public static int type = Type.TYPE_DESTINATION;
    
    public Cost() {
    	this.value = 0;
    	this.distance = 0;
    	this.time = 0;
    }
    
    public void reset() {
    	this.value = 0;
    	this.distance = 0;
    	this.time = 0;
    }

	public double getValue() {
		return value;
	}

	public void setValue() {
		if (type==Type.TYPE_TIME) {
			this.value = this.time;
		} else {
			this.value = this.distance;
		}
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
	
	public void incValue(double value) {
		this.value += value;
	}
}
