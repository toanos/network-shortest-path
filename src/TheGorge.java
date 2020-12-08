import java.math.BigInteger;
import java.util.ArrayList;

/**
 * This class represents the gore with floating pads that the Hobbits need to
 * hop on to go to the other side.
 *
 * @author Tony Ha
 */
public class TheGorge {

    /**
     * Construct the gorge with the specified list.
     *
     * @param padList an array list of BigInteger values
     */
    public TheGorge(ArrayList<BigInteger> padList) {
        this();
        for (BigInteger p : padList) {
            g.add(new FloatingPad(p));
        }
    }

    /**
     * Default constructor
     */
    public TheGorge() {
        g = new ArrayList<FloatingPad>();
        g.add(new FloatingPad());
    }

    /** Return the array list of floating pads of the gorge */
    public ArrayList<FloatingPad> getTheGorge() {
        return this.g;
    }

    public void removeFloatingPad(FloatingPad pad) {
        g.remove(pad);
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < g.size(); i++) {
            result += g.get(i).toString() + "\n";
        }
        return result;
    }

    private ArrayList<FloatingPad> g;
}
