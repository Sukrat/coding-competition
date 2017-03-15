

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * @Description
 */
public class StoreCredit {

    private static List<String> outputList;

    public static void main(String[] args) throws Exception {
        String fileName = "StoreCredit";
        File inputFile = new File(fileName + ".in");
        File outputFile = new File(fileName + ".out");
        try {
            inputFile.createNewFile();
            outputFile.createNewFile();

            Scanner scanner = new Scanner(inputFile);
            int no = scanner.nextInt();
            scanner.nextLine();
            outputList = new ArrayList<>(no);
            for (int i = 0; i < no; i++) {
                String[] input = new String[3];
                for (int j = 0; j < input.length; j++) {
                    input[j] = scanner.nextLine();
                }
                new StoreCredit(input);
            }

            try (PrintWriter writer = new PrintWriter(outputFile, "UTF-8")) {
                for (int i = 0; i < no; i++) {
                    String output = String.format("Case #%1$d: %2$s", (i + 1), outputList.get(i));
                    writer.println(output);
                    System.out.println(output);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public StoreCredit(String[] input) {
        String output = "";
        int creditToReach = Integer.parseInt(input[0]);
        int numberOfItems = Integer.parseInt(input[1]);
        String[] items = input[2].split(" ");

        List<Int> priceList = new ArrayList<>(numberOfItems);
        for (int i = 0; i < numberOfItems; i++) {
            priceList.add(new Int(i + 1, Integer.parseInt(items[i])));
        }

        for (Int elemFirst : priceList) {
            int diff = creditToReach - elemFirst.getPrice();
            if (diff <= 0) {
                continue;
            }
            Optional<Int> findFirst = priceList.stream()
                    .filter((x) -> (x.getPrice() == diff && x.getIndex() != elemFirst.getIndex()))
                    .findFirst();
            if (findFirst.isPresent()) {
                if (elemFirst.getIndex() > findFirst.get().getIndex()) {
                    output = findFirst.get().getIndex() + " " + elemFirst.getIndex();
                } else {
                    output = elemFirst.getIndex() + " " + findFirst.get().getIndex();
                }
                break;
            }
        }
        outputList.add(output);
    }

    class Int {

        private int index;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        private int price;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public Int(int index, int price) {
            this.index = index;
            this.price = price;
        }

    }

}
