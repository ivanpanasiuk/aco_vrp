package acsvrp;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import acsvrp.tools.Dbg;
import acsvrp.tools.Def;

/**
 * @author ivan.panasiuk
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
	 * Return distance to another node
	 * @param node
	 * @return int - distance to another node
	 */
	public double getDistance2Node(ANode anode) {
		double res = -1;
		String allEdgesEnds = "{";
		for (AEdge e : edges) {
			allEdgesEnds += " ["+e.endIndx+"]";
			if (e.endIndx.equals(anode.name)) {
				allEdgesEnds += "*";
				res = e.cost.getDistance();
			}
		}
		if (res<0) {	// edge probably start at destination node
			allEdgesEnds = "(-1){";
			for (AEdge e : anode.edges) {
				allEdgesEnds += "["+e.endIndx+"]";
				if (e.endIndx.equals(this.name)) {
					allEdgesEnds += "*";
					res = e.cost.getDistance();
				}
			}
		}
		double d = Math.sqrt(Math.pow((x-anode.x),2)+Math.pow((y-anode.y),2));
		if (res!=d) {
			Dbg.prnl("We got a problem with distance from ["+this.name+" to "+anode.name+"] d="+Def.df4(d)+" res="+Def.df4(res)+" not in "+allEdgesEnds+"}");
		}
		return d;
	}
	
	/**
	 * Calculate Distance to another node
	 * @param node
	 * @return int - calculated distance to another node
	 */
	public double calculateDistance2Node(ANode anode) {
		double d = Math.sqrt(Math.pow((x-anode.x),2)+Math.pow((y-anode.y),2));
		return d;
	}
	
	
	
	/**
	 * Calculate Time to another node
	 * @param node
	 * @return int calculated time to another node
	 */
	public double getTime2Node(ANode anode) {
		double res = -1;
		for (AEdge e : edges) {
			if (e.endIndx.equals(anode.name)) {
				res = e.cost.getTime();
			}
		}
		
		return res;
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

}
