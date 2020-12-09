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
        int gorgeSize = 0;
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
        gorgeSize = pads.size();
        /* NOTE: Pad 1 is not a maximal pad because hobbits can hop from it.
         * Since it is the starting pad, it definitely not minimal since hobbits are standing on it
         * so they can't jump onto it.
         * Another way of thinking is that the minimal pad is the SOURCE of the graph and the maximal pad is the SINK.
         */
        pads.get(0).padNotMinimal();
        pads.get(0).padNotMaximal();
        // Determine MINIMALITY (can't hop INTO except for pad 1) of all the pads
        for (int i = gorgeSize - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                BigInteger padA = pads.get(i).getLabel();
                BigInteger padB = pads.get(j).getLabel();
                if (!padB.gcd(padA).equals(BigInteger.ONE)) {
                    pads.get(i).padNotMinimal();
                    break;
                }
            }
        }
        // Determine MAXIMALITY (can't hop FROM) of all the pads
        for (int i = 0; i < gorgeSize - 1; i++) {
            for (int j = i + 1; j < gorgeSize; j++) {
                BigInteger padA = pads.get(i).getLabel();
                BigInteger padB = pads.get(j).getLabel();
                if (!padB.gcd(padA).equals(BigInteger.ONE)) {
                    pads.get(i).padNotMaximal();
                    break;
                }
            }
        }
        // Console output
        System.out.println("The gorge has " + gorgeSize + " floating pads.");
        System.out.println(saveHobbits);
        saveHobbits.printAdjList();
        // Output phase
        try {
            PrintWriter outFile = new PrintWriter("output.txt");
        } catch (IOException e) {
            System.out.println("Error occurred! Could not create output.txt");
        }
    }

    // NOTE the paths parameter is pass by reference
//    public static void findPaths(TheGorge evilGorge, ArrayList<String> paths) {
//        // TODO Save Hobbits using BFS
//        // while number of maximal pads is greater than 0
//        //    start at the smallest minimal pad
//        //    BFS from source to a maximal pad
//        //    Reconstruct the adjacency list
//        //    Reset pads back to default value
//        ArrayList<FloatingPad> floatingPads = evilGorge.getTheGorge();
//        while (evilGorge.getMaximalNum() > 0) {
//            // NOTE: If a path is both minimal & maximal, take that path first
//            // TODO BFS
//            // TODO Adjust sourcePad to fit specification, for now main alread did
//            FloatingPad sourcePad = floatingPads.get(0);
//            ArrayList<ArrayList<FloatingPad>> adjGorge = evilGorge.getAdjPads();
//            Queue<FloatingPad> myQ = new LinkedList<>();
//            myQ.add(sourcePad);
//            while (myQ.size() > 0) {
//                FloatingPad uPad = myQ.remove();
//                // NOTE: index of the floating pads are the same with the adjacency list
//                int indU = floatingPads.indexOf(uPad);
//                for (FloatingPad vPad : adjGorge.get(indU)) {
//                    if (vPad.checkVisited() == false) {
//                        // TODO Still need to implement more
//                        myQ.add(vPad);
//                    }
//                    uPad.padVisited();
//                }
//            }
//        }
//    }
    // NOTE the paths parameter is pass by reference
    public static void findPaths(TheGorge evilGorge, ArrayList<String> paths) {
        // TODO Save Hobbits using BFS
        // while number of maximal pads is greater than 0
        //    start at the smallest minimal pad
        //    BFS from source to a maximal pad
        //    Reconstruct the adjacency list
        //    Reset pads back to default value
        ArrayList<FloatingPad> floatingPads = evilGorge.getTheGorge();
//        while (evilGorge.getMaximalNum() > 0) {
            // NOTE: If a path is both minimal & maximal, take that path first
            // TODO BFS
            // TODO Adjust sourcePad to fit specification, for now main alread did
            FloatingPad sourcePad = floatingPads.get(0);
            FloatingPad minimalPad = floatingPads.get(1);
            ArrayList<ArrayList<FloatingPad>> adjGorge = evilGorge.getAdjPads();
            Queue<FloatingPad> myQ = new LinkedList<>();
            myQ.add(minimalPad);
            while (myQ.size() > 0) {
                FloatingPad uPad = myQ.remove();
                // NOTE: index of the floating pads are the same with the adjacency list
                int indU = floatingPads.indexOf(uPad);
                for (FloatingPad vPad : adjGorge.get(indU)) {
                    if (!vPad.checkVisited()) {
                        // TODO Still need to implement more
                        myQ.add(vPad);
                    }
                    uPad.padVisited();
                }
            }
//        }
    }
}
