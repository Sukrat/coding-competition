

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @Description
 */
public class SampleICPC {

    private class Solver {

        Solver() {

        }
    }

    private Scanner scanner;
    private PrintWriter printer;

    private void deploy() {
        boolean isTest = true;
//        isTest = false;
        if (isTest) {
            scanner = new Scanner("");
        } else {
            scanner = new Scanner(System.in);
        }
    }

    public void run() {
        try {
            deploy();
            printer = new PrintWriter(System.out);
            new Solver();
            scanner.close();
            printer.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        (new SampleICPC()).run();
    }
}
