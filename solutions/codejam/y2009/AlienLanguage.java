

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @Description
 */
public class AlienLanguage {

    private static List<String> outputList;

    public static void main(String[] args) throws Exception {
        String fileName = "AlienLanguage";
        File inputFile = new File(fileName + ".in");
        File outputFile = new File(fileName + ".out");
        try {
            inputFile.createNewFile();
            outputFile.createNewFile();

            Scanner scanner = new Scanner(Paths.get(fileName + ".in"));
//            Scanner scanner = new Scanner(System.in);
            int wordSize = scanner.nextInt();
            int dictSize = scanner.nextInt();
            int noOfCases = scanner.nextInt();
            scanner.nextLine();
            String[] dict = new String[dictSize];
            for (int i = 0; i < dictSize; i++) {
                dict[i] = scanner.nextLine();
            }
            String[] cases = new String[noOfCases];
            for (int i = 0; i < noOfCases; i++) {
                cases[i] = scanner.nextLine();
            }
            new AlienLanguage(wordSize, dictSize, noOfCases, dict, cases);

            try (PrintWriter writer = new PrintWriter(fileName + ".out", "UTF-8")) {
                for (int i = 0; i < outputList.size(); i++) {
                    String output = String.format("Case #%1$d: %2$s", (i + 1), outputList.get(i));
                    writer.println(output);
                    System.out.println(output);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private List<String> dictionary = new ArrayList<>();

    public AlienLanguage(int wordSize, int dictSize, int noOfCases, String[] dict, String[] cases) {
        outputList = new ArrayList<>(noOfCases);
        dictionary = Arrays.asList(dict);

        for (int i = 0; i < noOfCases; i++) {
            char[] word = cases[i].toCharArray();
            Stream<String> stream = dictionary.stream();
            int wordIndex = 0;
            for (int j = 0; j < word.length; j++) {
                List<Character> ls = new ArrayList<>(10);
                if (word[j] == '(') {
                    while (word[++j] != ')') {
                        ls.add(word[j]);
                    }
                } else {
                    ls.add(word[j]);
                }
                stream = filterBy(stream, wordIndex, ls);
                wordIndex++;
            }
            outputList.add(Long.toString(stream.count()));
        }
    }

    private Stream<String> filterBy(Stream<String> stream, int index, List<Character> c) {
        return stream
                .filter(m -> c.stream()
                        .anyMatch(n -> n.equals(m.charAt(index))));
    }

}
