package acsvrp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
public class Parameters extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    /*
	private JPanel jPanel1;
	private JTextField tBeta;
	private JLabel lLoops;
	private JTextField tLoops;
	private JCheckBox cbLocalUpdate;
	private JLabel jSpeed;
	private JSpinner sDisplayLevel;
	private JLabel jDisplayLevel;
	private JSpinner jSpinner1;
	private JButton bCancel;
	private JButton bOK;
	private JButton jButton2;
	private JButton jButton1;
	private JTextField tRo;
	private JTextField tPheromonStart;
	private JLabel lRo;
	private JLabel lBeta;
	private JLabel lPheromonStart;
	private JTextField tMaxCycles;
	private static JLabel lMaxCycles;
    */
	static boolean stoped;
    
    
    private JButton btnCancel;
    private JButton btnLoad;
    private JButton btnOK;
    private JButton btnSave;
    private JCheckBox cbUpdate;
    private JLabel lblAlpha;
    private JLabel lblBeta;
    private JLabel lblDisplayLevel;
    private JLabel lblLoop;
    private JLabel lblMaxCycleParams;
    private JLabel lblPheromon;
    private JLabel lblRo;
    private JLabel lblSpeed;
    private JPanel panel;
    private JSpinner sDisplayLevel;
    private JSpinner sSpeed;
    private JTextField tfAlpha;
    private JTextField tfBeta;
    private JTextField tfLoops;
    private JTextField tfMaxCycles;
    private JTextField tfPheromon;
    private JTextField tfRo;
	
	public Parameters(JFrame frame) {
		super(frame, true);
		//initGUI();
        initialize();
	}
    
    private void initialize() {
        GridBagConstraints gridBagConstraints;

        panel = new JPanel();
        lblPheromon = new JLabel();
        lblRo = new JLabel();
        lblBeta = new JLabel();
        lblAlpha = new JLabel();
        lblSpeed = new JLabel();
        lblDisplayLevel = new JLabel();
        lblLoop = new JLabel();
        tfPheromon = new JTextField();
        tfRo = new JTextField();
        tfBeta = new JTextField();
        tfAlpha = new JTextField();
        tfLoops = new JTextField();
        sSpeed = new JSpinner();
        sDisplayLevel = new JSpinner();
        btnLoad = new JButton();
        btnSave = new JButton();
        btnOK = new JButton();
        btnCancel = new JButton();
        cbUpdate = new JCheckBox();
        lblMaxCycleParams = new JLabel();
        tfMaxCycles = new JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("AntColony parameters");

        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);

        lblPheromon.setText("Pheromon start:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(25, 25, 5, 5);
        lblPheromon.setForeground(Color.WHITE);
        panel.add(lblPheromon, gridBagConstraints);

        lblRo.setText("Ro:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(25, 5, 5, 5);
        lblRo.setForeground(Color.WHITE);
        panel.add(lblRo, gridBagConstraints);

        lblBeta.setText("Beta:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        lblBeta.setForeground(Color.WHITE);
        panel.add(lblBeta, gridBagConstraints);

        lblAlpha.setText("Alpha:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        lblAlpha.setForeground(Color.WHITE);
        panel.add(lblAlpha, gridBagConstraints);

        lblSpeed.setText("Speed");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(5, 25, 5, 5);
        lblSpeed.setForeground(Color.WHITE);
        panel.add(lblSpeed, gridBagConstraints);

        lblDisplayLevel.setText("Display level:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        lblDisplayLevel.setForeground(Color.WHITE);
        panel.add(lblDisplayLevel, gridBagConstraints);

        lblLoop.setText("Loops:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(5, 25, 5, 5);
        lblLoop.setForeground(Color.WHITE);
        panel.add(lblLoop, gridBagConstraints);

        tfPheromon.setColumns(8);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(25, 5, 5, 5);
        String t = ""+AntColony.START_PHEROMON;
        tfPheromon.setText(t);
        panel.add(tfPheromon, gridBagConstraints);

        tfRo.setColumns(8);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(25, 5, 5, 25);
        tfRo.setText(""+AntColony.RO);
        panel.add(tfRo, gridBagConstraints);

        tfBeta.setColumns(3);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        tfBeta.setText(""+AntColony.BETA);
        panel.add(tfBeta, gridBagConstraints);

        tfAlpha.setColumns(3);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        tfAlpha.setText(""+AntColony.ALPHA);
        panel.add(tfAlpha, gridBagConstraints);

        tfLoops.setColumns(3);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        tfLoops.setText(""+AntColony.LOOPS);
        panel.add(tfLoops, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final SpinnerListModel jSpinner1Model = new SpinnerListModel(
            new String[] { "0", "1", "2", "5", "10", "20", "50" });
        this.addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent evt) {
                System.out
                    .println("this.componentHidden, event="
                        + evt);
                AntColony.STOP = stoped;
            }
            public void componentShown(ComponentEvent evt) {
                System.out
                    .println("this.componentShown, event="
                        + evt);
                stoped = AntColony.STOP;
                AntColony.STOP = true;
            }
        });
        jSpinner1Model.setValue(AntColony.SPEED+"");
        sSpeed = new JSpinner();
        lblSpeed.add(sSpeed);
        sSpeed.setModel(jSpinner1Model);
        sSpeed.setBounds(42, 0, 49, 21);
        sSpeed.setFont(new java.awt.Font("Tahoma", 0, 11));
        sSpeed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                AntColony.SPEED = Integer.parseInt((String)sSpeed.getValue());
            }
        });
        panel.add(sSpeed, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final SpinnerListModel sDisplayLevelModel = new SpinnerListModel(
            new String[] { "0", "1", "2", "3" });
        sDisplayLevelModel.setValue(AntColony.DIPSLAY_LEVEL+"");
        sDisplayLevel = new JSpinner();
        lblDisplayLevel.add(sDisplayLevel);
        sDisplayLevel.setModel(sDisplayLevelModel);
        sDisplayLevel.setBounds(84, 0, 49, 21);
        sDisplayLevel.setFont(new java.awt.Font("Tahoma", 0, 11));
        panel.add(sDisplayLevel, gridBagConstraints);

        btnLoad.setText("LOAD");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                load(evt);
            }
        });
        btnLoad.setBackground(Color.DARK_GRAY);
        btnLoad.setForeground(Color.WHITE);
        panel.add(btnLoad, gridBagConstraints);

        btnSave.setText("SAVE");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                save(evt);
            }
        });
        btnSave.setBackground(Color.DARK_GRAY);
        btnSave.setForeground(Color.WHITE);
        panel.add(btnSave, gridBagConstraints);

        btnOK.setText("OK");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("bOK.actionPerformed, event="
                    + evt);
                System.out.println("bCancel.actionPerformed, event="+ evt);
                AntColony.MAX_CYCLES_PARAM = Integer.parseInt(tfMaxCycles.getText());
                AntColony.START_PHEROMON = Float.parseFloat(tfPheromon.getText());
                AntColony.ALPHA = Float.parseFloat(tfAlpha.getText());
                AntColony.BETA = Float.parseFloat(tfBeta.getText());
                AntColony.RO = Float.parseFloat(tfRo.getText());
                AntColony.DIPSLAY_LEVEL = Integer.parseInt((String)sDisplayLevel.getValue());
                AntColony.LOCAL_UPDATE = cbUpdate.isSelected();
                AntColony.LOOPS = Integer.parseInt((String)tfLoops.getText());
                dispose();
                AntColony.STOP = stoped;
            }
        });
        btnOK.setBackground(Color.DARK_GRAY);
        btnOK.setForeground(Color.WHITE);
        panel.add(btnOK, gridBagConstraints);

        btnCancel.setText("CANCEL");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("bCancel.actionPerformed, event="+ evt);
                dispose();
                AntColony.STOP = stoped;
            }
        });
        btnCancel.setBackground(Color.DARK_GRAY);
        btnCancel.setForeground(Color.WHITE);
        panel.add(btnCancel, gridBagConstraints);

        cbUpdate.setText("Local Update");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(5, 25, 5, 5);
        cbUpdate.setBackground(Color.DARK_GRAY);
        cbUpdate.setForeground(Color.WHITE);
        panel.add(cbUpdate, gridBagConstraints);

        lblMaxCycleParams.setText("Max Cycles param:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        lblMaxCycleParams.setForeground(Color.WHITE);
        panel.add(lblMaxCycleParams, gridBagConstraints);

        tfMaxCycles.setColumns(3);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(tfMaxCycles, gridBagConstraints);
        tfMaxCycles.setText(""+AntColony.MAX_CYCLES_PARAM);

        getContentPane().add(panel, BorderLayout.CENTER);

        pack();
    }
	
    /*
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				this.setTitle("AntColony parameters");
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
//				jPanel1.setLayout(jPanel1Layout);
				jPanel1.setLayout(null);
				jPanel1.setPreferredSize(new java.awt.Dimension(280, 231));
				{
					lMaxCycles = new JLabel();
					jPanel1.add(lMaxCycles);
					lMaxCycles.setText("Max Cycles param:");
					lMaxCycles.setBounds(148, 126, 175, 21);
					lMaxCycles.setHorizontalTextPosition(SwingConstants.RIGHT);
					{
						tMaxCycles = new JTextField();
						lMaxCycles.add(tMaxCycles);
						tMaxCycles.setText(""+AntColony.MAX_CYCLES_PARAM);
						tMaxCycles.setBounds(112, 0, 49, 21);
						tMaxCycles.setHorizontalAlignment(SwingConstants.RIGHT);
					}
				}
				{
					lPheromonStart = new JLabel();
					jPanel1.add(lPheromonStart);
					lPheromonStart.setText("Pheromon start:");
					lPheromonStart.setBounds(14, 28, 161, 21);
					{
						tPheromonStart = new JTextField();
						lPheromonStart.add(tPheromonStart);
						tPheromonStart.setBounds(105, 0, 49, 21);
						String t = ""+AntColony.START_PHEROMON;
						tPheromonStart.setText(t);
						tPheromonStart.setHorizontalAlignment(SwingConstants.RIGHT);
					}
				}
				{
					lBeta = new JLabel();
					jPanel1.add(lBeta);
					lBeta.setText("Beta:");
					lBeta.setBounds(225, 56, 98, 21);
					{
						tBeta = new JTextField();
						lBeta.add(tBeta);
						tBeta.setBounds(35, 0, 49, 21);
						tBeta.setText(""+AntColony.BETA);
						tBeta.setIgnoreRepaint(true);
						tBeta.setHorizontalAlignment(SwingConstants.RIGHT);
					}
				}
				{
					lRo = new JLabel();
					jPanel1.add(lRo);
					lRo.setText("Ro:");
					lRo.setBounds(232, 28, 91, 21);
					{
						tRo = new JTextField();
						lRo.add(tRo);
						tRo.setBounds(28, 0, 49, 21);
						tRo.setText(""+AntColony.RO);
						tRo.setHorizontalAlignment(SwingConstants.RIGHT);
					}
				}
				{
					bOK = new JButton();
					jPanel1.add(bOK);
					bOK.setText("OK");
					bOK.setBounds(170, 175, 75, 21);
					bOK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("bOK.actionPerformed, event="
								+ evt);
							System.out.println("bCancel.actionPerformed, event="+ evt);
							AntColony.MAX_CYCLES_PARAM = Integer.parseInt(tMaxCycles.getText());
							AntColony.START_PHEROMON = Float.parseFloat(tPheromonStart.getText());
							AntColony.BETA = Float.parseFloat(tBeta.getText());
							AntColony.RO = Float.parseFloat(tRo.getText());
							AntColony.DIPSLAY_LEVEL = Integer.parseInt((String)sDisplayLevel.getValue());
							AntColony.LOCAL_UPDATE = cbLocalUpdate.isSelected();
							AntColony.LOOPS = Integer.parseInt((String)tLoops.getText());
							dispose();
							AntColony.STOP = stoped;
						}
					});
				}
				{
					bCancel = new JButton();
					jPanel1.add(bCancel);
					bCancel.setText("Cancel");
					bCancel.setBounds(249, 175, 75, 21);
					bCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("bCancel.actionPerformed, event="+ evt);
							dispose();
							AntColony.STOP = stoped;
						}
					});
				}
				{
					jDisplayLevel = new JLabel();
					jPanel1.add(jDisplayLevel);
					jDisplayLevel.setText("Display level: ");
					jDisplayLevel.setBounds(176, 98, 147, 21);
					{
						final SpinnerListModel sDisplayLevelModel = new SpinnerListModel(
							new String[] { "0", "1", "2", "3" });
						sDisplayLevelModel.setValue(AntColony.DIPSLAY_LEVEL+"");
						sDisplayLevel = new JSpinner();
						jDisplayLevel.add(sDisplayLevel);
						sDisplayLevel.setModel(sDisplayLevelModel);
						sDisplayLevel.setBounds(84, 0, 49, 21);
						sDisplayLevel.setFont(new java.awt.Font("Tahoma", 0, 11));
					}
				}
				{
					jSpeed = new JLabel();
					jPanel1.add(jSpeed);
					jSpeed.setText("Speed: ");
					jSpeed.setBounds(14, 98, 98, 21);
					{
						final SpinnerListModel jSpinner1Model = new SpinnerListModel(
							new String[] { "0", "1", "2", "5", "10", "20", "50" });
						this.addComponentListener(new ComponentAdapter() {
							public void componentHidden(ComponentEvent evt) {
								System.out
									.println("this.componentHidden, event="
										+ evt);
								AntColony.STOP = stoped;
							}
							public void componentShown(ComponentEvent evt) {
								System.out
									.println("this.componentShown, event="
										+ evt);
								stoped = AntColony.STOP;
								AntColony.STOP = true;
							}
						});
						jSpinner1Model.setValue(AntColony.SPEED+"");
						jSpinner1 = new JSpinner();
						jSpeed.add(jSpinner1);
						jSpinner1.setModel(jSpinner1Model);
						jSpinner1.setBounds(42, 0, 49, 21);
						jSpinner1.setFont(new java.awt.Font("Tahoma", 0, 11));
						jSpinner1.addChangeListener(new ChangeListener() {
							public void stateChanged(ChangeEvent evt) {
								AntColony.SPEED = Integer.parseInt((String)jSpinner1.getValue());
							}
						});
					}
				}
				{
					cbLocalUpdate = new JCheckBox();
					jPanel1.add(cbLocalUpdate);
					cbLocalUpdate.setText("Local update");
					cbLocalUpdate.setBounds(14, 56, 133, 21);
				}
				{
					lLoops = new JLabel();
					jPanel1.add(lLoops);
					lLoops.setText("Loops:");
					lLoops.setBounds(14, 126, 91, 21);
					{
						tLoops = new JTextField();
						tLoops.setText(""+AntColony.LOOPS);
						lLoops.add(tLoops);
						tLoops.setBounds(42, 0, 49, 21);
						tLoops.setHorizontalAlignment(SwingConstants.RIGHT);
					}
				}
				{
					jButton1 = new JButton();
					jPanel1.add(jButton1);
					jButton1.setText("Load");
					jButton1.setBounds(8, 175, 75, 21);
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton1ActionPerformed(evt);
						}
					});
				}
				{
					jButton2 = new JButton();
					jPanel1.add(jButton2);
					jButton2.setText("Save");
					jButton2.setBounds(86, 175, 75, 21);
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton2ActionPerformed(evt);
						}
					});
				}
			}
			pack();
			this.setSize(345, 258);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    */
	
	private void save(ActionEvent evt) {
		AntColony.saveACParam();
	}
	
	private void load(ActionEvent evt) {
		AntColony.loadACParam();
	}

}
