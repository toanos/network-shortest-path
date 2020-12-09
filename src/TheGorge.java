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
        for (BigInteger b : padList) {
            floatingPads.add(new FloatingPad(b));
        }
        adjPads = new ArrayList[floatingPads.size()];
        /* Construct an adjacency-list pads */
        for (int i = 0; i < floatingPads.size(); i++) {
            FloatingPad u = floatingPads.get(i);
            ArrayList<FloatingPad> neighborsV = new ArrayList<>();
            for (int j = i + 1; j < floatingPads.size(); j++) {
               FloatingPad v = floatingPads.get(j);
               if (u.getLabel().gcd(v.getLabel()).compareTo(BigInteger.ONE) > 0) {
                   neighborsV.add(v);
               }
            }
            adjPads[i] = neighborsV;
        }
        /* Count maximal pads */
        this.countMax();
        // TODO Determine minimality & maximality of newly constructed pads
    }

    /**
     * Default constructor
     */
    public TheGorge() {
        floatingPads = new ArrayList<FloatingPad>();
        // NOTE: The floating pad 1 always the startng pad. IOW, the source vertex
        floatingPads.add(new FloatingPad());
        adjPads = null;
        maximalNum = 0;
    }

    /** Return the array list of floating pads of the gorge */
    public ArrayList<FloatingPad> getTheGorge() {
        return this.floatingPads;
    }

    public ArrayList<FloatingPad>[] getAdjPads() {
        return this.adjPads;
    }

    public int getMaximalNum() {
        return maximalNum;
    }

    public void removeFloatingPad(FloatingPad pad) {
        floatingPads.remove(pad);
    }

    // NOTE: I might want to implement this toString to produce the path for output
    public String toString() {
        String result = "";
        for (int i = 0; i < floatingPads.size(); i++) {
            result += floatingPads.get(i).toString() + "\n";
        }
        return result;
    }

    public void printAdjList() {
        String result = "";
        for (int i = 0; i < adjPads.length; i++) {
//            result += floatingPads.get(i).getLabel().toString() + ":";
            result += floatingPads.get(i).toString() + ":";
            for (FloatingPad p : adjPads[i]) {
//                result += " ->" + p.getLabel().toString();
                result += " ->" + p.toString();
            }
            System.out.println(result + " /");
            result = "";
        }
    }

    // HELPER for counting number of maximal pads after collected pads
    private void countMax() {
        for (FloatingPad p : this.floatingPads) {
            if (p.isMaximal()) {
                maximalNum++;
            }
        }
    }

    // Note: Vertices of the graph
    private ArrayList<FloatingPad> floatingPads;
    // Note: The adjacency list of the graph
    private ArrayList<FloatingPad>[] adjPads;
    private int maximalNum;

}
