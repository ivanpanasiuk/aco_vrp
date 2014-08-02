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
//		this.removeAll();
		
		// Dodaj centralni magacin - node 0
		getGraphLayoutCache().insert(anodes.get(0).createVertex(Color.RED, anodes.kCorr, anodes.xCorr, anodes.yCorr));
		// Dodaj ostale gradove
		for (int i = 1; i < anodes.size(); i++) {	
			logger.trace("AGraph.java: dodajem grad " + i + ":" + anodes.get(i) + " ");
			getGraphLayoutCache().insert(anodes.get(i).createVertex(Color.YELLOW, anodes.kCorr, anodes.xCorr, anodes.yCorr));
			Dbg.delay(30);
			addAllEdges(i);
			Dbg.delay(30);
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
		logger.debug("addAllEdges: Dodajem putanje od grada "+i+" do ostalih gradova.");
		for (AEdge e: anodes.get(i).edges) {
			GraphConstants.setLabelAlongEdge(e.getAttributes(), true);
			GraphConstants.setSelectable(e.getAttributes(), false);
			GraphConstants.setLineColor(e.getAttributes(), new Color(200,200,200));
			logger.trace("addAllEdges e = "+e.getToolTipString());
			getGraphLayoutCache().insert(e);
//			getGraphLayoutCache().setVisible(e, false);
		}
		getGraphLayoutCache().setVisible(anodes.get(i).edges.toArray(), false);
		logger.trace("Sve putanje dodate");
//		Dbg.delay(100);
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
		logger.debug("addNewNode (int x, int y) : new size = "+anodes.size());
	}
	
	public void redrawAllEdges() {		
		/*
		logger.debug("redrawAllEdges()");
		if (AntColony.DIPSLAY_LEVEL > 2) {
			Dbg.delay(50 * AntColony.DIPSLAY_LEVEL);
		}
		*/
		/*
		for (ANode node: anodes.anodes) {
			for (AEdge e: node.edges) {
				redrawEdge(e);
			}
		}
		*/
		
		ArrayList<AEdge> notVisibleEdges = new ArrayList<AEdge>();
		ArrayList<AEdge> visibleEdges = new ArrayList<AEdge>();
		for (ANode node: anodes.anodes) {
			for (AEdge e: node.edges) {
				e.pheromon = e.pheromon + 1 - 1;
//				logger.debug(e.getToolTipString());
				if (e.getPheromon() <= AntColony.START_PHEROMON) {
					notVisibleEdges.add(e);
				} else {
					GraphConstants.setLineColor(e.getAttributes(), new Color(200,200,200));
					visibleEdges.add(e);
				}
			}
		}
		
		if (visibleEdges.size() > 0) {
			getGraphLayoutCache().setVisible(visibleEdges.toArray(), true);
		}
		if (notVisibleEdges.size() > 0) {
			getGraphLayoutCache().setVisible(notVisibleEdges.toArray(), false);
		}
//		logger.debug("Redraw all egdes finished.");
		/*
		if (AntColony.DIPSLAY_LEVEL > 2) {
			Dbg.delay(50 * AntColony.DIPSLAY_LEVEL);
		}
		*/
	}	
	
	public void redrawEdge(int i, int j) {
		AEdge e = anodes.getEdge(i, j);
		logger.trace("redrawEdge(i,j): "+e.getToolTipString());
		if (e != null) {
			redrawEdge(e);
		}
	}
	
	public void redrawEdge (AEdge e) {
		logger.debug("redrawEdge (AEdge e): "+e.getToolTipString());
		
		if (e.getSource()==null) {
			logger.error("redrawEdge: e.getSource()==null");
		}
		if (e.getTarget()==null) {
			logger.error("redrawEdge: e.getTarget()==null");
		}
		
//		GraphConstants.setLineColor(e.getAttributes(), Color.RED);
//		getGraphLayoutCache().setVisible(e, true);
		
		if (AntColony.DIPSLAY_LEVEL > 2) {
//			Dbg.delay(60 * AntColony.DIPSLAY_LEVEL);
		}
		
		if (e.getPheromon() > AntColony.START_PHEROMON) {
			try {
				logger.trace("e.getPheromon() > AntColony.START_PHEROMON");
				GraphConstants.setLineColor(e.getAttributes(), new Color(200,200,200));
				getGraphLayoutCache().setVisible(e, true);
				repaint();
			} catch (Exception exp) {
				logger.error("Showing.NullPointerException: i="+e.getToolTipString()+exp);
			}
		} else {
			logger.debug("Hide "+ e.getToolTipString());
			try {
				getGraphLayoutCache().setVisible(e, false);
//				GraphConstants.setLineColor(e.getAttributes(), new Color(230,230,230));
				repaint();
			} catch (Exception exp) {
				logger.error("Hidding.NullPointerException: "+e.getToolTipString()+exp);
			}
		}
		
		logger.debug("redrawEdge(AEdge e) finished. "+e.getToolTipString());
		
		if (AntColony.DIPSLAY_LEVEL > 2) {
			Dbg.delay(50 * AntColony.DIPSLAY_LEVEL);
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
//		anodes.removeAll();
//		getGraphLayoutCache().notifyAll();
//		getGraphLayoutCache().
	}
}