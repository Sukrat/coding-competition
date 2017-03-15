

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @Description
 */
public class RopeIntranet implements Runnable {

    private class Solver {

        Solver() {
            int noOfInputs = scanner.nextInt();
            scanner.nextLine();
            List<Connection> connList = new ArrayList<>(noOfInputs);
            for (int i = 0; i < noOfInputs; i++) {
                int y1 = scanner.nextInt();
                int y2 = scanner.nextInt();
                scanner.nextLine();
                connList.add(new Connection(y1, y2, y2 - y1));
            }
            Collections.sort(connList, (x, y) -> Integer.compare(x.getLeft(), y.getLeft()));
            int noOfIntersection = 0;
            for (int i = 0; i < connList.size() - 1; i++) {
                for (int j = i; j < connList.size(); j++) {
                    boolean intersecting = connList.get(i).isIntersecting(connList.get(j));
                    if (intersecting) {
                        noOfIntersection++;
                    }
                }
            }
            outputList.add(Integer.toString(noOfIntersection));
        }
    }

    private class Connection {

        private int left;

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        private int right;

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        private double slope;

        public double getSlope() {
            return slope;
        }

        public void setSlope(double slope) {
            this.slope = slope;
        }

        public Connection(int left, int right, double slope) {
            this.left = left;
            this.right = right;
            this.slope = slope;
        }

        public boolean isIntersecting(Connection c) {
            double x = (double) (c.left - left) / (double) (slope - c.slope);
            return x > 0 && x < 1;
        }
    }

    private Scanner scanner;
    private PrintWriter printer;
    private final String path = "inputFiles/";
    private static List<String> outputList;

    @Override
    public void run() {
        try {
            String fileName = path + this.getClass().getSimpleName();
            File inputFile = new File(fileName + ".in");
            File outputFile = new File(fileName + ".out");
            inputFile.createNewFile();
            outputFile.createNewFile();
            scanner = new Scanner(inputFile);
            int noOfTestCases = scanner.nextInt();
            scanner.nextLine();
            outputList = new ArrayList<>(noOfTestCases);
            for (int i = 0; i < noOfTestCases; i++) {
                new Solver();
            }
            scanner.close();
            printer = new PrintWriter(outputFile);
            try (PrintWriter writer = new PrintWriter(outputFile)) {
                for (int i = 0; i < noOfTestCases; i++) {
                    String output = String.format("Case #%1$d: %2$s", (i + 1), outputList.get(i));
                    writer.println(output);
                    System.out.println(output);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        new Thread(new RopeIntranet()).run();
    }
}
