/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acsvrp;

/**
 *
 * @author Milos Panasiuk
 */
public class TimeConnection {

    private ANode destination;
    private double time;
    
    public TimeConnection(ANode dest, double time)
    {
        this.destination = dest;
        this.time = time;
    }
    
    public ANode getDestination()
    {
        return destination;
    }
    
    public double getTime()
    {
        return time;
    }
    
}
