

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @Description
 */
public class T9 {

    private static List<String> outputList;

    public static void main(String[] args) throws Exception {
        String fileName = "T9";
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
                String[] input = new String[1];
                for (int j = 0; j < input.length; j++) {
                    input[j] = scanner.nextLine();
                }
                new T9(input);
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

    public T9(String[] input) {
        String output = "";
        char[] message = input[0].toCharArray();

        ConvertT9 converter = new ConvertT9();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length; i++) {
            builder.append(converter.getCode(message[i]));
        }
        output = builder.toString().trim();
        outputList.add(output);
    }

}

class ConvertT9 {

    private static HashMap<Character, String> mapping = new HashMap<Character, String>() {
        {
            put('a', "2");
            put('b', "22");
            put('c', "222");
            put('d', "3");
            put('e', "33");
            put('f', "333");
            put('g', "4");
            put('h', "44");
            put('i', "444");
            put('j', "5");
            put('k', "55");
            put('l', "555");
            put('m', "6");
            put('n', "66");
            put('o', "666");
            put('p', "7");
            put('q', "77");
            put('r', "777");
            put('s', "7777");
            put('t', "8");
            put('u', "88");
            put('v', "888");
            put('w', "9");
            put('x', "99");
            put('y', "999");
            put('z', "9999");
            put(' ', "0");
        }
    };

    private char previousChar = '\0';

    public String getCode(char c) {
        String get = mapping.get(Character.toLowerCase(c));
        char charAt = get.charAt(0);
        if (previousChar == charAt) {
            get = " " + get;
        }
        previousChar = charAt;
        return get;
    }

    public ConvertT9() {
    }

}
