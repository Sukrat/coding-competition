

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @Description
 */
public class GettingTheDigits implements Runnable {

    private class Solver {

        private int[] count = new int[26];
        private int[] tempCount = new int[26];

        Solver() {
            String line = scanner.nextLine();
            String[] values = new String[]{"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
            for (int i = 0; i < line.length(); i++) {
                char charAt = line.charAt(i);
                count[index(charAt)]++;
            }
            reset();
            String output = "";
            int i = 0;
            while (i < values.length) {
                String number = values[i];
                if (isThere(number)) {
                    subtract(number);
                    output += Integer.toString(i);
                } else {
                    i++;
                }
            }
            printer.println(output);
        }

        private int index(char c) {
            return (c - 'A');
        }

        private boolean isThere(String s) {
            for (int i = 0; i < s.length(); i++) {
                char charAt = s.charAt(i);
                if (tempCount[index(charAt)] > 0) {
                    tempCount[index(charAt)]--;
                } else {
                    reset();
                    return false;
                }
            }
            reset();
            return true;
        }

        private void reset() {
            for (int i = 0; i < count.length; i++) {
                tempCount[i] = count[i];
            }
        }

        private void subtract(String s) {
            for (int i = 0; i < s.length(); i++) {
                char charAt = s.charAt(i);
                count[index(charAt)]--;
            }
            reset();
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
        new Thread(new GettingTheDigits()).start();
    }
}
