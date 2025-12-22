package com.practice;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int result = calculate("12+323+323*43+3+2*8");
        System.out.println(result);

    }
    public static int calculate(String expression) {
        int result = 0;
        int currentBlockValue = 1;
        int currentValue = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                currentValue = currentValue * 10 + (c - '0');
            }

            if (!Character.isDigit(c) || i == expression.length() - 1) {
                if (c == '*') {
                    currentBlockValue *= currentValue;
                    currentValue = 0;
                } else if (c == '+') {
                    currentBlockValue *= currentValue;
                    result += currentBlockValue;
                    currentBlockValue = 1;
                    currentValue = 0;
                } else if (i == expression.length() - 1) {
                    currentBlockValue *= currentValue;
                    result += currentBlockValue;
                }
            }
        }
        return result;
    }
}