package acsvrp;

import acsvrp.tools.Def;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ShowPheromon extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(ShowPheromon.class);

	private static JLabel lblPheromonValue;

	private JSplitPane jSplitPane1;
	private static JLabel lPathCapacity;
	private static JTable tPheromon;
	private static JLabel lNodesVisited;
	private static JLabel lCycle;
	private static JLabel lCurrentAnt;
	private static JLabel lBestDist;
	private static JLabel lBestDistAnt;
	private static JLabel lCurrentPathDist;
	private static JLabel lcurrentNode;
	private JDesktopPane jDesktopPane1;
	private JScrollPane jScrollPane2;
	static private ANodes nodes;

	public ShowPheromon(ANodes nodes) {
		super();
		ShowPheromon.nodes = nodes;
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Calculating details");
			this.setLocationByPlatform(true);
			this.setPreferredSize(new Dimension(730, 500));
			this.setLocation(new java.awt.Point(500, 400));
			this.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {
					System.out.println("this.keyPressed, event=" + evt);
					if (evt.getKeyCode() == 80) {
						ShowPheromon.this.setVisible(false);
					}

				}
			});
			{
				jSplitPane1 = new JSplitPane();
				getContentPane().add(jSplitPane1, BorderLayout.CENTER);
				jSplitPane1.setPreferredSize(new java.awt.Dimension(724, 320)); //was (724, 280)
				jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
				{
					jScrollPane2 = new JScrollPane();
					jSplitPane1.add(jScrollPane2, JSplitPane.BOTTOM);
					jScrollPane2.setPreferredSize(new java.awt.Dimension(722, 170)); //was (722, 170)
					{
						TableModel tPheromonModel = new DefaultTableModel(
								new Double[][] { { 0.1,  0.1 },
										{  0.1,  0.1 } },
										new String[] { "1", "2" });		
						tPheromon = new JTable();
						jScrollPane2.setViewportView(tPheromon);
						tPheromon.setModel(tPheromonModel);
					}
				}
				{
					jDesktopPane1 = new JDesktopPane();
					jSplitPane1.add(jDesktopPane1, JSplitPane.TOP);
					jDesktopPane1.setPreferredSize(new java.awt.Dimension(602, 90)); //was (602, 70)
					jDesktopPane1.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							System.out.println("jDesktopPane1.mouseClicked, event="+ evt);
							setPheromon();
						}
					});
					{
						lcurrentNode = new JLabel();
						jDesktopPane1.add(lcurrentNode);
						lcurrentNode.setText("Node:");
						lcurrentNode.setBounds(7, 7, 91, 21);
						lcurrentNode.setToolTipText("Current");
					}
					{
						lPathCapacity = new JLabel();
						jDesktopPane1.add(lPathCapacity);
						lPathCapacity.setText("Path count: ");
						lPathCapacity.setBounds(112, 7, 126, 21);
					}
					{
						lCurrentPathDist = new JLabel();
						jDesktopPane1.add(lCurrentPathDist);
						lCurrentPathDist.setText("Path distance: ");
						lCurrentPathDist.setBounds(245, 7, 140, 21);
					}
					{
						lBestDistAnt = new JLabel();
						jDesktopPane1.add(lBestDistAnt);
						lBestDistAnt.setText("Best Ant distance:");
						lBestDistAnt.setBounds(245, 28, 210, 21);
					}
					{
						lCurrentAnt = new JLabel();
						jDesktopPane1.add(lCurrentAnt);
						lCurrentAnt.setText("Ant:");
						lCurrentAnt.setBounds(7, 28, 147, 21);
					}
					{
						lBestDist = new JLabel();
						jDesktopPane1.add(lBestDist);
						lBestDist.setText("Best distance:");
						lBestDist.setBounds(245, 49, 210, 21);
					}
					{
						lCycle = new JLabel();
						jDesktopPane1.add(lCycle);
						lCycle.setText("Cycle:");
						lCycle.setBounds(7, 49, 189, 21);
					}
					{
						lNodesVisited = new JLabel();
						jDesktopPane1.add(lNodesVisited);
						lNodesVisited.setText("Cities Visited:");
						lNodesVisited.setBounds(476, 7, 140, 21);
					}

					lblPheromonValue = new JLabel("Pheromon values x 1000 :");
					jDesktopPane1.add(lblPheromonValue);
					lblPheromonValue.setBounds(7, 75, 400, 15);

					//);
				}
			}
			setPheromon();
			pack();
			this.setSize(633, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param lcurrentNode The lcurrentNode to set.
	 */
	public static void setLcurrentNode(String currentNode) {
		lcurrentNode.setText(currentNode);
	}

	/**
	 * @param currentPathDist The lCurrentPathDist to set.
	 */
	public static void setLCurrentPathDist(String currentPathDist) {
		lCurrentPathDist.setText(currentPathDist);
	}

	/**
	 * @param pathCount The lPathCount to set.
	 */
	public static void setLPathCapacity(String pathCount) {
		lPathCapacity.setText(pathCount);
	}

	public static void setLBestDistAnt(String bestDistAnt) {
		lBestDistAnt.setText(bestDistAnt);
	}

	public static void setLCycle(String cycle) {
		lCycle.setText(cycle);
	}

	public static void setLCurrentAnt(String currentAnt) {
		lCurrentAnt.setText(currentAnt);
	}

	public static void setLBestDist(String bestDist) {
		lBestDist.setText(bestDist);
	}

	public static void setLNodesVisited(String nodesVisited) {
		lNodesVisited.setText(nodesVisited);
	}

	public static void setPheromon() {
		String[][] data = new String[nodes.size()][(nodes.size()+1)];
		String[] head = new String[(nodes.size()+1)];
		data = phValues();
		head[0]="";
		for (int i=0; i<nodes.size(); i++) {
			head[i+1]=nodes.get(i).toString();
			data[i][0]=nodes.get(i).toString();
		}
		TableModel tPheromonModel = new DefaultTableModel(data, head);
		tPheromon.setModel(tPheromonModel);
	}

	public static void refreshTableValueAt(int i, int j) {
		double ph = nodes.getEdge(i,j).getPheromon() * 1000;
		//logger.info(""+ i + "," + j + " " + ph);
		tPheromon.setValueAt(Def.df4(ph),i,j+1);
//		tPheromon.setValueAt(Math.round(ph*1000d)/1000d,i,j+1);
		//Dbg.delay(100);			
	}

	public static void refreshTable() {
		for (int i=0; i<nodes.size()-1; i++) {
			for (int j=i+1; j<nodes.size(); j++) {
				//TODO tPheromon.setValueAt(nodes.getEdge(i,j).getPheromon(),i,j+1);
				refreshTableValueAt(i,j);
			}
		}
	}

	private static String[][] phValues(){
		String[][] data = new String[nodes.size()][nodes.size()+1];
		//		Dbg.prnl();
		for (int i=0; i<nodes.size()-1; i++) {
			for (int j=i+1; j<nodes.size(); j++) {
				//				Dbg.prn(i+","+j+":"+nodes.getEdge(i,j).getPheromon()+" ")
				data[i][j+1] = Def.df4(nodes.getEdge(i,j).getPheromon()*1000);
				//				data[i][j+1] = (i+","+j);
			}
			//			Dbg.prnl();
		}
		return data;
	}

}
