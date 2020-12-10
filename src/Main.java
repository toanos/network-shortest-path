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
        ConsoleOutput(saveHobbits, pads);
        System.out.println();
        ///////////////////////////////////// Frodo perform Dijkstra's algorithm /////////////////////////////////
        ArrayList<FloatingPad> simplePaths = new ArrayList<>();
        // Case 1: Find simple & short hopping paths
        for (FloatingPad p : pads) {
            if (p.isMinimal() && p.isMaximal()) {
                simplePaths.add(p);
            }
        }
        // NOTE: Do this to avoid concurrent modification error
        for (FloatingPad q : simplePaths) {
            hoppingPaths.add(saveHobbits.hobbitHoppingPad(q));
        }
        while (saveHobbits.getMinimalPads().size() != 0 || saveHobbits.getMaximalPads().size() != 0) {
           // TODO Implement my paths finding
        }
        ///////////////////////////////////// End of Frodo perform Dijkstra's algorithm //////////////////////////
        // Console output
        ConsoleOutput(saveHobbits, pads);
        System.out.println();
        System.out.println("Frodo found " + hoppingPaths.size() + " path(s):");
        for (String s : hoppingPaths) {
            System.out.print(s);
        }
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

    private static void ConsoleOutput(TheGorge saveHobbits, ArrayList<FloatingPad> pads) {
        System.out.println("The gorge has " + pads.size() + " floating pads.");
        System.out.println(saveHobbits);
        saveHobbits.printAdjList();
    }

    /**
     * Traverse the shortest path from the specified minimal pad to a maximal path
     *
     * @param minPad the starting minimal pad
     * @param g the gorge where all the pads lie
     * @return the maximal pad with the generated shortest path
     */
    public static FloatingPad hopShortestPath(FloatingPad minPad, TheGorge g) {
        FloatingPad result = null;
        // Let s be the source
        FloatingPad s = minPad;
        s.setScore(0.0);
        ArrayList<FloatingPad> padList = g.getTheGorge();
        ArrayList<ArrayList<FloatingPad>> adjPads = g.getAdjPads();
        PriorityQueue<FloatingPad> pQ = new PriorityQueue<>(padList);
        while (!pQ.isEmpty()) {
            FloatingPad u = pQ.poll();
            if (u.getScore() < Double.POSITIVE_INFINITY) {
                int uAdjIndex = padList.indexOf(u);
                for (FloatingPad v : adjPads.get(uAdjIndex)) {
                    if (u.getScore() + v.getIncomingTraffic() < v.getScore()) {
                        v.setScore(u.getScore() + v.getIncomingTraffic());
                        v.setParent(u);
                    }
                    if (v.isMaximal()) {
                        result = v;
                        pQ.clear();
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Find the shortest path from the specified minimal pad to a maximal path
     *
     * @param minPad the starting minimal pad
     * @param g the gorge where all the pads lie
     * @return the score of the shortest path computed
     */
    public static double findShortestPath(FloatingPad minPad, TheGorge g) {
        double result = 0.0;
        // Let s be the source
        FloatingPad s = minPad;
        s.setScore(0.0);
        ArrayList<FloatingPad> padList = g.getTheGorge();
        ArrayList<ArrayList<FloatingPad>> adjPads = g.getAdjPads();
        PriorityQueue<FloatingPad> pQ = new PriorityQueue<>(padList);
        while (!pQ.isEmpty()) {
            FloatingPad u = pQ.poll();
            if (u.getScore() < Double.POSITIVE_INFINITY) {
                int uAdjIndex = padList.indexOf(u);
                for (FloatingPad v : adjPads.get(uAdjIndex)) {
                    if (u.getScore() + v.getIncomingTraffic() < v.getScore()) {
                        v.setScore(u.getScore() + v.getIncomingTraffic());
                        v.setParent(u);
                    }
                    if (v.isMaximal()) {
                        result = v.getScore();
                        pQ.clear();
                        break;
                    }
                }
            }
        }
        return result;
    }

}
