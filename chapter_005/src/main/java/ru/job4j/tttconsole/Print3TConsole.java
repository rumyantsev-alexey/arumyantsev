package ru.job4j.tttconsole;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Print3TConsole implements IPrint3T {
    private InputStream input;
    private PrintStream output;

    public Print3TConsole(InputStream in, PrintStream out) {
        this.input = in;
        this.output = out;
    }

    @Override
    public void print(String text) {
        System.setOut(output);
        System.out.print(text);
        System.setOut(System.out);
    }

    @Override
    public void println(String text) {
        System.setOut(output);
        System.out.println(text);
        System.setOut(System.out);
    }

    @Override
    public void printBoard(Board board) {
        StringBuffer result = new StringBuffer();
        result.append("\t");
        IntStream.range(1, board.getSize() + 1).forEach((x) -> result.append(x).append(" "));
        result.append("%n\t");
        IntStream.range(1, board.getSize() + 1).forEach((x) -> result.append("- "));
        result.append("%n");
        for (int i = 1; i < board.getSize() + 1; i++) {
            result.append((i) + " |" + "\t");
            for (int j = 1; j < board.getSize() + 1; j++) {
                result.append(board.getPointVal(new Point(i, j)).toString() + " ");
            }
            result.append("%n");
        }
        System.setOut(output);
        System.out.printf(result.toString());
        System.setOut(System.out);
    }

    @Override
    public String inputLine(String text) {
        String result;
        Scanner scn = new Scanner(input);
        System.setOut(output);
        System.out.print(text);
        result = scn.nextLine();
        System.setOut(System.out);
        return result;
    }
}
