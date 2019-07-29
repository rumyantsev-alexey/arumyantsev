package ru.job4j.rpncalc;

import java.util.Scanner;

/**
 * Класс функционированя интерфейса пользователя для калькуляторов типа RPN
 */
public class InteractCalc {

    public static void runCalc(RPNCalc calc) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Калькулятор основанный на обратной польской записи:");
        System.out.printf("\tвведите ? для списка комманд калькулятора%n\tили lsto для перевыполнения последней операции%n\tили q для выхода%n%n");
        Boolean exit = false;

        do {
            System.out.printf("\tСтек(%s)%nUP-->\t", calc.getStack().list().size());
            calc.getStack().list().forEach((x) -> System.out.printf("%s ", x));
            System.out.printf("%n%n");

            System.out.print("Ввод пользователя:");

            if (scn.hasNext("-?\\d+(?:\\.\\d+)?")) {
                Double num = scn.nextDouble();
                scn.nextLine();
                calc.getStack().push(num);
            } else if (scn.hasNext("[qQ]")) {
                scn.nextLine();
                exit = true;
            } else if (scn.hasNext("\\?")) {
                scn.nextLine();
                System.out.printf("%nДоступные команды калькулятора:%n");
                calc.getOperations().forEach((x, y) -> System.out.printf("команда:%s%n", x));
                System.out.printf("%n");
            } else {
                String line = scn.nextLine();
                if ("lsto".equals(line)) {
                    line = calc.getLastoper();
                }
                try {
                    calc.solve(line);
                } catch (IllegalOperationException e) {
                    System.out.printf("%nERROR: нет такой операции%n");
                } catch (NoArgumentException e) {
                    System.out.printf("%nERROR: нехватает аргументов для операции%n");
                }
            }
        } while (!exit);

        System.out.printf("%nКалькулятор калькулятор закончил работу !!!%n");

    }

    public static void main(String[] args) {
        RPNCalc calc = new SimpleRPNCalc();
        runCalc(calc);
    }
}
