package com.example.challenges;

public class HappinessThreshold {

    public static void main(String[] args) {
    int[] data = {1, 2, 3, 7};

        System.out.println(findTotalProblems(4, data));
    }

    public static int findTotalProblems(int threshold, int[] problems) {

        int totalProblems = 1;

        int highestValue = problems[problems.length - 1];

        boolean flag = false;

        for (int i = problems.length - 2; i >= 1; i--) {
            totalProblems++;
            if (highestValue - problems[i] >= threshold || highestValue - problems[ i - 1] >= threshold) {
                flag = true;
                break;
            }
        }
        return flag ? totalProblems : problems.length;
    }

}
