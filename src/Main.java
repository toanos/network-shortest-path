import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * CECS 328 PA5: hobbits
 *
 * @author Tony Ha
 */
public class Main {

    public static void main(String[] args) {
        TheGorge saveHobbits = new TheGorge();
        // Input phase
        try {
            Scanner inFile = new Scanner(new File("input1.txt"));
            do {
                String line = inFile.nextLine();
                saveHobbits.addFloatingPads(new FloatingPad(line));
            } while (inFile.hasNextLine());
        } catch (IOException e) {
            System.out.println("Input file does not exist! Program terminated.");
        }

        // Console output:
        System.out.println(saveHobbits);

        // Output phase
        try {
            PrintWriter outFile = new PrintWriter("output.txt");
        } catch (IOException e) {
            System.out.println("Error occurred! Could not create output.txt");
        }
    }

}
