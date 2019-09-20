package ru.job4j.menu;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс описывает работу с меню
 */
public class DoMenu implements iDoMenu {
    private final Menu menu;
    private final Map<String, Node> choose = new HashMap<>();
    private final InputStream in;
    private final PrintStream out;

    /**
     * Коструктор. На вход подается готовое меню.
     * @param menu меню
     * @param in входной поток
     * @param out выходной поток
     */
    public DoMenu(final Menu menu, final InputStream in, final PrintStream out) {
        this.menu = menu;
        this.in = in;
        this.out = out;
        List<Node> m = menu.getAllMenu();
        m.forEach((x) -> choose.put(menu.getNumberMenuPoint(x), x));
    }

    /**
     * Метод реализует функционал меню
     * @return либо пункт меню либо null если был выход из меню
     */
    @Override
    public Node runMenu() {
        Node result = null;
        String input;
        System.setOut(out);
        Scanner scn = new Scanner(in);
        boolean exit = false;
        while (!exit) {
            System.out.printf("Sample Menu:%n");
            System.out.printf(this.menu.toString());
            System.out.printf("0. Exit%n");
            System.out.printf("Your choose:");
            input = scn.nextLine();
            if (input.matches("^[0]\\.$")) {
                exit = true;
            } else if (input.matches("^([1-9]\\.)*$")) {
                result = choose.get(input);
                if (result != null) {
                    exit = true;
                } else {
                    System.out.printf("%nIncorrect menu point ((%n%n");
                }
            } else {
                System.out.printf("%nIncorrect enter ((%nChoose menu point in format X.X.X.%n or 0. for exit%n%n");
            }
        }
        System.setOut(System.out);
        return result;
    }

    public static void main(String[] args) {
        Menu test = new Menu();
        Node f1 = new Node("Fist11");
        f1.addNodes(List.of(new Node("First21"),
                            new Node("First22"),
                            new Node("First23")));
        Node f2 = new Node("Second12");
        Node ss = new Node("Second23");
        ss.addNodes(List.of(new Node("Second31"),
                new Node("Second32")));
        f2.addNodes(List.of(new Node("Second21"),
                            new Node("Second22"),
                            ss,
                            new Node("Second24")));
        Node f3 = new Node("Third13");
        f3.addNodes(List.of(new Node("Third21"),
                            new Node("Third22")));
        test.createMenu(List.of(f1, f2, f3));
        DoMenu run = new DoMenu(test, System.in, System.out);
        Node result = run.runMenu();
        if (result != null) {
            result.doOperation();
        }
    }
}
