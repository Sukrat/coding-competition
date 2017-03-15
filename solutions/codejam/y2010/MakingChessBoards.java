

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Description
 */
public class MakingChessBoards implements Runnable {

    private class Solver {

        private final int f = 4;
        private final int taken = -1;

        Solver() {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            scanner.nextLine();

            int[][] brd = new int[m][n];
            for (int i = 0; i < m; i++) {
                String[] input = scanner.nextLine().split("");
                for (int j = 0; j < input.length; j++) {
                    String[] b = convertToBinary(input[j]).split("");
                    for (int k = 0; k < b.length; k++) {
                        brd[i][(j * f) + k] = Integer.parseInt(b[k]);
                    }
                }
            }

            List<String> strList = new ArrayList<>();
            int maxChessSize = Integer.min(m, n);
            boolean isFound = false;
            int noOfBlocksTaken = 0;
            for (int size = maxChessSize; size > 1; size--) {
                int count = 0;
                for (int i = 0; i + size - 1 < m; i++) {
                    for (int j = 0; j + size - 1 < n; j++) {
                        if(brd[i][j]==taken){
                            continue;
                        }
                        if (isChessThere(brd, i, j, size)) {
                            fill(brd, i, j, size);
                            count++;
                            j += size - 1;
                            noOfBlocksTaken += (size * size);
                        }
                    }
                }
                if (count > 0) {
                    strList.add(String.format("%1$d %2$d", size, count));
                }
            }
            int noOfOnes = (m * n) - noOfBlocksTaken;
            if (noOfOnes > 0) {
                strList.add(String.format("%1$d %2$d", 1, noOfOnes));
            }
            printer.println(strList.size());
            for (int i = 0; i < strList.size(); i++) {
                printer.println(strList.get(i));
            }
        }

        private void fill(int[][] brd, int x, int y, int size) {
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    brd[i][j] = taken;
                }
            }
        }

        private boolean isChessThere(int[][] brd, int x, int y, int size) {
            if (brd[x][y] == taken) {
                return false;
            }

            int rowPrev = brd[x][y];
            for (int i = x; i < x + size; i++) {
                int colPrev = rowPrev;
                for (int j = y; j < y + size; j++) {
                    int curr = brd[i][j];
                    if (curr == taken) {
                        return false;
                    } else if (curr != colPrev) {
                        return false;
                    }
                    colPrev = getNext(curr);
                }
                rowPrev = getNext(rowPrev);
            }
            return true;
        }

        private String convertToBinary(String input) {
            return String.format("%1$04d", Integer.parseInt(
                    Integer.toBinaryString(
                            Integer.parseInt(input, 16))));
        }

        private int getNext(int curr) {
            return curr == 1 ? 0 : 1;
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
        new Thread(new MakingChessBoards()).start();
    }
}
