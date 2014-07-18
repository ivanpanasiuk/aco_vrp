/**
 * 
 */
package acsvrp;

import java.util.ArrayList;

/**
 * @author ivan.panasiuk
 *
 */
public class Ant {
	
	//Total cost for ant
	Cost cost = new Cost();
        
	// Ukupni broj gradova koji je mrav posetio
	int num_nodes;
	ArrayList<AEdge> path = new ArrayList<AEdge>();

	Ant() {
		cost.reset();
		num_nodes = 0;
	}
	
	public int addPath (ANodes anodes, int i, int j) {
		this.cost.distance += anodes.get(i).getDistance2Node(anodes.get(j));
		this.cost.time += anodes.get(i).getTime2Node(anodes.get(j));
		this.cost.value += anodes.get(i).getDistance2Node(anodes.get(j));
                
		this.path.add(anodes.getEdge(i, j));
		num_nodes++;
		if (AntColony.LOCAL_UPDATE) {
			anodes.getEdge(i,j).setPheromon(Process.localUpdate(anodes.getEdge(i,j).getPheromon()));
		}
//		Dbg.prnl("Ant.java:addPath: "+i+","+j+" d:"+this.dist+" ("+anodes.getEdge(i, j).len+")");
		//Dbg.delay(10);
		// TODO proveriti ovo
		return anodes.get(j).getDemand();
	}
	
	/**
	 * @return Returns the num_nodes.
	 */
	public int getNumOfNodes() {
		return num_nodes;
	}

	/**
	 * @return Returns the dist.
	 */
	public double getCost() {
		return cost.value;
	}
    

	/**
	 * @return Returns the path.
	 */
	public ArrayList<AEdge> getPath() {
		return path;
	}

	/**
	 * @param path The path to set.
	 */
	void setPath(ArrayList<AEdge> path) {
		this.path = path;
	}
}