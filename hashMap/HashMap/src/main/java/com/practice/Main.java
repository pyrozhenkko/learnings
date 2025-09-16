package com.practice;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Map<String, Integer> clientIds = new HashMap<>();
        clientIds.put("Artem", 1);
        clientIds.put("Bob", 2);
        clientIds.put("David", 3);
        clientIds.put("Emma", 4);
        clientIds.put("John", 5);
        System.out.println(clientIds.getClass());

        Integer id1 = clientIds.get("Artem");
        System.out.println(id1);

        System.out.println(clientIds.containsKey("Bob"));
        System.out.println(clientIds.containsValue(1));

        System.out.println(clientIds);
        clientIds.replace("Artem", 2);// лище існуючий
        System.out.println(clientIds);

        clientIds.putIfAbsent("Bob", 3);// якщо не існує
        clientIds.remove("Bob");
        clientIds.remove("Bob", 1);
        System.out.println(clientIds);
    }
}