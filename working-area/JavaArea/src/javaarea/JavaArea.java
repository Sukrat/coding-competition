
import java.io.PrintWriter;
import java.util.Scanner;

public class JavaArea {

    public static class Solver {

        public void solve() {
            //solution
        }
    }

    private static Scanner read;
    private static PrintWriter write;

    public static void main(String[] args) {
        boolean isTest = true;
        isTest = false;
        if (isTest) {
            read = new Scanner("1 50");
            write = new PrintWriter(System.out);
        } else {
            read = new Scanner(System.in);
            write = new PrintWriter(System.out);
        }
        Solver solver = new Solver();
        solver.solve();
        read.close();
        write.close();
    }
}
