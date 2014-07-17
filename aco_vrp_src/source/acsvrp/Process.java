/**
 * 
 */
package acsvrp;

import java.awt.Color;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.jgraph.graph.GraphConstants;

import acsvrp.tools.Dbg;
import acsvrp.tools.Def;
import acsvrp.ui.AFile;

/**
 * @author ipanasiuk
 *
 */
public class Process {

	static final Logger logger = Logger.getLogger(Process.class);
	
	//private ANodes anodes;
	private AGraph aG;
	public Ant bestAnt;
	private boolean finished = false;
	
	/**
	 * @param agraph
	 * @param anodes
	 */
	/*
	public Process(AGraph agraph) {
		super();		
		this.aG = agraph;
		runAlgoritham();
	}
	*/

	//public void runAlgoritham() {
	public Process(AGraph agraph) {
		String startTime = getNow();
		aG = agraph;
//		int antNum = (anodes.size()+1) / 2 ;
		int antNum = aG.anodes.size()-1;
		int bestCycle = 0;
		int sameCyleces = 0;
		aG.anodes.resetPheromon();
				
		// Pocetak obrade - ciklusi...
		for (int cycle = 0; cycle < aG.anodes.size() * AntColony.MAX_CYCLES_PARAM; cycle++) {
		
			if (AntColony.DIPSLAY_LEVEL > 0) {
				String cycleText = "Cycle: " + (cycle+1) + " of " + Def.df0(aG.anodes.size() * AntColony.MAX_CYCLES_PARAM);
				ShowPheromon.setLCycle(cycleText);
				MainFrame.statusBar.setText(cycleText);
			} else {
//				Dbg.prnl("Cycle: " + (cycle+1) + " of " + anodes.size()*AntColony.MAX_CYCLES_PARAM);
//				Dbg.prn("  "+antNum+" ants: ");
			}
			
			Ant[] ants = new Ant[antNum];
			int antBestIndx = 0;
			
			// Svaki mrav bira putanju
			for (int antCount = 0; antCount < antNum; antCount++) {
				
				if (AntColony.DIPSLAY_LEVEL > 0) {
					ShowPheromon.setLCurrentAnt("Ant: " + (antCount+1) + " of " + antNum);
					if (AntColony.DIPSLAY_LEVEL > 1) {
						MainFrame.statusBar.setText(" Calculating ant "+antCount+" of "+antNum + ". Cycle " +(cycle+1)+" of "+aG.anodes.size() * AntColony.MAX_CYCLES_PARAM);
						Dbg.delay(10);
					} 
				}

				aG.anodes.resetVisited();		
				ants[antCount] = new Ant();
				aG.anodes.get(0).setVisited(true);
				
				// Obilazi gradove dok ne obidje sve
				while (aG.anodes.numOfVisited() < aG.anodes.size()) {
					
					int currentNodeIndx = 0;
					int nextNodeIndx;
					int capacity = aG.anodes.capacity;
				
					// Mrav obilazi gradove dok ne popuni kapacitet vozila (ili obidje sve gradove)
					while ((capacity > 0) && (aG.anodes.numOfVisited() < aG.anodes.size())) {
						// Bira sledeci grad
						logger.trace("nextBestNode("+currentNodeIndx+", "+capacity+")");
						nextNodeIndx = nextBestNode(currentNodeIndx, capacity);
						if (AntColony.DIPSLAY_LEVEL > 2) {
							logger.trace("Capacity:" + capacity + " Curr#: "+currentNodeIndx+" Next#: "+nextNodeIndx+" ");
							ShowPheromon.setLcurrentNode("Node: "+currentNodeIndx+" ("+nextNodeIndx+")");
						}						
						if ((nextNodeIndx==0) || (aG.anodes.get(nextNodeIndx).getDemand()>capacity)) {
							capacity = 0;
						} else {
							if (AntColony.DIPSLAY_LEVEL > 2) {
								logger.trace("showEdge");
								showEdge(currentNodeIndx, nextNodeIndx);
								logger.trace("showed");
								Dbg.delay(10);
//								agraph.getGraphLayoutCache().setVisible(anodes.getEdge(currentNodeIndx, nextNodeIndx), true);
							}
							logger.trace("update capacity... ");
							capacity = capacity - ants[antCount].addPath(aG.anodes, currentNodeIndx, nextNodeIndx);
							logger.trace("Done");
							aG.anodes.get(nextNodeIndx).setVisited(true);
							currentNodeIndx = nextNodeIndx;
							//Dbg.prn(" "+anodes.get(nextNodeIndx).isVisited()+" Go on.");
						}
						
						logger.trace(" Vis: "+aG.anodes.numOfVisited()+" Cap: "+capacity+" Dist:"+ants[antCount].getDist());
						if (AntColony.DIPSLAY_LEVEL > 2) {
							ShowPheromon.setLcurrentNode("Node: "+currentNodeIndx+" ("+nextNodeIndx+")");
//							Dbg.prnl("Node: "+capacity);
							ShowPheromon.setLPathCapacity("Capacity left: "+capacity);
//							Dbg.prnl("Capacity left: "+capacity);
							ShowPheromon.setLCurrentPathDist("Path distance: "+Def.df2(ants[antCount].getDist()));
//							Dbg.prnl("Path distance: "+Def.df2(ants[antCount].getDist()));
							ShowPheromon.setLNodesVisited("Cities Visited: "+(aG.anodes.numOfVisited()-1)+" of "+(aG.anodes.size()-1));
							logger.trace("Cities Visited: "+(aG.anodes.numOfVisited()-1)+" of "+(aG.anodes.size()-1));
							//Dbg.delay(20);							
						}
						logger.trace("Next city.");
					} // Vehicle capacity is reached
					int tmpint = ants[antCount].addPath(aG.anodes, currentNodeIndx, 0);
					logger.trace("tmpint = "+tmpint+" capacity = "+capacity);
					capacity = capacity - tmpint;
					if (AntColony.DIPSLAY_LEVEL > 2) {
						showEdge(currentNodeIndx, 0);
						ShowPheromon.setLcurrentNode("Node: "+currentNodeIndx+" (0)");
						ShowPheromon.setLPathCapacity("Capacity left: "+capacity);
						ShowPheromon.setLCurrentPathDist("Path distance: "+Def.df2(ants[antCount].getDist()));
						ShowPheromon.setLNodesVisited("Cities Visited: "+(aG.anodes.numOfVisited()-1)+" of "+(aG.anodes.size()-1));
						//Dbg.delay(30);
					}
//					for (int t=0; t<anodes.size(); t++) {
//						if (!anodes.get(t).isVisited()) {
//						}
//					}
					logger.trace("Next ant");						
				} // All cities are visited. An Ant finished route.
//				logger.debug("All cities are visited. Current dist:"+ants[antCount].getDist());
//				Dbg.delay(500);
		
                                
                                /*************************************************************************************
                                 * ******************************* DODAO SAM JA **************************************
                                 ***********************************************************************************/
                                
                                if ( (ants[antBestIndx].getDist() > ants[antCount].getDist()) || (antCount==0)) {
					antBestIndx = antCount;
					if (AntColony.DIPSLAY_LEVEL > 0) {
						logger.trace("Showing best ant distance");
						ShowPheromon.setLBestDistAnt("Best (Ant) distance: "+Def.df2(ants[antBestIndx].getDist())+" ("+antBestIndx+")");
						Dbg.delay(10);
					}
				}
                                
//                                if ( (ants[antBestIndx].getTime() > ants[antCount].getTime()) || (antCount==0)) {
//					antBestIndx = antCount;
//					if (AntColony.DIPSLAY_LEVEL > 0) {
//						logger.trace("Showing best ant time");
//						ShowPheromon.setLBestDistAnt("Best (Ant) time: "+ants[antBestIndx].getTime()+" ("+antBestIndx+")");
//						Dbg.delay(10);
//					}
//				}
                                    
                                    
				logger.trace("Best time: "+ants[antBestIndx].getTime());
				
//				agraph.getGraphLayoutCache().setVisible(ants[antCount].path, false);				
				
				if (AntColony.DIPSLAY_LEVEL > 1) {
					MainFrame.statusBar.setText(" Ant "+(antCount+1)+" of "+antNum+" finished its route. Cycle "+(cycle+1)+" of "+aG.anodes.size() * AntColony.MAX_CYCLES_PARAM);
					//Dbg.delay(50);
					logger.debug("Redraw all egdes.");
					aG.redrawAllEdges();
					Dbg.delay(50);
				} 
				logger.debug("All ants finished routes.");

			} // All ants are finished routes
			
			// U pocetku je 0-ta ruta globalno najbolja puta ruta
			if (cycle == 0) { 
				bestAnt = ants[antBestIndx];
				if (AntColony.DIPSLAY_LEVEL > 0) {
					ShowPheromon.setLBestDist("Best distance: "+Def.df2(bestAnt.getDist())+" ("+cycle+")");
				}
			}
                        /***********************************************************************
                         * ************************** OVO MENJAO *******************************
                         ************************************************/
//                       if (cycle == 0) { 
//				bestAnt = ants[antBestIndx];
//				if (AntColony.DIPSLAY_LEVEL > 0) {
//					ShowPheromon.setLBestDist("Best time: "+bestAnt.getTime()+" ("+cycle+")");
//				}
//			}
                        

			// Isparavanje svih putanja
			if (AntColony.DIPSLAY_LEVEL > 1) {
				MainFrame.statusBar.setText(" Evaporation ...");
			} 
			if (!AntColony.LOCAL_UPDATE) {
				for (int i = 0; i < aG.anodes.size()-1; i++) {
					for (int j=i+1; j<aG.anodes.size(); j++) { 
							aG.anodes.getEdge(i,j).setPheromon(localUpdate(aG.anodes.getEdge(i,j).getPheromon()));
							;
					}
				}
			}
                        
                        /***********************************************************************
                         * ************************** OVO MENJAO *******************************
                         ************************************************/

			// Da li je nadjeno globalno najbolje resenje
			if (bestAnt.getDist() > ants[antBestIndx].getDist()) {
				sameCyleces = 0;
				bestAnt = ants[antBestIndx];
				bestCycle = cycle;
				if (AntColony.DIPSLAY_LEVEL > 0) {
					ShowPheromon.setLBestDist("Best distance: "+Def.df2(bestAnt.getDist())+" ("+cycle+")");
				} 
			}
                        
                        // Da li je nadjeno globalno najbolje resenje
//			if (bestAnt.getTime() > ants[antBestIndx].getTime()) {
//				sameCyleces = 0;
//				bestAnt = ants[antBestIndx];
//				bestCycle = cycle;
//				if (AntColony.DIPSLAY_LEVEL > 0) {
//					ShowPheromon.setLBestDist("Best time: "+bestAnt.getTime()+" ("+cycle+")");
//				} 
//			}
						
			// Pojacaj najbolju putanju
			for (AEdge e : ants[antBestIndx].path) {
//				Dbg.prn((t++)+" "+antBestIndx+":"+ants[antBestIndx].getDist()+" "+bestAnt.getDist()+" r: "+(bestAnt.getDist()/(ants[antBestIndx].getDist()*1.0))+" "+e.getPheromon());
				double newPh = globalUpadte(e.getPheromon(), ants[antBestIndx].getDist());
				e.setPheromon (newPh);
				aG.redrawEdge(e);
//				Dbg.delay(1000);
			}
			
			if (AntColony.DIPSLAY_LEVEL > 0) {
//TODO				agraph.hideAllEdges();
				if (AntColony.DIPSLAY_LEVEL > 1) {
					ShowPheromon.refreshTable();
				}
				Dbg.delay(100);
			}
			
			if (sameCyleces>aG.anodes.size()*AntColony.MAX_CYCLES_PARAM+50) {
				int c = aG.anodes.size()*AntColony.MAX_CYCLES_PARAM;
				cycle = c;
			}
			sameCyleces++;
			
		} //cycle++

		// Zavrsena optimizacija. Iscrtava se globalno najbolja putanja
		if (AntColony.DIPSLAY_LEVEL > 0) {
			for (AEdge e : bestAnt.path) {
				showEdge(e, Color.GREEN);
				logger.debug(e.getToolTipString());
			}
		}
		
		AFile.savePath(bestAnt, aG.anodes.size(), bestCycle, startTime, getNow(), getDuration(startTime));
		if (AntColony.DIPSLAY_LEVEL == 0) {
			Dbg.prn("; " + Def.df2(bestAnt.getDist()) + "; "+Def.df0(bestCycle)+"; "+Def.df0(aG.anodes.size()*AntColony.MAX_CYCLES_PARAM)+"; "+getDuration(startTime));
		} else {
			MainFrame.statusBar.setText(" Calculation finished.");
			//Dbg.prn("ending. ");
			Dbg.delay(50);
		}
		finished = true;
	}
	
	private int nextBestNode(int crnt, int capacity) {
		int best = 0;
		double best_score = 0;
		Random generator = new Random(); 
		for (int i = 1; i < aG.anodes.size(); i++) {
			ANode aN = aG.anodes.get(i);
			if (!aN.isVisited() && (aN.getDemand()<=capacity)) {
				// izracunaj tau ni proizvod iz putanje crnt-og i i-tog Node-a
//				Random generator = new Random();
				double rnd = generator.nextDouble();
//				if (rnd<AntColony.ALFA) {
//					rnd = rnd + AntColony.ALFA;
//					Dbg.prn("+");
//				}
//				double tau_ni = anodes.getEdge(crnt,i).getPheromon() * Math.pow(1000.0/anodes.get(crnt).dist(anodes.get(i)), AntColony.BETA);
//				double new_score =  Math.pow(generator.nextDouble(),1.0/2.0) * tau_ni(crnt,i);
                                
                                 /***********************************************************************
                                ***************************** OVO MENJAO *******************************
                                ************************************************************************/
				double new_score =  tauNi(aG.anodes.getEdge(crnt,i).getPheromon(),aG.anodes.get(crnt).dist(aN),rnd);
//                                double new_score =  tauNi(aG.anodes.getEdge(crnt,i).getPheromon(),aG.anodes.get(crnt).getTimeOfTimeConnectionByDestination(aN),rnd);
                                
//				Dbg.prn(" ["+crnt+"]->["+i+"] rnd:"+rnd+" val:"+new_score);
				if (new_score > best_score) {
					best = i;
					best_score = new_score;
//					Dbg.prn(" best:" + best_score);
				}
//				Dbg.prnl();
//				Dbg.prnl(" Best_score: "+ best_score + " (#" + best + ")");
//				Dbg.delay(500);
			}
		}
		return best;
	}
	
//	private double tau_ni(int i, int j) {
//		double ni = 1000.0;
//		double dist = anodes.get(i).dist(anodes.get(j));
//		if (dist>0) {
//			ni = 1000 / dist;
//		} else { 
//			Dbg.prnl("i:"+i+" j:"+j+" dist:"+dist);
//		}
//		double tau = anodes.getEdge(i, j).getPheromon();
//		double res = Math.pow(tau, AntColony.ALFA) * Math.pow(ni, AntColony.BETA);
//		return anodes.getEdge(i,j).getPh() * Math.pow(1.0/anodes.get(i).dist(anodes.get(j)),AntColony.BETA);
//		Dbg.prnl("     res: "+res);
//		Dbg.delay(1000);
//		return res;
//	}

	/**
	 * @return Returns the finished.
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * @param finished The finished to set.
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	private String getNow() {
	    Calendar cal = Calendar.getInstance(TimeZone.getDefault());	    
	    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	    java.text.SimpleDateFormat sdf = 
	          new java.text.SimpleDateFormat(DATE_FORMAT);
	    sdf.setTimeZone(TimeZone.getDefault());          
//	    System.out.println("Now : " + sdf.format(cal.getTime()));
	    return sdf.format(cal.getTime());
	}

	private long getDuration(String startTime) {
	    Calendar cal = Calendar.getInstance(TimeZone.getDefault());	    
	    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    sdf.setTimeZone(TimeZone.getDefault());          
//	    System.out.println("Now : " + sdf.format(cal.getTime()));
	    Date startDate = null;
	    try {
	        startDate = sdf.parse(startTime);
	   } catch (ParseException e) {
	        System.out.println("Invalid Date Parser Exception ");
	        e.printStackTrace();
	   }
//	   System.out.println((cal.getTime().getTime()-startDate.getTime())/1000);
	   return (cal.getTime().getTime()-startDate.getTime())/1000;
	}
	
	private void showEdge(AEdge e, Color color) {
		if (e != null) {
			try {
				float pheromonWidth = (float) (e.getPheromon()/AntColony.START_PHEROMON);
				if (pheromonWidth > AntColony.MAX_PHEROMON_PIXEL) {
					pheromonWidth = AntColony.MAX_PHEROMON_PIXEL;
				}
				GraphConstants.setLineColor(e.getAttributes(), color);
				GraphConstants.setLineWidth(e.getAttributes(), pheromonWidth);
				//check
				if (AntColony.DIPSLAY_LEVEL > 2) {
					Dbg.delay(15);
					aG.getGraphLayoutCache().setVisible(e, true);
					Dbg.delay(15);
				}
			} catch (NullPointerException obj) {
				logger.error("NullPointerException: "+obj);
				Dbg.delay(1000);
			}		 		
		} else {
			logger.error(" = null");
		}
		//e.showAEdge(color);
	}

	private void showEdge(int currIndx, int nextIndx) {
//		GraphConstants.setLineColor(anodes.getEdge(currIndx, nextIndx).getAttributes(), Color.BLUE);
//		GraphConstants.setLineWidth(anodes.getEdge(currIndx, nextIndx).getAttributes(),(float)(anodes.getEdge(currIndx, nextIndx).getPh()/AntColony.START_PHEROMON));
//		Dbg.prnl("w: "+(float)(anodes.getEdge(currIndx, nextIndx).getPh()/AntColony.START_PHEROMON));
//		agraph.getGraphLayoutCache().setVisible(anodes.getEdge(currIndx, nextIndx), true);
		logger.trace("showEdge "+currIndx+"->"+nextIndx+" ");
		try {
			AEdge e = aG.anodes.getEdge(currIndx, nextIndx);
			if (e != null) {showEdge(e, Color.BLUE);}
		} catch (NullPointerException e) {
			logger.error("showEdge error"+e);
		}
	}
	
	public static double tauNi (double tau, double distance, double rnd) {
//		Dbg.prnl("tauNi   "+tau+" "+distance);
		if (rnd >= AntColony.RO) {
			return rnd * tau * Math.pow(1.0/distance,AntColony.BETA);
		} else {
			return tau * Math.pow(1.0/distance,AntColony.BETA);
		}
	}
	
//	public static double tauNi (int i, int j, ANodes anodes) {
//		return tauNi(anodes.getEdge(i,j).getPh(),anodes.get(i).dist(anodes.get(j)));
//	}

	public static double localUpdate(double oldPh) {
		double newPh;
		newPh = (1.0f-AntColony.RO) * oldPh + AntColony.RO * AntColony.START_PHEROMON;
		return newPh;
	}
	
	public static double globalUpadte(double oldPh, double delta) {
//		return oldPh + AntColony.RO / delta;
		double newPh = oldPh + 1.0 / delta;
		return (newPh);
	}

}
