package acsvrp;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jgraph.graph.DefaultPort;

import acsvrp.tools.Dbg;

public class ANodes {

	static final Logger logger = Logger.getLogger(ANodes.class);
	
	ArrayList<ANode> anodes = new ArrayList<ANode>();
	public int capacity;
	public double kCorr = 1;
	public int xCorr = 0;
	public int yCorr = 0;
	
	public ANode get(int i) {
		ANode a = null;
		try {
			a = anodes.get(i);
		}
		catch (NullPointerException e) {
			// TODO: handle exception
			logger.info("!!!"+e);
		} 
		return a;
	}
	
	public int size() {
		return anodes.size();
	}
	
	public void add(ANode anode) {
		anodes.add(anode);
//		Dbg.prn("adding n#"+anodes.indexOf(anode)+" ");
		for (ANode n: anodes) {
			if (anode != n) {
				//AEdge aedge = new AEdge();
				AEdge aedge = new AEdge(anode.toString(), n.toString());
				//TODO KOMENTAR
				//GraphConstants.setLabelAlongEdge(aedge.getAttributes(), true);
				//GraphConstants.setSelectable(aedge.getAttributes(), false);
				DefaultPort portS = new DefaultPort();
				anode.add(portS);
				portS.setParent(anode);
				aedge.setSource (anode.getChildAt(0));
				//aedge.setSource (portS);
				DefaultPort portT = new DefaultPort();
				n.add(portT);
				portT.setParent(n);
				aedge.setTarget (n.getChildAt(0));
				//aedge.setTarget (portT);
                                
				aedge.len = anode.dist(n);
                aedge.time = anode.getTimeOfTimeConnectionByDestination(n);
				anode.edges.add(aedge); 
			}
		}
		kCorr = correction();
		xCorr = minX();
		yCorr = minY();
		logger.trace("kCorr:"+kCorr+" xCorr:"+xCorr+" yCorr:"+yCorr);
//		Dbg.delay(100);
	}
	
	public AEdge getEdge(int i, int j) {
		if (i != j) {
		if (i < j) {
			//Dbg.prnl("get #"+j+" #"+i);
			return anodes.get(j).edges.get(i);
		}
		else {
			logger.trace("get #"+i+" #"+j);
			return anodes.get(i).edges.get(j);
		}
		}
		else {
			logger.info("get #"+i+" #"+j+" are same. Plese check. [ANodes.java]");
			Dbg.delay(2000);
			return anodes.get(i).edges.get(j);
		}			
	}
	
	//TODO NOW
	public AEdge getEdge(String startPar, String endPar) {
		AEdge r = null;
		int control = 0;
		for (ANode n: anodes) {
			String currentNode = n.getName();
			if ((currentNode.equals(startPar)) || (currentNode.equals(endPar))) {
				for (AEdge e: n.edges) {
					if ((e.endIndx.equals(startPar)) || (e.endIndx.equals(endPar))) {
						r = e;
						control++;
					}
				}
			}
		}
		if (control>1) {
			Dbg.prnl("Something go wrong ...."+control);
		}
		if (r!=null) {
			Dbg.prnl("Edge found."+startPar+" -> "+endPar+" d:"+r.len);
		}
		return r;
	}
		
	// resetuje stanje svih nodova - svi postaju neposeceni
	public void resetVisited() {
		for (ANode n: anodes) {
			n.setVisited(false);
		}
	}
	
	// resetuje kolicinu feromona svih nodova
	public void resetPheromon() {
		for (ANode node: anodes) {
			for (AEdge e: node.edges) {
				e.setPheromon(AntColony.START_PHEROMON);
			}
		}
	}

	
	public int numOfVisited() {
		int num = 0;
		for (ANode n: anodes) {
			if (n.isVisited()) num++;
		}
		return num;
	}
	
//	public int dest2AllUnvisited(ANode anode) {
//		int dest = 0;
//		for (ANode n: anodes) {
//			if (! n.isVisited()) {
//				dest = dest + anode.dist(n);
//			}
//		}
//		return dest;
//	}
	
	private int maxX() {
		int maxX = 0;
		for (ANode n: anodes) {
			if (n.getX()>maxX) {
				maxX = n.getX();
			}
		}
		return maxX;
	}
	
	private int maxY() {
		int maxY = 0;
		for (ANode n: anodes) {
			if (n.getY()>maxY) {
				maxY = n.getY();
			}
		}
		return maxY;
	}
	
	public int minY() {
		int minY = anodes.get(0).getY();
//		Dbg.prn(minY+": ");
		for (ANode n: anodes) {
			if (n.getY() < minY) {
				minY = n.getY();
			}
//			Dbg.prn(n.getY()+" ");
		}
//		Dbg.prnl("minY: "+minY);
		return minY;
	}
	
	public int minX() {
		int minX = anodes.get(0).getX();
//		Dbg.prn(minX+": ");
		for (ANode n: anodes) {
			if (n.getX() < minX) {
				minX = n.getX();
			}
//			Dbg.prn(n.getX()+" ");
		}
//		Dbg.prnl("minX: "+minX);
		return minX;
	}

	/**
	 * 
	 * Vraca faktor korekcije kako bi se uskladile koordinate i prikaz na ekranu.
	 * 
	 * @return Faktor korekcije
	 */
	public double correction() {
		double corr = 1;
//		Dbg.prnl("x:"+maxX()+" y:"+maxY()+" "+ (AntColony.FIT_X / maxX()) + " <> " + (AntColony.FIT_Y / maxY()));
		// TODO proveriti
		if ((1.0f * AntColony.FIT_Y / (maxY()-yCorr)) < (1.0f * AntColony.FIT_X / (maxX()-xCorr))) {
			corr = 1.0f * AntColony.FIT_Y / (maxY()-yCorr);
//			Dbg.prnl("maxY: "+maxY()+", (AntColony.FIT_Y / maxY()):"+corr);
		}
		else {
			corr = 1.0f * AntColony.FIT_X / (maxX()-xCorr);
//			Dbg.prnl("maxX: "+maxX()+", (AntColony.FIT_X / maxX()):"+corr);
		}
//		Dbg.prnl(corr);
//		Dbg.delay(5000);
		return corr;
	}

	public boolean isEmpty() {
		return anodes.isEmpty();
	}
        
        public ANode getNodeByName(String name)
        {
            for(ANode node : anodes)
            {
                if(node.getName().equals(name))
                {
                    return node;
                }
            }
            
            return null;
        }
}
