package com.yeprogress;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Stream.of(1,2,3,4,5,7,3,4,5,7,6)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream();

        int[] numbers = {5,5,5,4,5,4,7,4,2,3,4,2,4,5,3,2,2};
        int maxCount= 0;
        int finalValue = 0;
       for (int i = 0; i < numbers.length; i++) {
           int count = 0;
           for (int j = 0; j < numbers.length; j++) {
               if (numbers[i] == numbers[j]) {
                   count++;
               }
           }
           if (count >= maxCount) {
               maxCount = count;
               finalValue = numbers[i];
           }
       }
       System.out.println(finalValue);

    }
}