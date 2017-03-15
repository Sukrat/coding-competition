

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description
 */
public class CoinJam implements Runnable {

    private class Solver {

        private BigInteger[] bigInteger = new BigInteger[9];
        private final int two = 2;
        private final int ten = 10;

        Solver() {
            int n = scanner.nextInt();
            int j = scanner.nextInt();
            printer.println();

            String binary = "1";
            for (int i = 0; i < n - 2; i++) {
                binary += "0";
            }
            binary += "1";

            for (int i = 0; i < bigInteger.length; i++) {
                bigInteger[i] = new BigInteger(binary, i + 2);
                System.out.println(bigInteger[i]);
            }

            for (int i = 0; i < j;) {
                String output = isDivisible();
                if (output != null) {
                    printer.println(bigInteger[0].toString(two) + " " + output);
                    i++;
                }
                nextInteger();
            }
        }

        private String isDivisible() {
            String output = "";
            for (int i = 0; i < bigInteger.length; i++) {
                BigInteger mod = bigInteger[i].mod(BigInteger.valueOf(i + 3));
                if (mod.equals(BigInteger.ZERO)) {
                    output += ((i + 3) + " ");
                } else {
                    return null;
                }
            }
            return output.trim();
        }

        private void nextInteger() {
            bigInteger[0] = bigInteger[0].add(BigInteger.valueOf(2));
            String binary = bigInteger[0].toString(2);
            for (int i = 1; i < bigInteger.length; i++) {
                bigInteger[i] = new BigInteger(binary, i + 2);
            }
        }

        private List<BigInteger> primeList = new LinkedList<>();

        private void createPrimeList() {
            for (int i = 2; i < 1000000; i++) {
                BigInteger valueOf = BigInteger.valueOf(i);
                if (valueOf.isProbablePrime(10)) {
                    primeList.add(valueOf);
                }
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
                printer.write(String.format("Case #%1$d:", (i + 1)));
                new Solver();
            }
            scanner.close();
            printer.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Thread(new CoinJam()).start();
    }
}
