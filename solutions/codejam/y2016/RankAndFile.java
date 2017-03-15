

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @Description
 */
public class RankAndFile implements Runnable {

    private class Solver {

        Solver() {
            int n = scanner.nextInt();
            scanner.nextLine();
            Grid grid = new Grid(n);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 2 * n - 1; i++) {
                list.add(scanner.nextLine());
            }
            Collections.sort(list, (x, y) -> x.compareTo(y));
            
            list.get(0);
            
            for (int i = 1; i < list.size(); i++) {
                String get = list.get(i);

            }
            System.out.println(list);
        }

        class Grid {

            private int[][] row;
            private int[][] col;

            public Grid(int n) {
                row = new int[n][n];
                col = new int[n][n];
            }

            public void addRow(String row) {
                row.split(" ");
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
        new Thread(new RankAndFile()).start();
    }
}
