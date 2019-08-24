package com.example.challenges;

import java.util.Arrays;

public class SeparateZeroesAndOnes {

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 1, 0, 0 ,1, 1, 1};

        int zeroIndex = 0;
        int oneIndex = arr.length - 1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[zeroIndex] == 1) {
                int temp = arr[zeroIndex];
                arr[zeroIndex] = arr[oneIndex];
                arr[oneIndex] = temp;
                oneIndex--;
            } else {
                zeroIndex++;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
