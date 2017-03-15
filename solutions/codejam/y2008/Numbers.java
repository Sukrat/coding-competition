

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

/**
 * @Description
 */
public class Numbers implements Runnable {

    private static final double orig = Math.sqrt(5);

    private class Solver {

        Solver() {
            long n = scanner.nextLong();
            Content start = new Content(3, 1);
            Content x = start;
            Content y = null;
            if (n == 0) {
                printer.println("1");
            } else {
                while (n > 1) {
                    if (n % 2 == 0) {
                        x = x.multiply(x);
                        n /= 2;
                    } else {
                        y = x.multiply(y);
                        x = x.multiply(x);
                        n = (n - 1) / 2;
                    }
                }
                x = x.multiply(y);
                long ans = (2 * x.x + 999) % 1000;
                printer.println(String.format("%1$03d", ans));
            }
        }

        class Content {

            private long x;
            private long a;

            public Content(long x, long y) {
                this.x = x;
                this.a = y;
            }

            public Content multiply(Content c) {
                if (c == null) {
                    return new Content(x % 1000, a % 1000);
                }
                long xx = (x * c.x) + (a * c.a * 5);
                long yy = (x * c.a) + (a * c.x);
                return new Content(xx % 1000, yy % 1000);
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
        new Thread(new Numbers()).start();
    }
}
