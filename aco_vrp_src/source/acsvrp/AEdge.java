package acsvrp;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.jgraph.graph.DefaultEdge;

public class AEdge extends DefaultEdge {

    private static final long serialVersionUID = 1L;
    static final Logger logger = Logger.getLogger(AEdge.class);

    double pheromon;
    String startIndx, endIndx;
    public Cost cost = new Cost();

    public AEdge(String startPar, String endPar)
    {
        super();
        pheromon = AntColony.START_PHEROMON;
        startIndx = startPar;
        endIndx = endPar;
        setUserObject(Double.toString(pheromon));
    }

    /**
     * @return Returns the pheromon.
     */
    public double getPheromon()
    {
        return pheromon;
    }

    /**
     * @param pheromon The pheromon to set.
     */
    public void setPheromon(double ph)
    {
        DecimalFormat df = new DecimalFormat(AntColony.NUM_FORMAT);
        DecimalFormat df2 = new DecimalFormat(AntColony.FORMAT_DOUBLE_2);
        setUserObject("P " + df.format(1000 * this.pheromon) + "   D " + df2.format(cost.getDistance()) + "  T " + df2.format(cost.getTime()));
        pheromon = ph;
    }

    public String getToolTipString()
    {
        return (startIndx+"->"+endIndx+" Ph:"+pheromon+" Length:"+cost.getDistance()
        		+" [" + Process.tauNi(pheromon, cost.getValue(), AntColony.RO) 
        		+ "] Cost:"+cost.getValue()+" Time:")+cost.getTime();
    }
    
    public String getStart()
    {
        return startIndx;
    }

    public String getEnd()
    {
        return endIndx;
    }

}
