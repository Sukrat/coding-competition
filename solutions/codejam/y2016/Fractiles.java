

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Description
 */
public class Fractiles implements Runnable {

    private final String IMPOSSIBLE = "IMPOSSIBLE";

    private class Solver {

        Solver() {
            int k = scanner.nextInt();
            int c = scanner.nextInt();
            int s = scanner.nextInt();
            StringBuilder builder = new StringBuilder();
            if (c * s < k) {
                printer.println(IMPOSSIBLE);
            } else {
                long ans = 0;
                int complex = 0;
                for (int i = 0; i < k; i += c) {
                    ans = 0;
                    complex = 0;
                    for (int j = i; j < i + c && j < k; j++) {
                        ans = ans * k + j;
                        complex++;
                    }
                    while (complex < c) {
                        ans *= k;
                        complex++;
                    }
                    builder.append(ans + 1).append(" ");
                }
                printer.println(builder.toString().trim());
            }
        }
    }

    private Scanner scanner;
    private PrintWriter printer;
    private final String path = "";

    @Override
    public void run() {
        try {
            String fileName = path + this.getClass().getCanonicalName().replace(".", "/");
            File inputFile = new File(fileName + ".in");
            File outputFile = new File(fileName + ".out");
            inputFile.createNewFile();
            outputFile.createNewFile();
            scanner = new Scanner(inputFile);
            printer = new PrintWriter(outputFile);
            int noOfTestCases = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < noOfTestCases; i++) {
                printer.write(String.format("Case #%1$d: ", (i + 1)));
                new Solver();
            }
            scanner.close();
            printer.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Thread(new Fractiles()).start();
    }
}
