package acsvrp.tools;

import acsvrp.AntColony;
import org.apache.log4j.Logger;


/**
 * @author ivan.panasiuk
 *
 */
public class Dbg {
	
	static final Logger logger = Logger.getLogger(Dbg.class);
	
	public static void prnl(Object obj) {
		prn(obj);
		prnl();
	}

	public static void prn(Object obj) {
		logger.debug(obj);
//		System.out.print(obj);		
	}

	public static void prnl() {
//		System.out.println();
	}
	
	public static void delay (int ms) {
		try
		{ 
			Thread.sleep(ms * AntColony.SPEED);
//			System.out.println("... Wait "+ (ms * AntColony.SPEED)+" ("+ms+" * "+AntColony.SPEED+") ms [Dbg.java]");
		}
		catch (InterruptedException ie) {	}
		while (AntColony.STOP) {
			try
			{ 
				Thread.sleep(1000); //wait
			}
			catch (InterruptedException ie) {	}			
		}
	}


}
