

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @Description
 */
public class RevengeofthePancakes implements Runnable {

    private class Solver {

        private final char plus = '+';
        private final char minus = '-';
        private long count = 0;

        Solver() {
            String input = scanner.nextLine();
            char[] stack = input.toCharArray();
            count = 0;
            char previous = minus;
            for (int i = 0; i < stack.length; i++) {
                if (stack[i] == minus) {
                    if (previous == minus) {
                        while ((i + 1) < stack.length && stack[i + 1] == minus) {
                            i++;
                        }
                        flip(stack, i);
                    } else {
                        int plusIndex = i - 1;
                        while ((i + 1) < stack.length && stack[i + 1] == minus) {
                            i++;
                        }
                        flip(stack, plusIndex);
                        flip(stack, i);
                    }
                    previous = plus;
                } else {
                    previous = plus;
                }
            }
            printer.println(count);
        }

        private void flip(char[] stack, int k) {
            for (int i = 0; i <= k; i++) {
                stack[i] = stack[i] == plus ? minus : plus;
            }
            count++;
        }
    }

    private Scanner scanner;
    private PrintWriter printer;
    private final String path = "";

    @Override
    public void run() {
        try {
            String fileName = path + this.getClass().getCanonicalName().replace(".", "/");;
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
        new Thread(new RevengeofthePancakes()).start();
    }
}
