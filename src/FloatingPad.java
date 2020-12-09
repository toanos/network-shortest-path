import java.math.BigInteger;
import java.util.LinkedList;

/**
 * This class represents the floating pad for the Hobbits to hop on.
 *
 * @author Tony Ha
 */
public class FloatingPad {

    /**
     * Construct a pad with specified value
     */
    public FloatingPad(BigInteger value) {
        this();
        label = value;
    }

    /**
     * Construct a pad with a value of zero (default constructor)
     */
    public FloatingPad() {
        label = BigInteger.ONE;
        minimal = true;
        maximal = true;
        visited = false;
    }

    /**
     * Return the value of floating pad
     */
    public BigInteger getLabel() {
        return this.label;
    }

    public static void connectPads(FloatingPad u, FloatingPad v) {
        // TODO Is this the right way to implement an adjacency list? Or should I go with adjacency matrix?
    }

    public void padVisited() {
        if (!visited) {
            this.visited = true;
        }
    }

    public void padNotMinimal() {
        if (this.minimal) {
            this.minimal = false;
        }
    }

    public void padNotMaximal() {
        if (this.maximal) {
            this.maximal = false;
        }
    }

    @Override
    public String toString() {
        String padType = "";
        if (this.minimal && this.maximal) { padType = "(Minimal, Maximal)"; }
        else if (this.minimal) {
            padType = "(Minimal)";
        }
        else if (this.maximal) {
            padType = "(Maximal)";
        }
        return padType + " " + this.label.toString();
    }

    private static int numPads = 0;
    private BigInteger label;
    private LinkedList<FloatingPad> neighbors;
    private boolean minimal;
    private boolean maximal;
    private boolean visited;

}
