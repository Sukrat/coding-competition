

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @Description
 */
public class FileFixIt implements Runnable {

    private class Solver {

        private final Folder EMPTY = new Folder("Empty");
        private final Folder root = new Folder("Root");

        Solver() {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < n; i++) {
                String filePath = scanner.nextLine();
                String[] split = filePath.split("/");
                Folder curr = root;
                for (int j = 1; j < split.length; j++) {
                    Folder folder = curr.getFolder(split[j]);
                    if (folder == EMPTY) {
                        folder = curr.createFolder(split[j]);
                    }
                    curr = folder;
                }
            }
            int count = 0;
            for (int i = 0; i < m; i++) {
                String filePath = scanner.nextLine();
                String[] split = filePath.split("/");
                Folder curr = root;
                for (int j = 1; j < split.length; j++) {
                    Folder folder = curr.getFolder(split[j]);
                    if (folder == EMPTY) {
                        folder = curr.createFolder(split[j]);
                        count++;
                    }
                    curr = folder;
                }
            }
            printer.println(count);
        }

        private class Folder {

            private final String name;
            private HashMap<String, Folder> map;

            public Folder(String folderName) {
                name = folderName;
                map = new HashMap<>();
            }

            public Folder getFolder(String name) {
                return map.getOrDefault(name, EMPTY);
            }

            public Folder createFolder(String name) {
                Folder folder = new Folder(name);
                map.put(name.toLowerCase(), folder);
                return folder;
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
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        new Thread(new FileFixIt()).start();
    }
}
