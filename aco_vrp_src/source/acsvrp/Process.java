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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import javax.swing.ImageIcon;

/**
 * @author ivan.panasiuk
 *
 */
public class Process {

    static final Logger logger = Logger.getLogger(Process.class);

    //private ANodes anodes;
    private AGraph aG;
    public Ant bestAnt;
    private boolean finished = false;

    public Process(AGraph agraph)
    {
        String startTime = getNow();
        aG = agraph;

        int antNum = aG.anodes.size() - 1;	//		int antNum = (anodes.size()+1) / 2 ;
        int bestCycle = 0;
        int sameCyleces = 0;
        aG.anodes.resetPheromon();
        int cyclesCount = (aG.anodes.size() - 1) * AntColony.MAX_CYCLES_PARAM;

        //saving cost, distance and time after every cycle
        Random rand = new Random();

        int randomNumber = rand.nextInt(Integer.MAX_VALUE);
        String fileName = "data_" + randomNumber + ".txt";
        File file = new File("./res/" + fileName);
//        File file = new File(fileName);
        BufferedWriter out = null;
        try
        {
            file.createNewFile();
            out = new BufferedWriter(new FileWriter(file));
            out.write("Cost \t Distance \t Time\n");
        } catch (IOException ex)
        {
            java.util.logging.Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Starting cycles
        for (int cycle = 0; cycle < cyclesCount; cycle++)
        {

            if (AntColony.DIPSLAY_LEVEL > 0)
            {
                String cycleText = "Cycle: " + (cycle + 1) + " of " + Def.df0(cyclesCount);
                ShowPheromon.setLabelCycle(cycleText);
                MainFrame.statusBar.setText(cycleText);
            } else
            {
//				Dbg.prnl("Cycle: " + (cycle+1) + " of " + anodes.size()*AntColony.MAX_CYCLES_PARAM);
//				Dbg.prn("  "+antNum+" ants: ");
            }

            Ant[] ants = new Ant[antNum];
            int antBestIndx = 0;

            // Single ant choose route
            for (int antCount = 0; antCount < antNum; antCount++)
            {

                if (AntColony.DIPSLAY_LEVEL > 0)
                {
                    ShowPheromon.setLabelCurrentAnt("Ant: " + (antCount + 1) + " of " + antNum);
                    if (AntColony.DIPSLAY_LEVEL > 1)
                    {
                        MainFrame.statusBar.setText(" Calculating ant " + antCount + " of " + antNum + ". Cycle " + (cycle + 1) + " of " + cyclesCount);
                        Dbg.delay(10);
                    }
                }

                aG.anodes.resetVisited();
                ants[antCount] = new Ant();
                aG.anodes.get(0).setVisited(true);

                // Go over all cities
                while (aG.anodes.numOfVisited() < aG.anodes.size())
                {
                    int currentNodeIndx = 0;
                    int nextNodeIndx;
                    int capacity = aG.anodes.capacity;

                    // single ant route until capacity reached
                    while ((capacity > 0) && (aG.anodes.numOfVisited() < aG.anodes.size()))
                    {
                        // choose next city
                        logger.trace("nextBestNode(" + currentNodeIndx + ", " + capacity + ")");
                        nextNodeIndx = nextBestNode(currentNodeIndx, capacity);
                        if (AntColony.DIPSLAY_LEVEL > 2)
                        {
                            logger.trace("Capacity:" + capacity + " Curr#: " + currentNodeIndx + " Next#: " + nextNodeIndx + " ");
                            ShowPheromon.setLabelCurrentNode("Node: " + currentNodeIndx + " (" + nextNodeIndx + ")");
                        }
                        if ((nextNodeIndx == 0) || (aG.anodes.get(nextNodeIndx).getDemand() > capacity))
                        {
                            capacity = 0;
                        } else
                        {
                            if (AntColony.DIPSLAY_LEVEL > 2)
                            {
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

                        logger.trace(" Vis: " + aG.anodes.numOfVisited() + " Cap: " + capacity + " Cost:" + ants[antCount].getCost());
                        if (AntColony.DIPSLAY_LEVEL > 2)
                        {
                            ShowPheromon.setLabelCurrentNode("Node: " + currentNodeIndx + " (" + nextNodeIndx + ")");
//							Dbg.prnl("Node: "+capacity);
                            ShowPheromon.setLabelCapacityLeft("Capacity left: " + capacity);
//							Dbg.prnl("Capacity left: "+capacity);

                            ShowPheromon.setLabelCurrentPathCost("Path cost: " + Def.df2(ants[antCount].getCost()));
//							Dbg.prnl("Path distance: "+Def.df2(ants[antCount].getDist()));
                            ShowPheromon.setLabelNodesVisited("Cities Visited: " + (aG.anodes.numOfVisited() - 1) + " of " + (aG.anodes.size() - 1));
                            logger.trace("Cities Visited: " + (aG.anodes.numOfVisited() - 1) + " of " + (aG.anodes.size() - 1));
                            //Dbg.delay(20);							
                        }
                        logger.trace("Next city.");
                    } // Vehicle capacity is reached
                    int tmpint = ants[antCount].addPath(aG.anodes, currentNodeIndx, 0);
                    logger.trace("tmpint = " + tmpint + " capacity = " + capacity);
                    capacity = capacity - tmpint;
                    if (AntColony.DIPSLAY_LEVEL > 2)
                    {
                        showEdge(currentNodeIndx, 0);

                        ShowPheromon.setLabelCurrentNode("Node: " + currentNodeIndx + " (0)");
                        ShowPheromon.setLabelCapacityLeft("Capacity left: " + capacity);
                        ShowPheromon.setLabelCurrentPathCost("Path cost: " + Def.df2(ants[antCount].getCost()));
                        ShowPheromon.setLabelNodesVisited("Cities Visited: " + (aG.anodes.numOfVisited() - 1) + " of " + (aG.anodes.size() - 1));
                        //Dbg.delay(30);
                    }

                    logger.trace("Next ant");
                } // All cities are visited. An Ant finished route.

                if ((ants[antBestIndx].getCost() > ants[antCount].getCost()) || (antCount == 0))
                {
                    antBestIndx = antCount;
                    if (AntColony.DIPSLAY_LEVEL > 0)
                    {
                        logger.trace("Showing best ant cost");
                        ShowPheromon.setLBestCostAnt("Best cost (Ant): " + Def.df2(ants[antBestIndx].getCost()) + " (" + antBestIndx + ")");
                        Dbg.delay(10);
                    }
                }

                if (AntColony.DIPSLAY_LEVEL > 1)
                {
                    MainFrame.statusBar.setText(" Ant " + (antCount + 1) + " of " + antNum + " finished its route. Cycle " + (cycle + 1) + " of " + aG.anodes.size() * AntColony.MAX_CYCLES_PARAM);
                    //Dbg.delay(50);
                    logger.trace("Redraw all egdes.");
                    aG.redrawAllEdges();
                    Dbg.delay(50);
                }

                // export COST TIME DISTANCE
                String resultOfCycle = ants[antCount].getCost() + " \t" + ants[antCount].cost.getDistance() + " \t" + ants[antCount].cost.getTime();
                try {
                	logger.debug(resultOfCycle);
                	out.write(resultOfCycle+"\n");
//                	out.newLine();
                } catch (IOException ex) {
                	java.util.logging.Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex) {
                	ex.printStackTrace();
                }  // export

            } // All ants are finished routes
            logger.debug("All ants finished routes.");

            // In the begining route 0 is the best one
            if (cycle == 0)
            {
                bestAnt = ants[antBestIndx];
                if (AntColony.DIPSLAY_LEVEL > 0)
                {
                    ShowPheromon.setLabelBestDist("Best cost: " + Def.df2(bestAnt.getCost()) + " (" + cycle + ")"
                        + " Distance: " + Def.df2(bestAnt.cost.getDistance()) + " Time: " + Def.df2(bestAnt.cost.getTime()));
                }
            }
            

            // Evaporation of all paths
            if (AntColony.DIPSLAY_LEVEL > 1)
            {
                MainFrame.statusBar.setText(" Evaporation ...");
            }
            if (!AntColony.LOCAL_UPDATE)
            {
                for (int i = 0; i < aG.anodes.size() - 1; i++)
                {
                    for (int j = i + 1; j < aG.anodes.size(); j++)
                    {
                        aG.anodes.getEdge(i, j).setPheromon(localUpdate(aG.anodes.getEdge(i, j).getPheromon()));
                    }
                }
            }

            // Is this best possible solution
            if (bestAnt.getCost() > ants[antBestIndx].getCost())
            {
                sameCyleces = 0;
                bestAnt = ants[antBestIndx];
                bestCycle = cycle;
                if (AntColony.DIPSLAY_LEVEL > 0)
                {
                	ShowPheromon.setLabelBestDist("Best cost: " + Def.df2(bestAnt.getCost()) + " (" + cycle + ")"
                            + " Distance: " + Def.df2(bestAnt.cost.getDistance()) + " Time: " + Def.df2(bestAnt.cost.getTime()));
                }
            }

            // Add pheromon for the best path
            for (AEdge e : ants[antBestIndx].path)
            {
//				Dbg.prn((t++)+" "+antBestIndx+":"+ants[antBestIndx].getDist()+" "+bestAnt.getDist()+" r: "+(bestAnt.getDist()/(ants[antBestIndx].getDist()*1.0))+" "+e.getPheromon());
                double newPh = globalUpdate(e.getPheromon(), ants[antBestIndx].getCost());
                e.setPheromon(newPh);
                aG.redrawEdge(e);
//				Dbg.delay(1000);
            }

            if (AntColony.DIPSLAY_LEVEL > 0)
            {
            	if (AntColony.DIPSLAY_LEVEL > 1)
            	{
            		ShowPheromon.refreshTable();
            	}
            	Dbg.delay(100);
            }

            if (sameCyleces > aG.anodes.size() * AntColony.MAX_CYCLES_PARAM + 50)
            {
            	int c = aG.anodes.size() * AntColony.MAX_CYCLES_PARAM;
            	cycle = c;
            }
            sameCyleces++;

        }
        
        // close export file
        try
        {
        	out.close();
        	logger.debug("Cost, distance and time exported to file: "+fileName);
        } catch (IOException ex)
        {
        	java.util.logging.Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        // Optimization finished. Redraw the best solution in GREEN
        if (AntColony.DIPSLAY_LEVEL > 0)
        {
            for (AEdge e : bestAnt.path)
            {
                showEdge(e, Color.GREEN);
                logger.debug(e.getToolTipString());
                MainFrame.jButtonStart.setText("Start");
                MainFrame.jButtonStart.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/objects_017.gif")));
            }
        }

        AFile.savePath(bestAnt, aG.anodes.size(), bestCycle, startTime, getNow(), getDuration(startTime), aG.anodes.get(0).getName());
        if (AntColony.DIPSLAY_LEVEL == 0)
        {
            Dbg.prn("; " + Def.df2(bestAnt.getCost()) + "; " + Def.df0(bestCycle) + "; " + Def.df0(aG.anodes.size() * AntColony.MAX_CYCLES_PARAM) + "; " + getDuration(startTime));
        } else
        {
            MainFrame.statusBar.setText(" Calculation finished.");
            MainFrame.jButtonStart.setText("Start");
            //Dbg.prn("ending. ");
            Dbg.delay(50);
        }
        finished = true;
    }

    private int nextBestNode(int crnt, int capacity)
    {
        int best = 0;
        double best_score = 0;
        Random generator = new Random();
        for (int i = 1; i < aG.anodes.size(); i++)
        {
            ANode aN = aG.anodes.get(i);
            if (!aN.isVisited() && (aN.getDemand() <= capacity))
            {
                // izracunaj tau ni proizvod iz putanje crnt-og i i-tog Node-a
                double rnd = generator.nextDouble();
                double new_score = tauNi(aG.anodes.getEdge(crnt, i).getPheromon(), aG.anodes.get(crnt).getDistance2Node(aN), rnd);

//				Dbg.prn(" ["+crnt+"]->["+i+"] rnd:"+rnd+" val:"+new_score);
                if (new_score > best_score)
                {
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

    /**
     * @return Returns the finished.
     */
    public boolean isFinished()
    {
        return finished;
    }

    /**
     * @param finished The finished to set.
     */
    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }

    private String getNow()
    {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        java.text.SimpleDateFormat sdf
            = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
//	    System.out.println("Now : " + sdf.format(cal.getTime()));
        return sdf.format(cal.getTime());
    }

    private long getDuration(String startTime)
    {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
//	    System.out.println("Now : " + sdf.format(cal.getTime()));
        Date startDate = null;
        try
        {
            startDate = sdf.parse(startTime);
        } catch (ParseException e)
        {
            System.out.println("Invalid Date Parser Exception ");
            e.printStackTrace();
        }
//	   System.out.println((cal.getTime().getTime()-startDate.getTime())/1000);
        return (cal.getTime().getTime() - startDate.getTime()) / 1000;
    }

    private void showEdge(AEdge e, Color color)
    {
        if (e != null)
        {
            try
            {
                float pheromonWidth = (float) (e.getPheromon() / AntColony.START_PHEROMON);
                if (pheromonWidth > AntColony.MAX_PHEROMON_PIXEL)
                {
                    pheromonWidth = AntColony.MAX_PHEROMON_PIXEL;
                }
                GraphConstants.setLineColor(e.getAttributes(), color);
                GraphConstants.setLineWidth(e.getAttributes(), pheromonWidth);
                //check
                if (AntColony.DIPSLAY_LEVEL > 2)
                {
                    Dbg.delay(15);
                    aG.getGraphLayoutCache().setVisible(e, true);
                    Dbg.delay(15);
                }
            } catch (NullPointerException obj)
            {
                logger.error("NullPointerException: " + obj);
                Dbg.delay(1000);
            }
        } else
        {
            logger.error(" = null");
        }
        //e.showAEdge(color);
    }

    private void showEdge(int currIndx, int nextIndx)
    {
        logger.trace("showEdge " + currIndx + "->" + nextIndx + " ");
        try
        {
            AEdge e = aG.anodes.getEdge(currIndx, nextIndx);
            if (e != null)
            {
                showEdge(e, Color.BLUE);
            }
        } catch (NullPointerException e)
        {
            Dbg.prnl("showEdge error:" + e);
        }
    }

    public static double tauNi(double tau, double distance, double rnd)
    {
//		Dbg.prnl("tauNi   "+tau+" "+distance);
        if (rnd >= AntColony.RO)
        {
            return rnd * Math.pow(tau, AntColony.ALPHA) * Math.pow(1.0 / distance, AntColony.BETA);
        } else
        {
            return Math.pow(tau, AntColony.ALPHA) * Math.pow(1.0 / distance, AntColony.BETA);
        }
    }

    public static double localUpdate(double oldPh)
    {
        double newPh;
        newPh = (1.0f - AntColony.RO) * oldPh + AntColony.RO * AntColony.START_PHEROMON;
        return newPh;
    }

    public static double globalUpdate(double oldPh, double delta)
    {
//		return oldPh + AntColony.RO / delta;
        double newPh = oldPh + 1.0 / delta;
        return (newPh);
    }

}
