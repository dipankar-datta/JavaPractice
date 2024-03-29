package com.example.challenges;

import java.util.LinkedList;

public class ConnectedGraph {

    int V;
    LinkedList<Integer>[] adjListArray;

    // constructor
    ConnectedGraph(int V) {
        this.V = V;
        // define the size of array as
        // number of vertices
        adjListArray = new LinkedList[V];

        // Create a new list for each vertex
        // such that adjacent nodes can be stored

        for(int i = 0; i < V ; i++){
            adjListArray[i] = new LinkedList<Integer>();
        }
    }

    // Adds an edge to an undirected graph
    void addEdge( int src, int dest) {
        // Add an edge from src to dest.
        adjListArray[src].add(dest);

        // Since graph is undirected, add an edge from dest
        // to src also
        adjListArray[dest].add(src);
    }

    void DFSUtil(int v, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v+" ");
        // Recur for all the vertices
        // adjacent to this vertex
        for (int x : adjListArray[v]) {
            if(!visited[x]) DFSUtil(x,visited);
        }

    }
    void connectedComponents() {
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[V];
        for(int v = 0; v < V; ++v) {
            if(!visited[v]) {
                // print all reachable vertices
                // from v
                DFSUtil(v,visited);
                System.out.println();
            }
        }
    }


    // Driver program to test above
    public static void main(String[] args){
        // Create a graph given in the above diagram
        ConnectedGraph g = new ConnectedGraph(5); // 5 vertices numbered from 0 to 4

        g.addEdge(1, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        System.out.println("Following are connected components");
        g.connectedComponents();
    }
}
