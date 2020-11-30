import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.math.BigInteger;

/**
 * CECS 328 PA5: hobbits
 *
 * @author Tony Ha
 */
public class Main {

    public static void main(String[] args) {
        // Input phase
        try {
            Scanner inFile = new Scanner(new File("input1.txt"));
            // TODO Input: The loop needs to construct floating pad or convert the string into BigInteger
            do {
                String line = inFile.nextLine();
                System.out.println(line);
            } while (inFile.hasNextLine());
        } catch (IOException e) {
            System.out.println("Input file does not exist! Program terminated.");
        }
        // Output phase
        try {
            PrintWriter outFile = new PrintWriter("output.txt");
        } catch (IOException e) {
            System.out.println("Error occurred! Could not create output.txt");
        }
    }

}
