package com.example.challenges;

import java.util.LinkedList;

public class PrintDigits {
    public static void main(String[] args) {
        int num = 123456;

        int temp = num;

        while (temp != 0) {
            System.out.println(temp % 10);
            temp = temp / 10;
        }
    }
}
