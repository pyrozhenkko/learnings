package com.yeprogress;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int maxCounter = 0;
        int temp = 0;

        int[] numbers = {5,5,5,4,5,4,7,4,2,3,4,2,4,5,3,2,2};
        for(int i = 0; i < numbers.length; i++) {
            int counter = 0;

            for(int j = i+1; j < numbers.length; j++) {
                if(numbers[i] == numbers[j]) {
                    counter++;
                    if(counter > maxCounter) {
                        maxCounter = counter;
                        temp = numbers[i];
                    }
                }
            }


        }
        System.out.println(temp);

    }
}
