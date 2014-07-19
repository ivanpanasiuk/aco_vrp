package acsvrp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

import acsvrp.ui.*;
import acsvrp.tools.*;
import java.awt.Dimension;


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
public class MainFrame extends JFrame {

	{
		//Set Look & Feel
		try {
//			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
			MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
			javax.swing.UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 2269971701250845501L;

	static final Logger logger = Logger.getLogger(MainFrame.class);

	static public JLabel statusBar;

	AGraph agraph;
	ShowPheromon showPheromon;
	Parameters parameters;
	private boolean startACO = false;

	private String urlPrefix = " http://www.poredi.com/def/";

	private JMenuItem jMenuItemOpen;
	private JMenuItem jMenuItemSave;
	private JMenuItem jMenuItemAbout;
	private JMenuItem jMenuItemHelp;
	private JMenu jMenuHelp;
	private JMenuItem jMenuItemStart;
	private JMenuItem jMenuItemInitPara;
	private JMenu jMenuTools;
	private JCheckBoxMenuItem jCheckBoxMenuItemShowPheromon;
	private JMenu jMenuView;
	private JMenuItem jMenuItemExit;
	private JSeparator jSeparatorFile;
	private JMenuItem jMenuItemSaveAs;
	private JMenuItem jMenuItemNew;
	private JMenu jMenu1;
	private JMenuBar jMenuBar1;
	private JScrollPane jScrollPane;
	private JButton jButtonOpen;
	static public 	JButton jButtonStart;
	private JButton jButtonAdd;
	private JButton jButtonSave;
	private JMenuItem jMenuItemLoadUrl;
	private JMenuItem jMenuItemAn53k7;
	private JMenuItem jMenuItemPn16k8;
	private JMenuItem jMenuItemAn37k6;
	private JMenu jMenuLoadUrl;
	private JButton jButtonNew;
	private JToolBar jToolBar1;

	public MainFrame() {

		//System.setProperty("sun.java2d.d3d", "false");
		PropertyConfigurator.configure("log4j.properties");

		this.setSize(720, 575);
		this.setTitle("Vechicle routing system using Ant Colony System optimization");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		{
			GraphModel model = new DefaultGraphModel();
			GraphLayoutCache view = new GraphLayoutCache(model,new DefaultCellViewFactory(),true);
			agraph = new AGraph(model, view);
			//agraph = new AGraph();
			jScrollPane = new JScrollPane(agraph);
			getContentPane().add(jScrollPane, BorderLayout.CENTER);
			jScrollPane.setPreferredSize(new java.awt.Dimension(712, 447));
			jScrollPane.setSize(712, 468);
			{
				BorderLayout agraphLayout = new BorderLayout();
				agraph.setLayout(agraphLayout);
			}
		}
		statusBar = new JLabel("Ready");
		add(statusBar, BorderLayout.SOUTH);
		statusBar.setText(" Ctrl-P for info about pheromon");

		{
			jToolBar1 = new JToolBar();
			getContentPane().add(jToolBar1, BorderLayout.NORTH);
			jToolBar1.setFloatable(false);
			jToolBar1.setOpaque(false);
			{
				jButtonNew = new JButton();
				BorderLayout jButtonNewLayout = new BorderLayout();
				jButtonNew.setLayout(jButtonNewLayout);
				jToolBar1.add(jButtonNew);
				jButtonNew.setToolTipText("Open new file");
				jButtonNew.setHorizontalAlignment(SwingConstants.LEFT);
				jButtonNew.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/Application-edit.gif")));
				jButtonNew.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						agraph.removeAll();
						agraph.validate();
					}
				});
			}
			{
				jButtonOpen = new JButton();
				BorderLayout jButtonOpenLayout = new BorderLayout();
				jButtonOpen.setLayout(jButtonOpenLayout);
				jToolBar1.add(jButtonOpen);
				jButtonOpen.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/Folder.gif")));
				jButtonOpen.setToolTipText("Open file");
				jButtonOpen.setHorizontalAlignment(SwingConstants.LEFT);
				jButtonOpen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						OpenActionPerformed(evt);
					}
				});
			}
			{
				jButtonSave = new JButton();
				BorderLayout jButtonSaveLayout = new BorderLayout();
				jButtonSave.setLayout(jButtonSaveLayout);
				jToolBar1.add(jButtonSave);
				jButtonSave.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/Save.gif")));
				jButtonSave.setHorizontalAlignment(SwingConstants.LEFT);
                jButtonSave.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        //TODO save
                    }
                });
                
			}
			{
				jToolBar1.addSeparator();
			}			
			{
				jButtonAdd = new JButton();
				BorderLayout jButtonAddLayout = new BorderLayout();
				jButtonAdd.setLayout(jButtonAddLayout);
				jToolBar1.add(jButtonAdd);
				jButtonAdd.setToolTipText("Add node");
				jButtonAdd.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/Doc-Add.gif")));
				jButtonAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButtonAddActionPerformed(evt);
					}
				});
			}
			{
				jToolBar1.addSeparator();
			}						
			{
				jButtonStart = new JButton();
				BorderLayout jButtonStartLayout = new BorderLayout();
				jButtonStart.setLayout(jButtonStartLayout);
				jToolBar1.add(jButtonStart);
				jButtonStart.setText("Start");
				jButtonStart.setToolTipText("Start process");
				jButtonStart.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/objects_017.gif")));
				jButtonStart.setPreferredSize(new java.awt.Dimension(80, 28));
				jButtonStart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						StartActionPerformed(evt);
					}
				});
			}
			{
				jToolBar1.addSeparator();
			}	
			{
				jToolBar1.add(new JLabel("Cost type:  "));
				String[] costTypes = { "Destination", "Time"};

				JComboBox costTypeList = new JComboBox(costTypes);
//				BorderLayout jCostListLayout = new BorderLayout();
//				costTypeList.setLayout(jCostListLayout);
				costTypeList.setSelectedIndex(0);
                costTypeList.setMaximumSize(new Dimension(120, 30));
				//costTypeList.setPreferredSize(new java.awt.Dimension(80, 28));
				costTypeList.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				jToolBar1.add(costTypeList);
			}
			{
//				jToolBar1.addSeparator();
//				jToolBar1.add(new JLabel(". "));
			}	
		}		

		this.setLocation(new java.awt.Point(40, 40));
		{
			jMenuBar1 = new JMenuBar();
			setJMenuBar(jMenuBar1);
			{
				jMenu1 = new JMenu();
				jMenuBar1.add(jMenu1);
				jMenu1.setText("File");
				jMenu1.setMnemonic('F');
				{
					jMenuItemNew = new JMenuItem();
					jMenu1.add(jMenuItemNew);
					jMenuItemNew.setText("New");
				}
				{
					jMenuItemOpen = new JMenuItem();
					jMenu1.add(jMenuItemOpen);
					jMenuItemOpen.setText("Open file");
					jMenuItemOpen.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
					jMenuItemOpen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							OpenActionPerformed(evt);
						}
					});
				}
				{
					jMenuItemSave = new JMenuItem();
					jMenu1.add(jMenuItemSave);
					jMenuItemSave.setText("Save");
				}
				{
					jMenuItemSaveAs = new JMenuItem();
					jMenu1.add(jMenuItemSaveAs);
					jMenuItemSaveAs.setText("Save as");
				}
				{
					jMenuLoadUrl = new JMenu();
					jMenu1.add(jMenuLoadUrl);
					jMenuLoadUrl.setText("Load URL");
					{
						jMenuItemPn16k8 = new JMenuItem();
						jMenuLoadUrl.add(jMenuItemPn16k8);
						jMenuItemPn16k8.setText("P-n16-k8.vrp");
						jMenuItemPn16k8.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								AntColony.FILE_NAME = urlPrefix+jMenuItemPn16k8.getText();
								createAnodes(AntColony.FILE_NAME);
							}
						});
					}
					{
						jMenuItemAn37k6 = new JMenuItem();
						jMenuLoadUrl.add(jMenuItemAn37k6);
						jMenuItemAn37k6.setText("A-n37-k6.vrp");
						jMenuItemAn37k6.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								AntColony.FILE_NAME = urlPrefix+jMenuItemAn37k6.getText();
								createAnodes(AntColony.FILE_NAME);
							}
						});
					}
					{
						jMenuItemAn53k7 = new JMenuItem();
						jMenuLoadUrl.add(jMenuItemAn53k7);
						jMenuItemAn53k7.setText("A-n53-k7.vrp");
						jMenuItemAn53k7.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								AntColony.FILE_NAME = urlPrefix+jMenuItemAn53k7.getText();
								createAnodes(AntColony.FILE_NAME);
							}
						});
					}
					{
						jMenuItemLoadUrl = new JMenuItem();
						jMenuLoadUrl.add(jMenuItemLoadUrl);
						jMenuItemLoadUrl.setText("Load from URL...");
						jMenuItemLoadUrl.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								String s = "http://ipanasiuk.webs.com/def/P-n19-k2.vrp";
								s = (String)JOptionPane.showInputDialog(null,"Chose VRP file","Enter URL:",JOptionPane.PLAIN_MESSAGE, null,null,s);
								createAnodes(s);
							}
						});
					}
				}
				{
					jSeparatorFile = new JSeparator();
					jMenu1.add(jSeparatorFile);
				}
				{
					jMenuItemExit = new JMenuItem();
					jMenu1.add(jMenuItemExit);
					jMenuItemExit.setText("Exit");
					jMenuItemExit.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
					jMenuItemExit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							logger.debug("jMenuItemExit.actionPerformed, event="+evt);
							System.exit(0);
						}
					});
				}
			}
			{
				jMenuView = new JMenu();
				jMenuBar1.add(jMenuView);
				jMenuView.setText("View");
				jMenuView.setMnemonic('V');
				{
					jCheckBoxMenuItemShowPheromon = new JCheckBoxMenuItem();
					jMenuView.add(jCheckBoxMenuItemShowPheromon);
					jCheckBoxMenuItemShowPheromon.setText("Show Pheromone");
					jCheckBoxMenuItemShowPheromon.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
					jCheckBoxMenuItemShowPheromon
					.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (AntColony.DIPSLAY_LEVEL > 0) {
								if (!showPheromon.isVisible()) {
									showPheromon.setVisible(true);
								} else {
									showPheromon.setVisible(false);
								}
							}
						}
					});
				}
			}
			{
				jMenuTools = new JMenu();
				jMenuBar1.add(jMenuTools);
				jMenuTools.setText("Tools");
				jMenuView.setMnemonic('T');
				{
					jMenuItemInitPara = new JMenuItem();
					jMenuTools.add(jMenuItemInitPara);
					jMenuItemInitPara.setText("Init Parameters");
					jMenuItemInitPara.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
					jMenuItemInitPara.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							parameters.setVisible(true);
						}
					});
				}
				{
					jMenuItemStart = new JMenuItem();
					jMenuTools.add(jMenuItemStart);
					jMenuItemStart.setText("Start");
					jMenuItemStart.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
					jMenuItemStart.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							StartActionPerformed(evt);
						}
					});
				}
			}
			{
				jMenuHelp = new JMenu();
				jMenuBar1.add(jMenuHelp);
				jMenuHelp.setText("Help");
				jMenuView.setMnemonic('H');
				{
					jMenuItemHelp = new JMenuItem();
					jMenuHelp.add(jMenuItemHelp);
					jMenuItemHelp.setText("Help");
				}
				{
					jMenuItemAbout = new JMenuItem();
					jMenuHelp.add(jMenuItemAbout);
					jMenuItemAbout.setText("About");
				}
			}
		}
		startACO = AntColony.AUTO_START;

		this.setVisible(true);

		if (AntColony.FILE_NAME.replaceAll(" ", "") != "") { 
			if (startACO) {
				createAnodes(AntColony.FILE_NAME);	
			}
			else {
				String message = "Do you want to load file '"+AntColony.FILE_NAME+"'";
				int a = JOptionPane.showConfirmDialog(null, message, "Opening file", JOptionPane.YES_NO_OPTION);
				logger.info("YesNo: "+a);
				if (a==0) {
					//createAnodes(AntColony.FILE_NAME);
                    createAnodes("/home/milos/Development/ivan/ants/aco-vrp/aco_vrp_src/def/pro5.vrp");
				}
			}
		}


		while (true) {
			if (startACO) {
				logger.info("Started!");
				double best_found = Double.MAX_VALUE;

				/***********************************************************************
				 ***************************** OVO MENJAO *******************************
				 ************************************************************************/

//				int best_found_time = Integer.MAX_VALUE;

				// TESTING PURPOSE
				double minRo = AntColony.RO; 		double maxRo = AntColony.RO;
				double minBeta = AntColony.BETA; 	double maxBeta = AntColony.BETA;				
				if (AntColony.DIPSLAY_LEVEL==0) {
					minRo = 0.1f; maxRo = 0.1f;
					minBeta = 2; maxBeta = 2; 
				}
				for (double ro= minRo; ro <= maxRo ; ro=ro+0.1) {
					for (double beta= minBeta; beta <= maxBeta; beta=beta+0.5) {
						AntColony.RO = ro;
						AntColony.BETA = beta;
						logger.debug("Beta: "+AntColony.BETA+" Ro: "+AntColony.RO);
						for (int testCount = 0; testCount < AntColony.LOOPS; testCount++) {
							Dbg.prn("#" + Def.df0(testCount+1));
							Process pro = new Process(agraph);

							/***********************************************************************
							 * ************************** OVO MENJAO *******************************
							 ************************************************/

//							logger.info("; (" + Def.df2(best_found) + ") ");
							if (best_found > pro.bestAnt.getCost()) {
								best_found = pro.bestAnt.getCost();
								logger.info("["+Def.df2(best_found)+"] New best found !!! ");
							} else if (best_found / pro.bestAnt.getCost() > 1 / 1.05) {
								for (int i=0; i < 100 * best_found / pro.bestAnt.getCost() - 95; i++) {
									//Dbg.prn("*  ");
								}
							}
							/*
							logger.info("; (" + Def.df2(best_found_time) + ") ");
							if (best_found_time > pro.bestAnt.time) {
								best_found_time = pro.bestAnt.time;
								logger.info("["+Def.df2(best_found_time)+"] New best found !!! ");
							} 
							 */
						}
					}  // beta
				} // ro
				startACO = false;
				//Dbg.prnl("Optimisation finished.");
				if (AntColony.DIPSLAY_LEVEL==0) {
					System.exit(0);
				}
				logger.info("Waiting for new start ");
			}
			try {Thread.sleep(500); } catch (InterruptedException ie) {}
		}
	}

	private void createAnodes(String vrpFile) {
		// Ucitaj sve gradove iz fajla FILE_NAME
		agraph.anodes = AFile.loadNodes(vrpFile);
		statusBar.setText(agraph.anodes.size() + " cities loaded. Drawing all rutes...");
		// Draw a AGraph graph
		if (agraph.draw()) {
			if (AntColony.DIPSLAY_LEVEL > 0) {
				showPheromon = new ShowPheromon(agraph.anodes);
				parameters = new Parameters(MainFrame.this);
			}
			statusBar.setText((agraph.anodes.size()-1) + " cities loaded."); // centrali i (size-1) gradova	
		}
	}

	public void start() {
		startACO = true;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainFrame.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			logger.error("Couldn't find file: " + path);
			logger.error(MainFrame.class.getResource(path));
			return null;
		}
	}

	private void OpenActionPerformed(ActionEvent evt) {
		//		System.out.println("Open actionPerformed, event="+ evt);
		final JFileChooser fc = new JFileChooser("./def");
		fc.addChoosableFileFilter(new VrpFilter());
		int returnVal = fc.showOpenDialog(MainFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			createAnodes(fc.getSelectedFile().getAbsoluteFile().toString());
		}
	}

	private void jButtonAddActionPerformed(ActionEvent evt) {
		logger.debug("jButtonAdd.actionPerformed, event=" + evt);
		if(agraph.anodes != null)
		{
			agraph.addNewNode(30 * (agraph.anodes.size() + 1), 30 * (agraph.anodes.size() + 1));
		}
		else
		{
			agraph.addNewNode(30, 30);
		}


		// posle ubacivanja novih node-ova instanciranje prozora
		showPheromon = new ShowPheromon(agraph.anodes);
		parameters = new Parameters(MainFrame.this);
	}

	private void StartActionPerformed(ActionEvent evt) {            
		logger.info("jButtonStart.actionPerformed, event=" + evt);
		startACO = (agraph.anodes.size()>0);
		if (jButtonStart.getText().equals("Pause")) {
			AntColony.STOP = true;
		} else {
			AntColony.STOP = false;
		}
		if (AntColony.STOP) {
			jButtonStart.setText("Start");
			jButtonStart.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/objects_017.gif")));
		} else {
			jButtonStart.setText("Pause");
			jButtonStart.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/Error.gif")));
		}
		logger.info("start: "+startACO);
	}

}