package com.example.challenges;

import java.util.ArrayList;
import java.util.List;

public class PrintArmstrongNumbers {

    public static void main(String[] args) {

        List<Integer> result = getArmstrongNumbers(7);
        System.out.println(result.toString());

    }

    public static List<Integer> getArmstrongNumbers(int maxLimit) {

        if (maxLimit > 6) {
            throw new RuntimeException("Max 6 is allowed");
        }

        List<Integer> result = new ArrayList<>();
        int currentNumber = 0;
        while(result.size() < maxLimit) {
            System.out.println("Checking -> " + currentNumber);
            int temp = 0;
            int currentNumberCopy = currentNumber;

            while (currentNumberCopy != 0) {
                temp += Math.pow((currentNumberCopy % 10), 3);
                currentNumberCopy /= 10;
            }

            if (currentNumber == temp) {
                result.add(currentNumber);
            }
            currentNumber++;
        }
        return result;
    }
}
