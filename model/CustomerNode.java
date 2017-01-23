package vrp.model;

/**
 * Created by harikanukala on 4/21/16.
 */

public class CustomerNode {
    public int customerNumber;
    public Route route;
    public double x;
    public double y;
    public int demand;


    public CustomerNode(int i) {
        customerNumber = i;
    }

}
