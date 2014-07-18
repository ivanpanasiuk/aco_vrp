package acsvrp;

public class Cost {
	
	public double value;
	public double distance;
    public double time;
    
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

}
