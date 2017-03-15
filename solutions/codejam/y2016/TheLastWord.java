

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @Description
 */
public class TheLastWord implements Runnable {

    private class Solver {

        Solver() {
            String input = scanner.nextLine();

            String output = "";
            output = Character.toString(input.charAt(0));
            for (int i = 1; i < input.length(); i++) {
                String s = Character.toString(input.charAt(i));
                String first = Character.toString(output.charAt(0));
                if (first.compareTo(s) > 0) {
                    output += s;
                } else {
                    output = s + output;
                }
            }
            printer.println(output);
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
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        new Thread(new TheLastWord()).start();
    }
}
