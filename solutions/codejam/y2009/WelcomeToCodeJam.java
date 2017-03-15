

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @Description
 */
public class WelcomeToCodeJam {

    private static List<String> outputList;

    public static void main(String[] args) throws Exception {
        String fileName = "WelcomeToCodeJam";
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
                String input = "";
                input = scanner.nextLine();
                new WelcomeToCodeJam(input);
            }

            try (PrintWriter writer = new PrintWriter(fileName + ".out", "UTF-8")) {
                for (int i = 0; i < no; i++) {
                    String output = String.format("Case #%1$d: %2$04d", (i + 1), Integer.parseInt(outputList.get(i)));
                    writer.println(output);
                    System.out.println(output);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public WelcomeToCodeJam(String input) {
        String output = "";
        Integer solve = solve(input);
        outputList.add(solve.toString());
    }

    private int solve(String input) {
        String word = "welcome to code jam";
        List<Word> listToSearch = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            String charAt = Character.toString(input.charAt(i));
            if ("welcomtdjam ".contains(charAt)) {
                listToSearch.add(new Word(i, charAt));
            }
        }

        List<Word> wordList = new LinkedList<>();
        List<Word> tempList = new LinkedList<>();

        Count count = new Count();
        wordList.addAll(listToSearch.stream()
                .filter(c -> c.getLetter().equals("w"))
                .collect(Collectors.toList()));
        for (int i = 1; i < word.length(); i++) {
            String toSearch = Character.toString(word.charAt(i));
            count.reset();
            tempList.clear();
            wordList.stream()
                    .forEach(m -> {
                        List<Word> collect = listToSearch.stream()
                                .filter(n -> n.getLetter().equals(toSearch)
                                        && n.getIndex() > m.getIndex())
                                .collect(Collectors.toList());
                        count.add(collect.size());
                        tempList.addAll(collect);
                    });
            wordList = new LinkedList<>(tempList);
        }
        return count.result();
    }

}

class Word {

    private int index;

    private String letter;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Word(int index, String letter) {
        this.index = index;
        this.letter = letter;
    }
}

class Count {

    private int count = 0;

    public void add(int x) {
        count = (count + x) % 10000;
    }

    public int result() {
        return count;
    }

    public Count() {
        count = 0;
    }

    void reset() {
        count = 0;
    }
}
