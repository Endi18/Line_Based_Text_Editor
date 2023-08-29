import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class Project1 {
    static LinkedList<String> List = new LinkedList<>();
    static File file = new File("src/Files/input02.txt");
    static int currentLine = 1;
    static Scanner input = new Scanner(System.in);
    static String command;
    static int toggle = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        Scanner in = new Scanner(file);

        while (in.hasNext()) {
            String container = br.readLine();

            if (container == null)
                break;

            List.add(container);
        }
        in.close();
        br.close();
        while (true) {

            while (currentLine < 1 || currentLine > List.size()) {
                System.out.print("\nReposition the currentLine: ");
                currentLine = input.nextInt();
            }

            if (file.exists() && file != null) {
                help();
            } else {
                System.out.println("The file does not exist.");
                break;
            }
        }
    }

    static public void help() throws IOException {
        System.out.printf("%-30.30s  %s%n", "Commands", "Function");
        System.out.print("---------------------------------------------------------------------\n");
        System.out.printf("%-30.30s  %s%n", "1", "Go to the Top");
        System.out.printf("%-30.30s  %s%n", "a", "Add text after current line until . on its own line");
        System.out.printf("%-30.30s  %s%n", "d", "Delete current line");
        System.out.printf("%-30.30s  %s%n", "dr num num", "Delete several lines");
        System.out.printf("%-30.30s  %s%n", "f name", "Change name of current file (for next write)");
        System.out.printf("%-30.30s  %s%n", "g num", "Go to a numbered line");
        System.out.printf("%-30.30s  %s%n", "h", "Get help");
        System.out.printf("%-30.30s  %s%n", "i", "Like append, but add lines before current line");
        System.out.printf("%-30.30s  %s%n", "m num", "Move current line after some other line");
        System.out.printf("%-30.30s  %s%n", "mr num num num", "Move several lines as a unit after some other line");
        System.out.printf("%-30.30s  %s%n", "n", "Toggle whether line numbers are displayed");
        System.out.printf("%-30.30s  %s%n", "pr num num", "Print several lines");
        System.out.printf("%-30.30s  %s%n", "p", "Print current line");
        System.out.printf("%-30.30s  %s%n", "pa", "Print all lines");
        System.out.printf("%-30.30s  %s%n", "q!", "Abort without write");
        System.out.printf("%-30.30s  %s%n", "r name", "Read and paste another file into current file");
        System.out.printf("%-30.30s  %s%n", "s text text", "Substitute text with other text");
        System.out.printf("%-30.30s  %s%n", "t num", "Copy current line to after some other line");
        System.out.printf("%-30.30s  %s%n", "tr num num num", "Copy several lines after some other line");
        System.out.printf("%-30.30s  %s%n", "w", "Write file to disk");
        System.out.printf("%-30.30s  %s%n", "x!", "Exit with write");
        System.out.printf("%-30.30s  %s%n", "$", "Go to the last line");
        System.out.printf("%-30.30s  %s%n", "-", "Go up one line");
        System.out.printf("%-30.30s  %s%n", "+", "Go down one line");
        System.out.printf("%-30.30s  %s%n", "=", "Print current line number");
        System.out.printf("%-30.30s  %s%n", "/ text", "Search forward for a pattern");
        System.out.printf("%-30.30s  %s%n", "? text", "Search backward for a pattern");
        System.out.printf("%-30.30s  %s%n", "#", "Print number of lines and characters in file");
        System.out.print("----------------------------------------------------------------------\n");
        System.out.print("Choose a command as above: ");
        command = input.next();
        commands(command);
    }

    static public void commands(String command) throws IOException {
        int line, line1, line2, line3;
        String text1, text2, name;

        switch (command) {
            case "1" -> goToTheTop();
            case "a", "A" -> addText();
            case "d", "D" -> deleteCurrentLine();
            case "dr", "DR", "Dr", "dR" -> {
                System.out.print("Choose the line that you want to start deleting: ");
                line1 = input.nextInt();
                System.out.print("Choose the line that you want to stop deleting: ");
                line2 = input.nextInt();
                deleteSeveralLines(line1, line2);
            }
            case "f", "F" -> {
                System.out.print("Choose the name of the file: ");
                input.nextLine();
                name = input.nextLine();
                renameFile(name);
            }
            case "g", "G" -> {
                System.out.print("Choose the line you want to go: ");
                line = input.nextInt();
                goToANumberedLine(line);
            }
            case "h", "H" -> help();
            case "i", "I" -> {
                System.out.print("Choose the text you want to enter before currentLine ");
                input.nextLine();
                text1 = input.nextLine();
                enterTextBeforeCurrentLine(text1);
            }
            case "m", "M" -> {
                System.out.print("Choose the line that you want to move the currentLine: ");
                line = input.nextInt();
                moveAndPaste(line);
            }
            case "mr", "MR", "Mr", "mR" -> {
                System.out.print("Choose the line that you want to start copying: ");
                line1 = input.nextInt();
                System.out.print("Choose the line that you want to stop copying: ");
                line2 = input.nextInt();
                System.out.print("Choose the line that you want to move the text after that line: ");
                line3 = input.nextInt();
                cutAndPasteSeveralLines(line1, line2, line3);
            }
            case "n", "N" -> toggle();
            case "pr", "PR", "Pr", "pR" -> {
                System.out.print("Enter the line that you want to start printing: ");
                line1 = input.nextInt();
                System.out.print("Enter the line that you want to stop printing: ");
                line2 = input.nextInt();
                printSeveralLines(line1, line2);
            }
            case "p", "P" -> printCurrentLine();
            case "pa", "PA", "Pa", "pA" -> printAllLines();
            case "q!", "Q!" -> {
                System.out.println("The program is terminated");
                System.exit(0);
            }
            case "r", "R" -> {
                System.out.print("Enter name of the file: ");
                input.nextLine();
                name = input.nextLine();
                readAndPasteFile(name);
            }
            case "s", "S" -> {
                System.out.print("Enter the text that you want to substitute: ");
                input.nextLine();
                text1 = input.nextLine();
                System.out.print("Enter the text that will be substituted: ");
                text2 = input.nextLine();
                List.substituteV3(text1, text2);
            }
            case "t", "T" -> {
                System.out.print("Choose the line that you want to paste the text after that line: ");
                line = input.nextInt();
                copyCurrentLine(line);
            }
            case "tr", "TR", "Tr", "tR" -> {
                System.out.print("Choose the line that you want to start copying: ");
                line1 = input.nextInt();
                System.out.print("Choose the line that you want to stop copying: ");
                line2 = input.nextInt();
                System.out.print("The text will be pasted after line: ");
                line3 = input.nextInt();
                copyAndPasteSeveralLines(line1, line2, line3);
            }
            case "w", "W" -> write();
            case "x!", "X!" -> {
                write();
                System.out.println("The program is terminated");
                System.exit(0);
            }
            case "$" -> goToTheLastLine();
            case "-" -> oneLineUp();
            case "+" -> oneLineDown();
            case "=" -> System.out.println("The current line is: " + currentLine);
            case "/" -> {
                input.nextLine();
                System.out.print("Enter a pattern: ");
                text1 = input.nextLine();
                if (currentLine == 1)
                    List.searchForward(0, text1);
                else
                    List.searchForward(currentLine - 1, text1);
            }
            case "?" -> {
                input.nextLine();
                System.out.print("Enter a pattern: ");
                text1 = input.nextLine();
                if (currentLine == 1)
                    List.searchBackward(0, text1);
                else
                    List.searchBackward(currentLine - 1, text1);
            }
            case "#" -> System.out.println(
                    "There are: " + List.size() + " lines and there are " + List.numberOfCharacters() + " characters.");
            default -> {
                System.out.println("Wrong inputs");
                help();
            }
        }
    }

    // 1
    static public void goToTheTop() {

        if (currentLine == 1) {
            System.out.println("You are on the top");
        } else {
            System.out.println("Now, you are on the top");
        }
        currentLine = 1;
    }

    // a
    static public void addText() {

        System.out.print("Add text that you want until you click .(dot): ");
        input.nextLine();
        String name = input.nextLine();

        if (name.contains(".")) {
            int j = name.indexOf(".");

            StringBuilder a = new StringBuilder();

            for (int i = 0; i <= j; i++) {
                a.append(name.charAt(i));
            }
            List.add(currentLine, a.toString());
            System.out.println("It is added");

        }
        else
            System.out.println("It does contain dot so we cannot execute this command");
    }

    // d
    static public void deleteCurrentLine() {

        if (currentLine > 0 && currentLine <= List.size()) {
            List.remove(currentLine - 1);
            System.out.println("Removed");
        } else
            System.out.println("Reposition your pointer");
    }

    // dr
    static public void deleteSeveralLines(int line1, int line2) {

        if ((line1 > 0 && line1 <= List.size()) && (line2 > 0 && line2 <= List.size())) {
            if (line1 < line2) {
                List.remove(line1 - 1, line2 - 1);
                System.out.println("Removed");
            }

            else if (line2 < line1) {
                List.remove(line2 - 1, line1 - 1);
                System.out.println("Removed");
            } else {
                List.remove(line2 - 1);
                System.out.println("Removed");
            }

        } else
            System.out.println("You entered wrong lines");
    }

    // g
    static public void goToANumberedLine(int line) {

        if (line > 0 && line <= List.size()) {
            currentLine = line;
            System.out.println("The current line is: " + currentLine + " and the line contains: ");
            List.printASpecificLine(line - 1);
        } else
            System.out.println("Invalid line");
    }

    // m
    static public void moveAndPaste(int line) {

        if (line > 0 && line <= List.size()) {
            List.copy(line, currentLine - 1);
            List.remove(currentLine - 1);
            System.out.println("Removed");
        } else
            System.out.println("Wrong inputs");
    }

    // mr
    static public void cutAndPasteSeveralLines(int line1, int line2, int line3) {

        if ((line1 > 0 && line1 <= List.size()) && (line2 > 0 && line2 <= List.size())
                && (line3 > 0 && line3 <= List.size())) {
            List.cutAndPaste(line1 - 1, line2 - 1, line3 - 1);
        } else
            System.out.println("Wrong inputs");
    }

    // i
    static public void enterTextBeforeCurrentLine(String text) {

        if (currentLine == 1) {
            List.add(0, text);
        } else
            List.add(currentLine - 2, text);
    }

    // n
    public static void toggle() {

        if (toggle == 0) {
            toggle = 1;
            List.printWithIndex();
        } else if (toggle == 1) {
            toggle = 0;
            List.showList();
        }
    }

    // pr
    static public void printSeveralLines(int line1, int line2) {

        if (toggle == 0) {

            if (line1 > 0 && line2 <= List.size()) {
                if (line1 > line2)
                    List.printSomeLines(line2 - 1, line1 - 1);

                else if (line1 < line2)
                    List.printSomeLines(line1 - 1, line2 - 1);
                else
                    List.printSomeLines(line1 - 1, line1 - 1);

            } else
                System.out.println("Entered wrong numbers");
        } else if (toggle == 1) {
            if (line1 > 0 && line2 <= List.size()) {
                if (line1 > line2)
                    List.printSomeLinesWithIndex(line2 - 1, line1 - 1);

                else if (line1 < line2)
                    List.printSomeLinesWithIndex(line1 - 1, line2 - 1);

                else
                    List.printSomeLinesWithIndex(line1 - 1, line1 - 1);

            } else
                System.out.println("Entered wrong numbers");
        }

    }

    // p
    static public void printCurrentLine() {

        if (toggle == 0) {
            if (currentLine > 0 && currentLine <= List.size())
                List.printASpecificLine(currentLine - 1);
            else
                System.out.println("Reposition the cursor");
        } else if (toggle == 1) {
            if (currentLine > 0 && currentLine <= List.size())
                List.printASpecificLineWithIndex(currentLine - 1);
            else
                System.out.println("Reposition the cursor");
        }
    }

    // t
    static public void copyCurrentLine(int line) {

        if (line > 0 && line <= List.size()) {
            List.copy(line, currentLine - 1);
            System.out.println("The line is pasted");
        } else
            System.out.println("Wrong inputs");
    }

    // tr
    static public void copyAndPasteSeveralLines(int ind1, int ind2, int ind3) {
        if ((ind1 > 0 && ind1 <= List.size()) && (ind2 > 0 && ind2 <= List.size())
                && (ind3 > 0 && ind3 <= List.size())) {
            List.copyAndPaste(ind1 - 1, ind2 - 1, ind3 - 1);
            System.out.println("The lines is pasted");
        } else
            System.out.println("Wrong inputs");
    }

    // w
    static public void write() {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < List.size(); i++) {
                write.write(List.get(i));
                write.newLine();
            }

            System.out.println("Done");
            write.flush();
            write.close();
        } catch (IOException e) {
            System.out.println("IO Exception Error");
        }
    }

    // $
    static public void goToTheLastLine() {

        if (currentLine == List.size()) {
            System.out.println("You are at the last line");
        } else {
            System.out.println("Now, you are at the last line");
            currentLine = List.size();
        }
    }

    // -
    static public void oneLineUp() {

        if (currentLine == 1) {
            System.out.println("You do not have any line up");
        } else {
            System.out.println("Now, you are one line up");
            --currentLine;
        }

    }

    // +
    static public void oneLineDown() {
        if (currentLine == List.size()) {
            System.out.println("You do not have any line down");
        } else {
            System.out.println("Now, you are one line down");
            ++currentLine;
        }
    }

    // f
    static public void renameFile(String name) {
        String path = "src/Files/" + name + ".txt";

        File file2 = new File(path);

        if (file.renameTo(file2)) {
            file = file2;
            System.out.println("Renamed successfully ");
        } else
            System.out.println("Error");
    }

    // r
    static public void readAndPasteFile(String name) throws IOException {

        String path3 = "src/Files/" + name + ".txt";

        File file3 = new File(path3);

        BufferedReader br = new BufferedReader(new FileReader(file3));
        Scanner read = new Scanner(file3);

        if (file3.exists()) {
            List.clear();

            while (read.hasNext()) {
                String line = br.readLine();

                if (line == null)
                    break;

                List.add(line);
            }

            write();
            read.close();
            br.close();
        } else
            System.out.println("The file does not exist");
    }

    // pa
    static public void printAllLines() {
        if (toggle == 0)
            List.showList();
        else if (toggle == 1)
            List.printWithIndex();

    }

}