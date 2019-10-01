package ru.job4j.storage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class ImportUser {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        UserStorage us = context.getBean("userS", UserStorage.class);
        Scanner scn = new Scanner(System.in);
        Boolean exit = false;
        while (!exit) {
            System.out.print("Please enter user name or 0 for exit:");
            String name = scn.nextLine();
            if (!name.equals("0")) {
                us.add(new User(name));
            } else {
                exit = true;
            }
        }
        System.out.printf("%nUsers list:%n");
        us.getAll().forEach(System.out::println);
    }
}
