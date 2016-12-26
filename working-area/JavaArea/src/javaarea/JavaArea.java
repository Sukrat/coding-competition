import java.io.PrintWriter;
import java.util.Scanner;

public class JavaArea {

    public static class Solver {
        private Scanner read;
        private PrintWriter write;

        public Solver(Scanner scanner, PrintWriter printWriter) {
            read = scanner;
            write = printWriter;
        }

        public void solve() {
            //solution
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