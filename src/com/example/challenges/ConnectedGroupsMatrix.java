package com.example.challenges;

import java.util.*;

public class ConnectedGroupsMatrix {

    private static Map<Integer, List<Integer>> links = new HashMap<>();

    public static void main(String[] args) {
//        int[][] input = {
//                {1, 1, 0},
//                {1, 1, 0},
//                {0, 0, 1}
//        };

        int[][] input = {
                {1, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 1}
        };
        System.out.println("Total connected nodes -> " + getNumberOfConnectedGroups(input));
    }

    static int getNumberOfConnectedGroups(int[][] groups) {

        int result = 0;

        for (int i = 0; i < groups.length; i++) {

            int[] connections = groups[i];

            for (int j = 0; j < connections.length; j++) {

                if (connections[j] == 1) {

                    if (!links.containsKey((i))) {
                        List<Integer> bucket = new ArrayList<>();
                        bucket.add(j);
                        links.put( (i), bucket);
                    } else {
                        if (!links.get(i).contains(j)) {
                            links.get(i).add(j);
                        }
                    }

                    if (!links.containsKey((j))) {
                        List<Integer> bucket = new ArrayList<>();
                        bucket.add(i);
                        links.put( (j), bucket);
                    } else {
                        if (!links.get(j).contains(i)) {
                            links.get(j).add(i);
                        }
                    }
                }
            }
        }

        System.out.println(links.toString());

        List<Integer> visitedNode = new ArrayList<>(links.size());

        for (Map.Entry<Integer, List<Integer>> node : links.entrySet()) {

            List<Integer> connectedNodes = new ArrayList<>();

            BFSUtil(node.getKey(), visitedNode, connectedNodes);

            if (!connectedNodes.isEmpty()) {
                System.out.println(connectedNodes.toString());
                result++;
            }
        }


        return result;
    }


    static void BFSUtil(int node, List<Integer> visited, List<Integer> connectedNodes) {
        if (!visited.contains(node)) {
            visited.add(node);
            if (!connectedNodes.contains(node)) {
                connectedNodes.add(node);
            }

            for (Integer newNode : links.get(node)) {
                if (!visited.contains(newNode)) {
                    BFSUtil(newNode, visited, connectedNodes);
                }
            }
        }

    }
}
