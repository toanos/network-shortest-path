import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
            Scanner inFile = new Scanner(new File("input2.txt"));
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
        int gorgeSize = pads.size();
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

}
