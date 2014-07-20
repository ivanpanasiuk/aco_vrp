package acsvrp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AntColony {

	static final Logger logger = Logger.getLogger(AGraph.class);
	
	// Ant Colony parameters
	static public double START_PHEROMON = 0.001;
    static public double ALPHA = 0.25;
	static public double BETA = 4.0;
	static public double RO = 0.1;
	static public int costType = Cost.Type.TYPE_DESTINATION;
	// Algorithm parameters
	static public int MAX_CYCLES_PARAM = 1;
	static public boolean LOCAL_UPDATE = true;
	// Presentation parameters
	public static int SPEED = 1;
	public static int DIPSLAY_LEVEL = 3;
	public static boolean STOP = false;
	public static int LOOPS = 1;
	public static boolean AUTO_START = false;	
	// Math
	public static int PREC = 4;
	public static String NUM_FORMAT = "0.";
	public static String FORMAT_DOUBLE_2 = "0.00";
	// Display parameters
	static public int NODE_DIM = 14;
	static public int FIT_X = 620;	// fit 640 x 480
	static public int FIT_Y = 430;	
	static public int MAX_PHEROMON_PIXEL = 15;  //max line width
	
	// File IO parameters
	static public String FILE_NAME = "";
	
	// Lablel
	static public String lblPathCost = "Path cost: ";
	static public String lblBestCost = "Best cost:";
	static public String lblBestAntCost = "Best Ant cost:";



	static MainFrame mf;
	
	static Properties properties = new Properties();
	
	public static void main(String[] args) {
		// pripremi format za prikaz brojeva
		for (int i=0; i < PREC; i++) {
			NUM_FORMAT += "0";
		};
		if (!loadACParam()) {
			saveACParam();
		}
		mf = new MainFrame();          
 	}
	
	public static boolean loadACParam() {
		try {
			logger.trace("Loading AC parameters");
		    properties.load(new FileInputStream("acsvrp.properties"));
		    START_PHEROMON = Float.valueOf(properties.getProperty("START_PHEROMON"));
		    BETA = Float.valueOf(properties.getProperty("BETA"));
		    RO = Float.valueOf( properties.getProperty("RO"));
		    MAX_CYCLES_PARAM = Integer.valueOf( properties.getProperty("MAX_CYCLES_PARAM"));
		    LOCAL_UPDATE = Boolean.valueOf( properties.getProperty("LOCAL_UPDATE"));
		    SPEED = Integer.valueOf( properties.getProperty("SPEED"));
		    DIPSLAY_LEVEL = Integer.valueOf( properties.getProperty("DIPSLAY_LEVEL"));
		    LOOPS = Integer.valueOf( properties.getProperty("LOOPS"));
		    AUTO_START = Boolean.valueOf( properties.getProperty("AUTO_START"));
		    PREC = Integer.valueOf( properties.getProperty("PREC"));
		    NODE_DIM = Integer.valueOf( properties.getProperty("NODE_DIM"));
		    FIT_X = Integer.valueOf( properties.getProperty("FIT_X"));
		    FIT_Y = Integer.valueOf( properties.getProperty("FIT_Y"));
		    FILE_NAME = properties.getProperty("FILE_NAME",FILE_NAME);
		    return true;
		} catch (IOException e) {
			logger.error("Could not read file 'acsvrp.properties'");
			return false;
		}
	}
	
	public static boolean saveACParam() {
		try {
			properties.setProperty("START_PHEROMON", String.valueOf(START_PHEROMON));
			properties.setProperty("BETA", String.valueOf(BETA));
			properties.setProperty("RO", String.valueOf(RO));
			properties.setProperty("MAX_CYCLES_PARAM", String.valueOf(MAX_CYCLES_PARAM));
			properties.setProperty("LOCAL_UPDATE", String.valueOf(LOCAL_UPDATE));
			properties.setProperty("SPEED", String.valueOf(SPEED));
			properties.setProperty("DIPSLAY_LEVEL", String.valueOf(DIPSLAY_LEVEL));
			properties.setProperty("LOOPS", String.valueOf(LOOPS));
			properties.setProperty("AUTO_START", String.valueOf(AUTO_START));
			properties.setProperty("PREC", String.valueOf(PREC));
			properties.setProperty("NODE_DIM", String.valueOf(NODE_DIM));
			properties.setProperty("FIT_X", String.valueOf(FIT_X));
			properties.setProperty("FIT_Y", String.valueOf(FIT_Y));
			properties.setProperty("FILE_NAME", FILE_NAME);			
		    properties.store(new FileOutputStream("acsvrp.properties"), null);
		    return true;
		} catch (IOException e) {
			return false;
		}
	}

}
