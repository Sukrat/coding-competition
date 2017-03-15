

import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @Description
 */
public class CountingSheep implements Runnable {

    private class Solver {

        Solver() {
            long n = scanner.nextLong();
            HashSet<Integer> set = new HashSet<>();
            long currNum = 0;
            long i = 1;
            if (n > 0) {
                do {
                    currNum = n * (i++);
                    long digits = currNum;
                    while (digits > 0) {
                        int mod = (int) (digits % 10);
                        set.add(mod);
                        digits /= 10;
                    }
                } while (set.size() != 10);
                printer.println(currNum);
            } else {
                printer.println("INSOMNIA");
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
                printer.print(String.format("Case #%1$d: ", (i + 1)));
                new Solver();
            }
            scanner.close();
            printer.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Thread(new CountingSheep()).start();
    }
}
