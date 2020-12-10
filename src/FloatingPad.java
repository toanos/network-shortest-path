import java.math.BigInteger;
import java.util.LinkedList;

/**
 * This class represents the floating pad for the Hobbits to hop on.
 *
 * @author Tony Ha
 */
public class FloatingPad implements Comparable<FloatingPad> {

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
        incomingTraffic = 0;
        score = 0;
        parent = null;
    }

    public void setParent(FloatingPad p) {
        this.parent = p;
    }

    public FloatingPad getParent() {
        return this.parent;
    }

    public int getIncomingTraffic() {
        return this.incomingTraffic;
    }

    public void incrementTraffic() {
        this.incomingTraffic++;
    }

    /**
     * Return the value of floating pad
     */
    public BigInteger getLabel() {
        return this.label;
    }

    public boolean checkVisited() {
        return this.visited;
    }

    public static void connectPads(FloatingPad u, FloatingPad v) {
        // TODO Is this the right way to implement an adjacency list? Or should I go with adjacency matrix?
    }

    public boolean isMinimal() {
        return this.minimal;
    }

    public boolean isMaximal() {
        return this.maximal;
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
    public boolean equals(Object o) {
        if (o instanceof FloatingPad) {
            FloatingPad p = (FloatingPad) o;
            if (this.label.equals(p.label)) {
                return true;
            }
        }
        return false;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int s) { this.score = s; }

    @Override
    public int compareTo(FloatingPad b) {
        if (this.score < b.getScore()) {
            return -1;
        } else if (this.score > b.getScore()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        String padType = "";
        if (this.minimal && this.maximal) {
            padType = "(Minimal, Maximal)";
        } else if (this.minimal) {
            padType = "(Minimal)";
        } else if (this.maximal) {
            padType = "(Maximal)";
        }
        return padType + " " + this.label.toString();
    }

    private BigInteger label;
    private boolean minimal;
    private boolean maximal;
    private boolean visited;
    private FloatingPad parent;
    private int incomingTraffic;
    private int score;

}
