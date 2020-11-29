import java.math.BigInteger;
import java.util.LinkedList;

/**
 * This class represents the floating pads.
 *
 * @author Tony Ha
 */
public class FloatingPads {

    public FloatingPads() {
        // TODO Implement default constructor
    }

    public static void connectPads(FloatingPads u, FloatingPads v) {
        // TODO Is this the right way to implement an adjacency list? Or should I go with adjacency matrix?
    }

    /**
     * Return number of existing floating pads
     */
    public static int getNumPads() {
        return numPads;
    }

    private static int numPads = 0;
    private BigInteger label;
    private LinkedList<FloatingPads> neighbors;

}
