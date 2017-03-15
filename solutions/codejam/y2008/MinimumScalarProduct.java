

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @Description
 */
public class MinimumScalarProduct {

    private static List<String> outputList;

    public static void main(String[] args) throws Exception {
        String fileName = "MinimumScalarProduct";
        File inputFile = new File(fileName + ".in");
        File outputFile = new File(fileName + ".out");
        try {
            inputFile.createNewFile();
            outputFile.createNewFile();

            Scanner scanner = new Scanner(Paths.get(fileName + ".in"));
//            Scanner scanner = new Scanner(System.in);
            int no = scanner.nextInt();
            scanner.nextLine();
            outputList = new ArrayList<>(no);
            for (int i = 0; i < no; i++) {
                String[] input = new String[3];
                for (int j = 0; j < input.length; j++) {
                    input[j] = scanner.nextLine();
                }
                new MinimumScalarProduct(input);
            }

            try (PrintWriter writer = new PrintWriter(fileName + ".out", "UTF-8")) {
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

    public MinimumScalarProduct(String[] input) {
        String output = "";
        int no = Integer.parseInt(input[0]);
        String[] xStrArr = input[1].split(" ");
        String[] yStrArr = input[2].split(" ");

        List<Integer> xVector = new ArrayList<>(no);
        List<Integer> yVector = new ArrayList<>(no);
        for (int i = 0; i < no; i++) {
            xVector.add(Integer.parseInt(xStrArr[i]));
            yVector.add(Integer.parseInt(yStrArr[i]));
        }

        Collections.sort(xVector);
        Collections.sort(yVector, (x, y) -> Integer.compare(y, x));

        output = multiply(xVector, yVector).toString();
        outputList.add(output);
    }

    private BigInteger multiply(List<Integer> x, List<Integer> y) {
        BigInteger sum = new BigInteger("0");
        for (int i = 0; i < x.size(); i++) {
            BigInteger strX = new BigInteger(Integer.toString(x.get(i)));
            BigInteger strY = new BigInteger(Integer.toString(y.get(i)));
            sum = sum.add(strX.multiply(strY));
        }
        return sum;
    }
}
