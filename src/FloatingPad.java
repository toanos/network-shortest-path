import java.math.BigInteger;
import java.util.LinkedList;

/**
 * This class represents the floating pad for the Hobbits to hop on.
 *
 * @author Tony Ha
 */
public class FloatingPad {

    /** Construct a pad with specified value */
    public FloatingPad(String value) {
        new FloatingPad();
        label = new BigInteger(value);
    }

    /** Construct a pad with a value of zero (default constructor) */
    public FloatingPad() {
        label = BigInteger.ZERO;
        minimal = true;
        maximal = true;
    }

    /** Return the value of floating pad */
    public BigInteger getLabel() { return this.label; }

    public static void connectPads(FloatingPad u, FloatingPad v) {
        // TODO Is this the right way to implement an adjacency list? Or should I go with adjacency matrix?
    }

    /**
     * TODO: Can be remove because the gorge should be the one keeping track of number of pads
     * Return number of existing floating pads
     */
    public static int getNumPads() {
        return numPads;
    }

    @Override
    public String toString() {
        String padType ="(";
        if (this.minimal) { padType += "minimal, "; }
        if (this.minimal) { padType += "maximal"; }
        padType += ")";
        return this.label.toString() + padType;
    }

    private static int numPads = 0;
    private BigInteger label;
    private LinkedList<FloatingPad> neighbors;
    private boolean minimal;
    private boolean maximal;

}
