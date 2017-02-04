import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Collections;
public class JavaArea {

    public static class Solver {
        private Scanner read;
        private PrintWriter write;

        public Solver(Scanner scanner, PrintWriter printWriter) {
            read = scanner;
            write = printWriter;
        }
        Collections.shu
        public void solve() {
            //solution
            ArrayList list = new ArrayList();
            list.add(1);list.add(2);list.add(3);
        }
    }

    public static void main(String[] args) {
        boolean isTest = true;
        Scanner scanner;
        PrintWriter printWriter;
        if (isTest) {
            scanner = new Scanner("");
            printWriter = new PrintWriter(System.out);
        } else {
            scanner = new Scanner(System.in);
            printWriter = new PrintWriter(System.out);
        }
        Solver solver = new Solver(scanner, printWriter);
        solver.solve();
        scanner.close();
        printWriter.close();
    }
}