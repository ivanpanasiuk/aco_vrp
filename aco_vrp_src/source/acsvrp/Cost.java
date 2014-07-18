package acsvrp;

public class Cost {
	
	public double value;
	public double distance;
    public double time;
    
    static class Type {
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

}
