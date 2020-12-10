import java.math.BigInteger;
import java.util.ArrayList;
import java.util.ListIterator;

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
            // NOTE: these new pads have at least 1 in-degree
            floatingPads.get(floatingPads.size() - 1).incrementTraffic();
        }
        /* Construct an adjacency-list pads */
        constructAdjFloatingPads();
        // TODO Determine minimality & maximality of newly constructed pads
        // TODO Keep track of maximal pads
        trackMaximalPads();
    }

    /**
     * Default constructor
     */
    public TheGorge() {
        floatingPads = new ArrayList<FloatingPad>();
        // NOTE: The floating pad 1 always the starting pad. IOW, the source vertex
        floatingPads.add(new FloatingPad());
        adjPads = null;
        maximalPads = new ArrayList<FloatingPad>();
    }

    /**
     * Construct an adjacency list of all floating pads. This also check for traffic for each floating pads
     */
    private void constructAdjFloatingPads() {
        adjPads = new ArrayList<>();
        for (int i = 0; i < floatingPads.size(); i++) {
            FloatingPad u = floatingPads.get(i);
            ArrayList<FloatingPad> neighborsV = new ArrayList<>();
            for (int j = i + 1; j < floatingPads.size(); j++) {
                FloatingPad v = floatingPads.get(j);
                if (u.getLabel().gcd(v.getLabel()).compareTo(BigInteger.ONE) > 0) {
                    neighborsV.add(v);
                    v.incrementTraffic();
                }
            }
            adjPads.add(neighborsV);
        }
    }

    public void initializePadScore() {
        for (FloatingPad p : floatingPads) {
            p.setScore(p.getIncomingTraffic());
        }
    }

    /**
     * Once the hobbit crosses to the other side, the traversed pad will drop into the gorge.
     *
     * @param targetPad the floating pad the hobbit needs to reach in order to be saved
     * @return the path the hobbit traversed
     */
    public String hobbitHoppingPad(FloatingPad targetPad) {
        String result = "";
        if (targetPad.isMaximal() && targetPad.checkVisited()) {
            result = targetPad.getLabel() + " \n";
            FloatingPad currentPad = targetPad;
            while (!(currentPad.getParent().getLabel().compareTo(BigInteger.ONE) == 0)) {
                currentPad = currentPad.getParent();
                result = currentPad.getLabel() + " " + result;
            }
            result = "1 " + result;
            constructAdjFloatingPads();
        } else {
            System.out.println(targetPad + "has not been visited.");
            result = "Error: No path for " + targetPad;
        }
        return result;
    }

    /** Remove a floating pad. Formally, traverse the floating pad list and remove the specified element */
    private void removePad(FloatingPad p) {
        ListIterator<FloatingPad> flPadListIt = this.floatingPads.listIterator();
        while (flPadListIt.hasNext()) {
            FloatingPad q = flPadListIt.next();
            if (p.equals(q)) {
                flPadListIt.remove();
            }
        }
    }

    /**
     * Return the list of floating pads of the gorge
     */
    public ArrayList<FloatingPad> getTheGorge() {
        return this.floatingPads;
    }

    /**
     * Return the adjacency list of floating pads of the gorge
     */
    public ArrayList<ArrayList<FloatingPad>> getAdjPads() {
        return this.adjPads;
    }

    public void removeFloatingPad(FloatingPad pad) {
        floatingPads.remove(pad);
        adjPads.remove(pad);
    }

    // NOTE: I might want to implement this toString to produce the path for output
    public String toString() {
        String result = "";
        for (int i = 0; i < floatingPads.size(); i++) {
            result += floatingPads.get(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Console output the adjacency list of the gorge
     */
    public void printAdjList() {
        String result = "";
        System.out.println("ADJACENCY LIST:");
        for (int i = 0; i < adjPads.size(); i++) {
            result += "(In-degree: " + floatingPads.get(i).getIncomingTraffic() + ") ";
            result += floatingPads.get(i).toString() + ":";
            for (FloatingPad p : adjPads.get(i)) {
                result += " ->" + p.toString();
            }
            System.out.println(result + " /");
            result = "";
        }
    }

    /**
     * Return the number of maximal pads available
     */
    public int countMaximal() {
        return this.maximalPads.size();
    }

    /**
     * Find pre-determined maximal pads and store their references (HELPER)
     */
    private void trackMaximalPads() {
        for (FloatingPad p : floatingPads) {
            if (p.isMaximal()) {
                this.maximalPads.add(p);
            }
        }
    }

    public ArrayList<FloatingPad> getMaximalPads() {
        return this.maximalPads;
    }

    // Gorge's instances
    private ArrayList<FloatingPad> floatingPads; // Note: Vertices of the graph
    private ArrayList<FloatingPad> maximalPads;
    // NOTE: HAve a list for tracking minimal pads
    private ArrayList<ArrayList<FloatingPad>> adjPads; // Note: The adjacency list of the graph

}
