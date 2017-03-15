

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @Description
 */
public class Watersheds {

    private static List<String> outputList;

    public static void main(String[] args) throws Exception {
        String fileName = "Watersheds";
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
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                scanner.nextLine();
                String[] input = new String[x];
                for (int j = 0; j < input.length; j++) {
                    input[j] = scanner.nextLine();
                }
                new Watersheds(x, y, input);
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

    private List<Mountain> mountainList = new ArrayList<>();
    private int height;
    private int width;
    private char nameOfTheShed = 'a';
    private char[][] result;

    private Watersheds(int x, int y, String[] input) {
        height = x;
        width = y;
        int index = 0;
        for (int i = 0; i < height; i++) {
            String[] each = input[i].split(" ");
            for (int j = 0; j < each.length; j++) {
                int h = Integer.parseInt(each[j]);
                mountainList.add(new Mountain(index++, h, null));
            }
        }
        solve();
        index = 0;
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Mountain get = mountainList.get(index++);
                builder.append(get.getName()).append(" ");

            }
            builder.deleteCharAt(builder.length() - 1);
            builder.append("\n");
        }
        outputList.add(builder.deleteCharAt(builder.length() - 1).toString());
    }

    private void solve() {
        while (mountainList.stream()
                .anyMatch(m -> m.getName() == null)) {
            List<Mountain> sortedMountain = mountainList.stream()
                    //                    .sorted(Comparator.comparing(Mountain::getHeight).thenComparing(Mountain::getIndex))
                    .collect(Collectors.toList());

            for (Mountain mountain : sortedMountain) {
                if (mountain.isLabeled()) {
                    continue;
                }
                Mountain temp = getLowestNeighbour(mountain.getIndex());
                if (temp == null) {
                    mountain.setName(getNewNameOfTheShed());
                    continue;
                }
                Stack<Mountain> lowNeighbourList = new Stack<>();
                while (temp != null) {
                    lowNeighbourList.push(temp);
                    if (temp.isLabeled()) {
                        break;
                    }
                    temp = getLowestNeighbour(temp.getIndex());
                }
                Mountain sink = lowNeighbourList.pop();
                if (!sink.isLabeled()) {
                    sink.setName(getNewNameOfTheShed());
                }
                while (!lowNeighbourList.isEmpty()) {
                    lowNeighbourList.pop().setName(sink.getName());
                }
                mountain.setName(sink.getName());
            }
        }
    }

    private Mountain getLowestNeighbour(int index) {
        Mountain mountain = mountainList.get(index);
        Integer northIndex = getNorth(index);
        Integer westIndex = getWest(index);
        Integer eastIndex = getEast(index);
        Integer southIndex = getSouth(index);
        List<Mountain> neighbourList = new ArrayList<>(4);
        if (northIndex != null) {
            neighbourList.add(mountainList.get(northIndex));
        }
        if (westIndex != null) {
            neighbourList.add(mountainList.get(westIndex));
        }
        if (eastIndex != null) {
            neighbourList.add(mountainList.get(eastIndex));
        }
        if (southIndex != null) {
            neighbourList.add(mountainList.get(southIndex));
        }

        Optional<Mountain> findFirst = neighbourList.stream()
                .min(Comparator.comparing(Mountain::getHeight).thenComparing(Mountain::getIndex))
                .filter((m -> m.getHeight() < mountain.getHeight()));

        return findFirst.isPresent() ? findFirst.get() : null;
    }

    private Integer getNorth(int index) {
        int value = (index - width);
        return value >= 0 && value < mountainList.size() ? value : null;
    }

    private Integer getSouth(int index) {
        int value = (index + width);
        return value >= 0 && value < mountainList.size() ? value : null;
    }

    private Integer getWest(int index) {
        int line = index / width;
        int value = (index - 1);
        int newLine = value / width;
        return line == newLine && value >= 0 && value < mountainList.size() ? value : null;
    }

    private Integer getEast(int index) {
        int line = index / width;
        int value = (index + 1);
        int newLine = value / width;
        return line == newLine && value >= 0 && value < mountainList.size() ? value : null;
    }

    private char getNewNameOfTheShed() {
        return nameOfTheShed++;
    }
}

class Mountain {

    private int index;

    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private Character name;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Character getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public boolean isLabeled() {
        return name != null;
    }

    public Mountain(int postion, int height, Character name) {
        this.index = postion;
        this.height = height;
        this.name = name;
    }
}
