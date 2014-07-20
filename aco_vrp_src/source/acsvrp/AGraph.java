package acsvrp;

import java.awt.Color;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jgraph.JGraph;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

import acsvrp.tools.Dbg;

public class AGraph extends JGraph {

	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(AGraph.class);
	ANodes anodes;

	/**
	 * @param model
	 */
	
	//public AGraph() {
	public AGraph(GraphModel model, GraphLayoutCache view) {
		
		//super(new DefaultGraphModel());
		super(model, view);

		setGridVisible(true);
		setAntiAliased(true);
		this.setBackground(new Color(245,245,245));
		
		//TODO vratiti ToolTipManager.sharedInstance().registerComponent(this);
		//CellViewFactory cvf = new DefaultCellViewFactory();
		//this.setGraphLayoutCache(new GraphLayoutCache(this.graphModel, cvf, true));

//		MouseListener that Prints the Cell on Doubleclick
		/*
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
//					Get Cell under Mousepointer
					int x = e.getX(), y = e.getY();
					Object cell = getFirstCellForLocation(x, y);
//					Print Cell Label
					if (cell != null) {
						String lab = convertValueToString(cell);
						System.out.println(lab);
					}
				}
			}
		});
		*/
	}
	
	public boolean draw() {
		/*
		ToolTipManager.sharedInstance().registerComponent(this);
		*/
		this.removeAll();
		
		// Dodaj centralni magacin - node 0
		getGraphLayoutCache().insert(anodes.get(0).createVertex(Color.RED, anodes.kCorr, anodes.xCorr, anodes.yCorr));
		// Dodaj ostale gradove
		for (int i = 1; i < anodes.size(); i++) {	
			logger.trace("AGraph.java: dodajem grad " + i + ":" + anodes.get(i) + " ");
			getGraphLayoutCache().insert(anodes.get(i).createVertex(Color.YELLOW, anodes.kCorr, anodes.xCorr, anodes.yCorr));
			Dbg.delay(30);
			addAllEdges(i);
			Dbg.delay(30);
			/*
			for (AEdge e: anodes.get(i).edges) {
				getGraphLayoutCache().insert(e);
				getGraphLayoutCache().setVisible(e, false);
			}
			*/
			//Dbg.delay(1500);
		}
		logger.debug("Svi gradovi i putanje iscrtane. ");
		//getModel().addGraphModelListener(new ModelListener());
		if (anodes.size()>0) {
			return true;
			} else {
			return false;
			}
	}
	
	public void addAllEdges (int i) {
		logger.trace("Dodajem putanje od grada "+i+" do ostalih gradova.");
		for (AEdge e: anodes.get(i).edges) {
			GraphConstants.setLabelAlongEdge(e.getAttributes(), true);
			GraphConstants.setSelectable(e.getAttributes(), false);
			getGraphLayoutCache().insert(e);
			getGraphLayoutCache().setVisible(e, false);
		}
		//TODO getGraphLayoutCache().setVisible(anodes.get(i).edges.toArray(), false);
		logger.trace("Sve putanje dodate");
		Dbg.delay(10);
	}		

	public void addNewNode (int x, int y) {
		String aname = "0";
		if (anodes == null) { 
			anodes = new ANodes();
		} else {
			aname = "" + anodes.size();
		};
		ANode newNode = new ANode(x,y,aname);
		anodes.add(newNode);
		getGraphLayoutCache().insert(newNode.createVertex(Color.ORANGE, 1, 0, 0));
		addAllEdges(anodes.size()-1);
		Dbg.prnl("new size: "+anodes.size());
	}
	
	public void redrawAllEdges() {		
		for (int i = 0; i < anodes.size()-1; i++) {
			for (int j = i+1; j < anodes.size(); j++) {
				redrawEdge(i,j);
			}
		}
	}	
	
	public void redrawEdge(int i, int j) {
		AEdge e = anodes.getEdge(i, j);
		logger.trace("redrawEdge(): "+e.getToolTipString());
		if (e != null) {
			redrawEdge(e);
		}
	}
	
	public void redrawEdge (AEdge e) {
		if (e.getPheromon() > AntColony.START_PHEROMON) {
			logger.trace("setVisible: "+e.getToolTipString());
			try {
				GraphConstants.setLineColor(e.getAttributes(), new Color(200,200,200));
				getGraphLayoutCache().setVisible(e, true);
			} catch (NullPointerException obj) {
				logger.error("Showing.NullPointerException: i="+e.getToolTipString());
			}
//			Dbg.delay(50);
		} else {
			logger.trace(e.getToolTipString()+" hide");
			try {
				this.getGraphLayoutCache().setVisible(e, false);
			} catch (NullPointerException obj) {
				logger.error("Hidding.NullPointerException: "+e.getToolTipString());
			}
//			Dbg.delay(50);
		}
		
	}
	
	//TODO vratiti
	/*
	public String getToolTipText(MouseEvent event) {
		  Object cell = getFirstCellForLocation(event.getX(), event.getY());
		  if (cell instanceof ANode) {
		    return ((ANode) cell).getToolTipString();
		  }
		  if (cell instanceof AEdge) {
			    return ((AEdge) cell).getToolTipString();
			  }
		  return null;
		}
	*/	
	
	public void removeEverything() {
		this.removeAll();
		this.validate();
		anodes.removeAll();
		getGraphLayoutCache().notifyAll();
//		getGraphLayoutCache().
	}
}