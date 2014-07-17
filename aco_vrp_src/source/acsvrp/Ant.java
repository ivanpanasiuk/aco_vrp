/**
 * 
 */
package acsvrp;

import java.util.ArrayList;

/**
 * @author ipanasiuk
 *
 */
public class Ant {
	
	//  Ukupni put koji je mrav presao
	double dist;
        
        int time;
        
	// Ukupni broj gradova koji je mrav posetio
	int num_nodes;
	ArrayList<AEdge> path = new ArrayList<AEdge>();

	Ant() {
		dist = 0;
		num_nodes = 0;
	}
	
	public int addPath (ANodes anodes, int i, int j) {
		this.dist = this.dist + anodes.get(i).dist(anodes.get(j));
                
        this.time += anodes.get(i).getTimeOfTimeConnectionByDestination(anodes.get(j));
                
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
	public double getDist() {
		return dist;
	}
        
        /**
         * @return Returns the time.
         */
	public double getTime()
        {
            return time;
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