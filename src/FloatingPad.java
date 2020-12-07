import java.math.BigInteger;
import java.util.LinkedList;

/**
 * This class represents the floating pad for the Hobbits to hop on.
 *
 * @author Tony Ha
 */
public class FloatingPad {

    public FloatingPad() {
        // TODO Implement default constructor
    }

    public static void connectPads(FloatingPad u, FloatingPad v) {
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
    private LinkedList<FloatingPad> neighbors;
    private boolean minimal;
    private boolean maximal;

}
