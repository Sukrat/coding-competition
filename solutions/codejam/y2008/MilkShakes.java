import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @Description
 */
public class MilkShakes implements Runnable {

    private class Solver {

        Solver() {
            int noOfMilkShake = scanner.nextInt();
            int noOfCustomer = scanner.nextInt();

            Integer[] milshakes = new Integer[noOfMilkShake + 1];
            Arrays.fill(milshakes, 0);
            List<Customer> customers = new ArrayList<>();
            for (int i = 0; i < noOfCustomer; i++) {
                int noOfPreference = scanner.nextInt();
                Customer cust = new Customer();
                for (int j = 0; j < noOfPreference; j++) {
                    int milkShakeId = scanner.nextInt();
                    int isMalted = scanner.nextInt();
                    if (isMalted == 0) {
                        cust.unmalted.add(milkShakeId);
                    } else {
                        cust.malted.add(milkShakeId);
                    }
                }
                customers.add(cust);
            }

            boolean possible = true;
            for (int i = 0; i < customers.size(); i++) {
                Customer cust = customers.get(i);
                if (cust.isSatisfy(milshakes)) {
                    continue;
                }
                if (cust.malted.size() == 1 && cust.size() == 1) {
                    milshakes[cust.malted.get(0)] = 1;
                    i = -1;
                } else if (cust.malted.size() == 0) {
                    possible = false;
                    break;
                } else if (cust.malted.size() > 0) {
                    milshakes[cust.malted.get(0)] = 1;
                    i = -1;
                }
            }
            if (possible) {
                printer.println(Arrays.stream(milshakes)
                        .map(m -> Integer.toString(m))
                        .skip(1)
                        .collect(Collectors.joining(" ")));
            } else {
                printer.println("IMPOSSIBLE");
            }
        }

        class Customer {

            private List<Integer> malted = new ArrayList<>();
            private List<Integer> unmalted = new ArrayList<>();

            public int size() {
                return malted.size() + unmalted.size();
            }

            public Customer() {
            }

            public boolean isSatisfy(Integer[] arr) {
                for (int i : unmalted) {
                    if (arr[i] == 0) {
                        return true;
                    }
                }

                for (int i : malted) {
                    if (arr[i] == 1) {
                        return true;
                    }
                }
                return false;
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
        new Thread(new MilkShakes()).start();
    }
}
