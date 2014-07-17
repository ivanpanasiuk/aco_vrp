package acsvrp;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

/**
 * @author ipanasiuk
 *
 */
public class ANode extends DefaultGraphCell {

	private static final long serialVersionUID = -3895939121806721850L;
	
	private int x;
	private int y;
	private int demand;
	private boolean visited;
	private String name;
	public ArrayList<AEdge> edges = new ArrayList<AEdge>();
        private ArrayList<TimeConnection> timeDistances = new ArrayList<TimeConnection>();
	
	public String getToolTipString() {
//		   return ("Demand: "+demand+" ("+GraphConstants.getBounds(getAttributes()).getCenterX()+","+GraphConstants.getBounds(getAttributes()).getCenterY()+")");
		return ("Demand: "+demand+" ("+getX()+","+getY()+")");
		}
    
    public ANode (int x, int y, String name) {
    	super();
    	this.x = x;
    	this.y = y;
    	this.demand = 0;
    	this.name = name;
    	this.visited = false;
    }
    
	/**
     * Calculate Distance to given node
     * @param node
     * @return int calculated distance to give node
     */
	public double dist(ANode anode) {
		return Math.sqrt(Math.pow((x-anode.x),2)+Math.pow((y-anode.y),2));
	}
		
	public String toString() {
//		return "("+ x + "," + y + ")";
		return (name);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
        
        public String getName()
        {
            return name;
        }
        
        public ArrayList<TimeConnection> getTimeDistances()
        {
            return timeDistances;
        }

	/**
	 * @return Returns the visited.
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * @param visited The visited to set.
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	protected ANode createVertex(Color bg, double kCorr, int xCorr, int yCorr) {
		//TODO make circle
		GraphConstants.setBounds(this.getAttributes(),new Rectangle2D.Double((x-xCorr)*kCorr+AntColony.NODE_DIM, (y-yCorr)*kCorr+AntColony.NODE_DIM, AntColony.NODE_DIM, AntColony.NODE_DIM));
		//Dbg.prnl("x:"+x+" y:"+y+" "+((x-xCorr)*kCorr+AntColony.NODE_DIM)+" "+((y-yCorr)*kCorr+AntColony.NODE_DIM)+" k:"+kCorr+" x:"+xCorr+" y:"+yCorr);
		if (bg != null) {
			GraphConstants.setGradientColor(this.getAttributes(), bg);
			GraphConstants.setOpaque(this.getAttributes(),true);
			GraphConstants.setSelectable(this.getAttributes(),true);
			GraphConstants.setSizeable(this.getAttributes(),false);
		}
		GraphConstants.setBorderColor(this.getAttributes(), Color.black);
		GraphConstants.setFont(getAttributes(),new Font("Dialog",Font.PLAIN,10));
		GraphConstants.setHorizontalAlignment(getAttributes(),SwingConstants.CENTER);
		return this;
	}

	/**
	 * @return Returns the demand.
	 */
	public int getDemand() {
//		Dbg.prnl("ANode.java:demand: " + demand);
		return demand;
	}

	/**
	 * @param demand The demand to set.
	 */
	public void setDemand(int demand) {
		this.demand = demand;
	}
        
        public void addTimeConnection(ANode dest, int time)
        {
            TimeConnection td = new TimeConnection(dest, time);
            
            timeDistances.add(td);
        }
        
        public int getTimeOfTimeConnectionByDestination(ANode dest)
        {
            for(TimeConnection tm : timeDistances)
            {
                if(tm.getDestination().equals(dest))
                {
                    return tm.getTime();
                }
            }
            
            return -1;
        }

}
