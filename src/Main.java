import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

/**
 * CECS 328 PA5: Find the maximal way of hopping through the gorge to save as many hobbits as possible.
 *
 * @author Tony Ha
 */
public class Main {

    public static void main(String[] args) {
        TheGorge saveHobbits = null;
        ArrayList<String> hoppingPaths = new ArrayList<>();
        // Input phase
        try {
            Scanner inFile = new Scanner(new File("input0.txt"));
            ArrayList<BigInteger> inputPads = new ArrayList<>();
            do {
                String line = inFile.nextLine();
                inputPads.add(new BigInteger(line));
            } while (inFile.hasNextLine());
            Collections.sort(inputPads);
            saveHobbits = new TheGorge(inputPads);
        } catch (IOException e) {
            System.out.println("Input file does not exist! Program terminated.");
        }

        ArrayList<FloatingPad> pads = saveHobbits.getTheGorge();
        // Console output
        System.out.println("The gorge has " + pads.size() + " floating pads.");
        System.out.println(saveHobbits);
        saveHobbits.printAdjList();
        System.out.println();
        // Finding hopping paths
        findPaths(saveHobbits, hoppingPaths);
        // Console output
        System.out.println("The gorge has " + pads.size() + " floating pads.");
        System.out.println(saveHobbits);
        saveHobbits.printAdjList();
        System.out.println();
        System.out.println("Frodo found " + hoppingPaths.size() + " path(s):");
        for (String s : hoppingPaths) {
            System.out.print(s);
        }
        ////////////////// PQ Testing /////////////////////
//        saveHobbits.initializePadScore();
//        PriorityQueue<FloatingPad> h = new PriorityQueue<>(pads);
//        System.out.println("Print Priority Queue h");
//        while (h.peek() != null) {
//            FloatingPad p = h.poll();
//            System.out.println(p + ", Score = " + p.getScore());
//        }
        ////////////////// End of PQ Testing /////////////////////
        // Output phase
        try {
            PrintWriter outFile = new PrintWriter("output.txt");
            for (String s : hoppingPaths) {
                outFile.print(s);
            }
            outFile.close();
        } catch (IOException e) {
            System.out.println("Error occurred! Could not create output4.txt");
        }
    }

    // NOTE the paths parameter is pass by reference
    public static void findPaths(TheGorge evilGorge, ArrayList<String> paths) {
        // NOTE: Use a list of pad to keep track of potential hopping path for the Hobbits
        ArrayList<FloatingPad> floatingPads = evilGorge.getTheGorge();
        FloatingPad sourcePad = floatingPads.get(0);
//        while (evilGorge.getMaximalPads().size() > 0) {
        // NOTE: If a path is both minimal & maximal, take that path first
        for (FloatingPad p : evilGorge.getMaximalPads()) {
            if (p.isMinimal() && p.isMaximal()) {
                p.padVisited();
                p.setParent(sourcePad);
                paths.add(evilGorge.hobbitHoppingPad(p));
            }
        }
        FloatingPad minimalPad = floatingPads.get(1);
        FloatingPad maximalPad = null;
        ArrayList<ArrayList<FloatingPad>> adjGorge = evilGorge.getAdjPads();
        Queue<FloatingPad> myQ = new LinkedList<>();
        myQ.add(minimalPad);
        while (myQ.size() > 0) {
            FloatingPad uPad = myQ.remove();
            // NOTE: index of the floating pads are the same with the adjacency list
            int indU = floatingPads.indexOf(uPad);
            for (FloatingPad vPad : adjGorge.get(indU)) {
                if (vPad.isMaximal()) { maximalPad = vPad; }
                if (!vPad.checkVisited()) {
                    // TODO Still need to implement more
                    vPad.setParent(uPad);
                    myQ.add(vPad);
                }
            }
            uPad.padVisited();
        }
        // BFS finished, now store the generated path
        paths.add(evilGorge.hobbitHoppingPad(maximalPad));
//        }
    }
}
