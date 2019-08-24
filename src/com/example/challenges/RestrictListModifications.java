package com.example.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestrictListModifications {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> unmodifiableList = Collections.unmodifiableList(list);

        try {
            unmodifiableList.add(4);
            System.out.println("Successfully added in List");
        }catch (Exception ex) {
            System.out.println("Exception while adding in list");;
        }

    }
}
