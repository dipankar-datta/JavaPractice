package com.example.challenges;

import java.util.*;

public class MininumSubstring {

    public static void main(String[] args) {
        //System.out.println(getMinimumSubstring("152354512345"));
        System.out.println(getMinimumSubstring("dabbcabcd"));
    }

    static String getMinimumSubstring(String input) {

        List<Character> charInput = new ArrayList<>();
        Set<Character> uniqueChars = new HashSet();
        for (char ch : input.toCharArray()) {
            charInput.add(ch);
            uniqueChars.add(ch);
        }

        int initialMatchSize = uniqueChars.size();

        while (initialMatchSize <= input.length()) {

            int iterationCount = input.length() - initialMatchSize + 1;

            for (int i = 0; i < iterationCount; i++) {

                List<Character> currentData = charInput.subList(i, (i + initialMatchSize));

                boolean validFlag = true;

                for (Character ch : uniqueChars) {
                    if (!currentData.contains(ch)) {
                        validFlag = false;
                        break;
                    }
                }

                if (validFlag) {
                    StringBuilder sb = new StringBuilder();
                    currentData.forEach(sb::append);
                    return sb.toString();
                }
            }

            initialMatchSize++;
        }

        return null;
    }
}
