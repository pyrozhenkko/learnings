package com.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class MinStack {
    private final List<Integer> localStorage;
    private final List<Integer> minValues;

    public MinStack() {
        localStorage = new ArrayList<>();
        minValues = new ArrayList<>();
    }

    public void push(int val) {
        localStorage.add(val);
        if (minValues.isEmpty() || val < minValues.get(minValues.size() - 1)) {
            minValues.add(val);
        }
    }


    public void pop(){
        var top = localStorage.get(localStorage.size() - 1);
        localStorage.remove(localStorage.size() - 1);
        if(minValues.get(minValues.size() - 1).equals(top)){
            minValues.remove(minValues.size() - 1);
        }
    }

    public int top() {
        return localStorage.get(localStorage.size()-1);
    }

    public int min() {
        return minValues.get(minValues.size()-1);
    }

}
