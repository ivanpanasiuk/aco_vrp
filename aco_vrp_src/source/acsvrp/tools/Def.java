/**
 * 
 */
package acsvrp.tools;

import java.text.DecimalFormat;

/**
 * @author ipanasiuk
 *
 */
public class Def {
	
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	public static String df2(double param) {
		return df2.format(param);
	}
	
	public static String df0(int param) {
		String res = Integer.toString(param);
		return res;
	}


}
