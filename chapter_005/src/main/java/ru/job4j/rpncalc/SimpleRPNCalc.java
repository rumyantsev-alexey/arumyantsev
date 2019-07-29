package ru.job4j.rpncalc;

public class SimpleRPNCalc extends RPNCalc {

    /**
     * Простейший калькулятор типа RPN
     */
    public SimpleRPNCalc() {
        super.getOperations().put("+", (x) -> x.push(x.pop() + x.pop()));
        super.getOperations().put("-", (x) -> { var op2 = x.pop(); x.push(x.pop() - op2); });
        super.getOperations().put("*", (x) -> x.push(x.pop() * x.pop()));
        super.getOperations().put("/", (x) -> { var op2 = x.pop(); x.push(x.pop() / op2); });
        super.getOperations().put("clr", MyStack::clearStack);
    }

}
