

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description
 */
public class ReverseWords {

    public static void main(String[] args) {
        run();
//        test();
    }

    private static void run() {
        try {
            Scanner scanner = new Scanner(Paths.get("ReverseWords.in"));
            int no = scanner.nextInt();
            scanner.nextLine();
            String[] input = new String[no];
            for (int i = 0; i < input.length; i++) {
                input[i] = scanner.nextLine();
            }
            ReverseWords reverseWords = new ReverseWords(no, input);
        } catch (IOException ex) {

        }
    }

    private static void test() {
        ReverseWords reverseWords = new ReverseWords(3, new String[]{
            "this is a test", "foobar", "all your base"
        });
    }

    private List<String> outputList;

    public ReverseWords(int no, String[] inputArray) {
        outputList = new ArrayList<>(no);

        for (int i = 0; i < inputArray.length; i++) {
            reverse(inputArray[i]);
        }

        try (PrintWriter writer = new PrintWriter("ReverseWords.txt", "UTF-8")) {
            for (int i = 0; i < outputList.size(); i++) {
                String output = String.format("Case #%1$d: %2$s", (i + 1), outputList.get(i));
                writer.println(output);
                System.out.println(output);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {

        }
    }

    private void reverse(String input) {
        StringBuilder builder = new StringBuilder();
        String[] split = input.split(" ");
        for (int i = 0, index = split.length - 1; i < split.length; i++, index--) {
            builder.append(split[index]).append(" ");
        }
        outputList.add(builder.toString().trim());
    }

}
