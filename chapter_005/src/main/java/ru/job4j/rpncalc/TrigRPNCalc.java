package ru.job4j.rpncalc;

public class TrigRPNCalc extends SimpleRPNCalc {

    public TrigRPNCalc() {
        super.getOperations().put("sin", (x) -> x.push(Math.sin(x.pop())));
        super.getOperations().put("cos", (x) -> x.push(Math.cos(x.pop())));
        super.getOperations().put("tan", (x) -> x.push(Math.tan(x.pop())));
        super.getOperations().put("asin", (x) -> x.push(Math.asin(x.pop())));
        super.getOperations().put("acos", (x) -> x.push(Math.acos(x.pop())));
        super.getOperations().put("atan", (x) -> x.push(Math.atan(x.pop())));
    }
}
