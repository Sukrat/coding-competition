

import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.Scanner;

/**
 * @Description
 */
public class BFF implements Runnable {

    private class Solver {

        private int[] bff;
        private int[] count;

        Solver() {
            int noOfChildren = scanner.nextInt();
            bff = new int[noOfChildren + 1];
            count = new int[noOfChildren + 1];
            for (int i = 1; i < bff.length; i++) {
                bff[i] = scanner.nextInt();
            }
            int maxSize = 0;
            for (int i = 1; i < bff.length; i++) {
                int c = getCount(i, i);
                if (c > maxSize) {
                    maxSize = c;
                    if (c == noOfChildren) {
                        break;
                    }
                }
            }
            printer.println(maxSize);
        }

        private int getCount(int start, int next) {
            int c = 0;
            if (count[next] > 0) {
                c = count[next];
            } else if (start == next) {
                c = 1 + getCount(start, bff[next]);
            } else if (start == bff[next]) {
                c = 1;
            } else if (next == bff[bff[next]]) {
                c = 2 + reverseCount(bff[next]);
                count[bff[next]] = c - 1;
            } else {
                c = 1 + getCount(start, bff[next]);
            }
            count[next] = c;
            return c;
        }

        private int reverseCount(int base) {
            int maxSize = 0;
            for (int i = 1; i < bff.length; i++) {
                int c = 0;
                if (bff[i] == base && bff[base] != i) {
                    c = 1 + reverseCount(i);
                }
                if (c > maxSize) {
                    maxSize = c;
                }
            }
            return maxSize;
        }

        class Node {

            int value;
            Node Bff;
            boolean done;

            public Node(int value) {
                this.value = value;
            }

            public Node(int value, Node Bff) {
                this.value = value;
                this.Bff = Bff;
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
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        new Thread(new BFF()).start();
    }
}
