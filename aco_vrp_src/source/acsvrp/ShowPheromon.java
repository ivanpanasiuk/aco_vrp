package acsvrp;

import acsvrp.tools.Def;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ShowPheromon extends javax.swing.JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    static final Logger logger = Logger.getLogger(ShowPheromon.class);
    
    static private ANodes nodes;
    
    
    
    
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JSplitPane jSplitPane;
    private static JLabel lblAnt;
    private static JLabel lblBestCost;
    private static JLabel lblCapacity;
    private static JLabel lblCities;
    private static JLabel lblCostAnt;
    private static JLabel lblCycle;
    private JLabel lblFormat;
    private static JLabel lblNode;
    private static JLabel lblPathCost;
    private static JTable tPheromon;

    public ShowPheromon(ANodes nodes)
    {
        super();
        ShowPheromon.nodes = nodes;
        //initGUI();
        initComponents();
    }
    
    private void initComponents()
    {
        GridBagConstraints gridBagConstraints;

        jPanel1 = new JPanel();
        jSplitPane = new JSplitPane();
        jPanel2 = new JPanel();
        lblNode = new JLabel();
        lblAnt = new JLabel();
        lblCycle = new JLabel();
        lblFormat = new JLabel();
        lblCapacity = new JLabel();
        lblPathCost = new JLabel();
        lblCostAnt = new JLabel();
        lblBestCost = new JLabel();
        lblCities = new JLabel();
        jScrollPane1 = new JScrollPane();
        tPheromon = new JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(500, 250));
        setPreferredSize(new Dimension(800, 350));
        setTitle("Calculating details");
        setLocationRelativeTo(null);
        
        setBackground(Color.DARK_GRAY);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt)
            {
                System.out.println("this.keyPressed, event=" + evt);
                if (evt.getKeyCode() == 80)
                {
                    ShowPheromon.this.setVisible(false);
                }

            }
        });

        jPanel1.setPreferredSize(new Dimension(800, 250));
        jPanel1.setLayout(new GridBagLayout());
        jPanel1.setBackground(Color.DARK_GRAY);

        jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPane.setMinimumSize(new Dimension(500, 250));
        jSplitPane.setPreferredSize(new Dimension(800, 250));

        jPanel2.setMinimumSize(new Dimension(500, 150));
        jPanel2.setPreferredSize(new Dimension(800, 150));
        GridBagLayout jPanel2Layout = new GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel2Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel2.setLayout(jPanel2Layout);
        jPanel2.setBackground(Color.LIGHT_GRAY);

        lblNode.setHorizontalAlignment(SwingConstants.TRAILING);
        lblNode.setText("Node:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(25, -85, 5, 5);
        jPanel2.add(lblNode, gridBagConstraints);

        lblAnt.setHorizontalAlignment(SwingConstants.TRAILING);
        lblAnt.setText("Ant:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(5, -85, 5, 5);
        jPanel2.add(lblAnt, gridBagConstraints);

        lblCycle.setText("Cycle:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(5, -85, 5, 5);
        jPanel2.add(lblCycle, gridBagConstraints);

        lblFormat.setHorizontalAlignment(SwingConstants.CENTER);
        lblFormat.setText("Pheromon value format: [value] * 1000");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 25, 25, 25);
        jPanel2.add(lblFormat, gridBagConstraints);

        lblCapacity.setText("Capacity left:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(25, 25, 5, 5);
        jPanel2.add(lblCapacity, gridBagConstraints);

        lblPathCost.setText("Path cost:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(25, 65, 5, 45);
        jPanel2.add(lblPathCost, gridBagConstraints);

        lblCostAnt.setText("Best cost (Ant):");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 45);
        jPanel2.add(lblCostAnt, gridBagConstraints);

        lblBestCost.setText("Best cost:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 45);
        jPanel2.add(lblBestCost, gridBagConstraints);

        lblCities.setText("Cities visited:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(25, 65, 5, -50);
        jPanel2.add(lblCities, gridBagConstraints);

        jSplitPane.setTopComponent(jPanel2);

        jScrollPane1.setMinimumSize(new Dimension(600, 100));
        jScrollPane1.setPreferredSize(new Dimension(800, 100));

        tPheromon.setModel(new DefaultTableModel(
            new Double[][]
            {
                {
                    0.1, 0.1
                },
                {
                    0.1, 0.1
                }
            },
            new String[]
            {
                "1", "2"
            }));
        tPheromon.setMinimumSize(new Dimension(600, 100));
        tPheromon.setPreferredSize(new Dimension(800, 100));
        tPheromon.setBackground(Color.LIGHT_GRAY);
        jScrollPane1.setViewportView(tPheromon);
        jScrollPane1.setBackground(Color.LIGHT_GRAY);

        jSplitPane.setBottomComponent(jScrollPane1);
        jSplitPane.setBackground(Color.DARK_GRAY);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel1.add(jSplitPane, gridBagConstraints);

        getContentPane().add(jPanel1, BorderLayout.CENTER);

        pack();
        setPheromon();
    }

    /*
    private void initGUI()
    {
        try
        {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            this.setTitle("Calculating details");
            this.setLocationByPlatform(true);
            this.setPreferredSize(new Dimension(730, 500));
            this.setLocation(new java.awt.Point(500, 400));
            this.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent evt)
                {
                    System.out.println("this.keyPressed, event=" + evt);
                    if (evt.getKeyCode() == 80)
                    {
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
                                new Double[][]
                                {
                                    {
                                        0.1, 0.1
                                    },
                                    {
                                        0.1, 0.1
                                    }
                                },
                                new String[]
                                {
                                    "1", "2"
                                });
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
                        public void mouseClicked(MouseEvent evt)
                        {
                            System.out.println("jDesktopPane1.mouseClicked, event=" + evt);
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

                    //);
                }
            }
            setPheromon();
            pack();
            this.setSize(633, 300);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    */

    /**
     * @param currentNode The lblNode to set.
     */
    public static void setLabelNode(String currentNode)
    {
        lblNode.setText(currentNode);
    }

    /**
     * @param pathCost The lblPathCost to set.
     */
    public static void setLabelPathCost(String pathCost)
    {
        lblPathCost.setText(pathCost);
    }

    /**
     * @param capacity The lblCapacity to set.
     */
    public static void setLabelCapacity(String capacity)
    {
        lblCapacity.setText(capacity);
    }
    
    /**
     * @param costAnt The lblCostAnt to set.
     */
    public static void setLabelCostAnt(String costAnt)
    {
        lblCostAnt.setText(costAnt);
    }

    /**
     * @param cycle The lblCycle to set.
     */
    public static void setLabelCycle(String cycle)
    {
        lblCycle.setText(cycle);
    }

    /**
     * @param currentAnt The lblAnt to set.
     */
    public static void setLabelAnt(String currentAnt)
    {
        lblAnt.setText(currentAnt);
    }
    
    /**
     * @param bestCost The lblBestCost to set.
     */
    public static void setLabelBestCost(String bestCost)
    {
        lblBestCost.setText(bestCost);
    }

    /**
     * @param nodesVisited The lblCities to set.
     */
    public static void setLabelCities(String nodesVisited)
    {
        lblCities.setText(nodesVisited);
    }

    public static void setPheromon()
    {
        String[][] data = new String[nodes.size()][(nodes.size() + 1)];
        String[] head = new String[(nodes.size() + 1)];
        data = phValues();
        head[0] = "";
        for (int i = 0; i < nodes.size(); i++)
        {
            head[i + 1] = nodes.get(i).toString();
            data[i][0] = nodes.get(i).toString();
        }
        TableModel tPheromonModel = new DefaultTableModel(data, head);
        tPheromon.setModel(tPheromonModel);
    }

    public static void refreshTableValueAt(int i, int j)
    {
        double ph = nodes.getEdge(i, j).getPheromon() * 1000;
        //logger.info(""+ i + "," + j + " " + ph);
        tPheromon.setValueAt(Def.df4(ph), i, j + 1);
//		tPheromon.setValueAt(Math.round(ph*1000d)/1000d,i,j+1);
        //Dbg.delay(100);			
    }

    public static void refreshTable()
    {
        for (int i = 0; i < nodes.size() - 1; i++)
        {
            for (int j = i + 1; j < nodes.size(); j++)
            {
                //TODO tPheromon.setValueAt(nodes.getEdge(i,j).getPheromon(),i,j+1);
                refreshTableValueAt(i, j);
            }
        }
    }

    private static String[][] phValues()
    {
        String[][] data = new String[nodes.size()][nodes.size() + 1];
        //		Dbg.prnl();
        for (int i = 0; i < nodes.size() - 1; i++)
        {
            for (int j = i + 1; j < nodes.size(); j++)
            {
                //				Dbg.prn(i+","+j+":"+nodes.getEdge(i,j).getPheromon()+" ")
                data[i][j + 1] = Def.df4(nodes.getEdge(i, j).getPheromon() * 1000);
                //				data[i][j+1] = (i+","+j);
            }
            //			Dbg.prnl();
        }
        return data;
    }

}
