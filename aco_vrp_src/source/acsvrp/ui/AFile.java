package acsvrp.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

//import acsvrp.AEdge;
import acsvrp.AEdge;
import acsvrp.ANode;
import acsvrp.ANodes;
import acsvrp.Ant;
//import acsvrp.Ant;
import acsvrp.AntColony;
import acsvrp.tools.AMath;
import acsvrp.tools.CalendarTime;
import acsvrp.tools.Dbg;

public class AFile {

	static final Logger logger = Logger.getLogger(AFile.class);

	static public ANodes loadNodes(String fileName)
	{
		boolean isFile = !(fileName.contains("tp:"));
		ANodes anodes = new ANodes();
		boolean exists = true;
		URL urlVrp = null;
		if (isFile)
		{
			exists = (new File(fileName)).exists();
		} else
		{
			try
			{
				urlVrp = new URL(fileName);
				try
				{
					int conLen = urlVrp.openConnection().getContentLength();
					System.out.print(urlVrp.getFile());
					exists = (conLen > 0);
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (MalformedURLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (exists)
		{
			// File exists
			try
			{
				BufferedReader in;
				if (isFile)
				{
					in = new BufferedReader(new FileReader(fileName));
				} else
				{
					in = new BufferedReader(new InputStreamReader(urlVrp.openStream()));
				}
				String str = in.readLine();
				int rowNotFound = 0;
				while (!(str.contains("CAPACITY")))
				{
					System.out.println("str: " + str);
					str = in.readLine();
					rowNotFound++;
					if ((str == null) || (rowNotFound > 30))
					{ // if first 20 rows do not contains "CAPACITY"
						String message = "URL '" + fileName + "' does not exist or do not contain correct VRP file!";
						JOptionPane.showMessageDialog(null, message, "Opening URL", JOptionPane.ERROR_MESSAGE);
						return null;
					}
				}
				StringTokenizer st = new StringTokenizer(str, " ");
				String token;
				token = st.nextToken();
				token = st.nextToken();
				token = st.nextToken();
				//            	Dbg.prnl("<"+token+">");
				if (token != "")
				{
					anodes.capacity = Integer.parseInt(token);
					//            		Dbg.prnl("Capasity: "+anodes.capacity);
					//            		Dbg.delay(5000);
				}
				//        		Dbg.delay(5000);
				while (!(str = in.readLine()).contains("NODE_COORD_SECTION"))
				{
					//            		Dbg.prnl(str+"sss");
					//            		Dbg.delay(1000);
				}
				logger.debug("AFile.java: Loading cooordinates ...  ");
				// Central depot
				str = in.readLine();
				st = new StringTokenizer(str, " ");
				String name = st.nextToken();
				anodes.add(new ANode(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), name));
				// TODO new aedge (n1,n2,,len, , time)
				// clients (cities)
				while (!(str = in.readLine()).contains("DEMAND_SECTION"))
				{
					st = new StringTokenizer(str, " ");
					name = st.nextToken();
					int xC = Integer.parseInt(st.nextToken());
					int yC = Integer.parseInt(st.nextToken());
					ANode newNode = new ANode(xC, yC, name);
					//	            	Dbg.prnl(newNode);
					//	            	Dbg.delay(1000);
					anodes.add(newNode);
				}
				//	            Dbg.prnl();
				// Demand section
				int i = 0;
				while (!(str = in.readLine()).contains("DEPOT_SECTION"))
				{
					st = new StringTokenizer(str, " ");
					name = st.nextToken();
					int demand = Integer.parseInt(st.nextToken());
					if (anodes.get(i).toString().equals(name))
					{
						anodes.get(i).setDemand(demand);
					}
					//	            	Dbg.prnl("[" + anodes.get(i).toString() + "] [" + name + "] d = " + demand + "(" + anodes.get(i).getDemand()+")");
					//	            	Dbg.delay(2000);
					i++;
				}

				// Load time section for all edges
				logger.debug("Loading time section...");
				while ((str = in.readLine()) != null)
				{
					if (!str.contains("TIME_SECTION"))
					{
						if (str.length()>0) {
							logger.debug(str);
							st = new StringTokenizer(str, " ");
							try {
								String sourceNode = st.nextToken();
								String destNode = st.nextToken();
								String time = st.nextToken();
								anodes.getEdge(sourceNode, destNode).cost.setTime(Double.parseDouble(time));
							} catch (Exception e) {
								// TODO: handle exception
								String message = "Line '" + str + "' could no be parsed!";
								JOptionPane.showMessageDialog(null, message, "Parsing time section", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}

				in.close();
				logger.debug("File loading finished");
				//TODO AntColony.START_PHEROMON *= anodes.size();
				logger.trace("AFile.java: Found " + anodes.size() + " Nodes, " + anodes.size()
						* (anodes.size() + 1) / 2 + " Edges.");
				//	            Dbg.delay(2000);
				return anodes;
			} catch (IOException e)
			{
				return null;
			}
		} else
		{
			// File or directory does not exist
			String message = "File '" + fileName + "' does not exist!";
			Dbg.prnl(message);
			JOptionPane.showMessageDialog(null, message, "Opening file", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	static public boolean saveNodes(ANodes nodes, String fileName)
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			Dbg.prnl("Snimam kooordinate u fajl..." + nodes.size());
			// klijenti (gradovi)
			for (int i = 0; i < nodes.size(); i++)
			{
				Dbg.prnl(nodes.get(i).getX() + "," + nodes.get(i).getY());
				out.write(nodes.get(i).getX() + "," + nodes.get(i).getY());
				out.newLine();
			}
			out.close();
			return true;
		} catch (IOException e)
		{
			return false;
		}
	}

	static public boolean savePath(Ant bestAnt, int numCities, int bestCycle, String startTime, String endTime, long durationMin, String startingNode)
	{
		try
		{            
			String fileName = "r" + numCities + "_" + AMath.round(bestAnt.getCost(), 2) + "_c" + bestCycle + "_" + CalendarTime.getFormatedTime() + ".txt";
			File file = new File(AntColony.EXPORT_FOLDER + fileName);
//			File file = new File(fileName);
            file.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			// klijenti (gradovi)
			//        	String firstOne = "n0";
			//        	String firstNode = bestAnt.getPath().get(0).getTarget().toString();
			//        	Dbg.prnl("<"+firstNode+">");
			out.write("Number of cities: " + numCities);
			out.newLine();

			int counter = 1;
			String newRoute = "Route #";

			String previous = startingNode;

			for (AEdge e : bestAnt.getPath())
			{
				/*
                 if (firstOne == e.getSource().toString()) {
                 //					Dbg.prnl("S: "+e.getSource() + " E:"+ e.getTarget() +" pheromon: "+e.toString());
                 //					out.write(e.getSource().toString()+" ("+e.len+") ");
                 out.write(e.getSource().toString()+" ");
                 if (firstNode == e.getTarget().toString()){
                 out.newLine();
                 //						Dbg.prnl();
                 }
                 firstOne = e.getTarget().toString();
                 } else {
                 //					Dbg.prnl("xS: "+e.getTarget() + " E:"+ e.getSource() +" pheromon: "+e.toString());
                 //					out.write(e.getTarget().toString()+" ("+e.len+") ");
                 out.write(e.getTarget().toString()+" ");
                 //					out.newLine();
                 firstOne = e.getSource().toString();
                 }
				 */
				if(e.getEnd().equals(startingNode))
				{
					if(previous.equals(startingNode))
					{
						out.write(newRoute + counter + ": " + previous + " " + e.getStart());
						previous = e.getStart();
						++counter;
					}
					else
					{
						if(previous.equals(e.getStart()))
						{
							out.write(" " + startingNode);
							previous = startingNode;
							out.newLine();
						}
					}
				}
				else
				{
					if(previous.equals(e.getEnd()))
					{
						out.write(" " + e.getStart());
						previous = e.getStart();
					}
					else
					{
						out.write(" " + e.getEnd());
						previous = e.getEnd();
					}
				}
			}
			out.newLine();

			out.write("COST: " + bestAnt.getCost());
			out.newLine();
			out.newLine();
			out.write("PARAM:");
			out.write(" ALPHA="+AntColony.ALPHA);
			out.write(" BETA=" + AntColony.BETA);
			out.write(" RO=" + AntColony.RO);
			//        	out.write(" E="+AntColony.E);
			out.write(" | MAX_CYCLE_PARAM=" + AntColony.MAX_CYCLES_PARAM);
			out.write(" SPEED=" + AntColony.SPEED);
			out.write(" DISP_LEVEL=" + AntColony.DIPSLAY_LEVEL);
			out.newLine();
			out.newLine();
			out.write("Started: " + startTime + ", Ended: " + endTime + " Duration: " + durationMin + " sec");
			out.close();
			return true;
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
