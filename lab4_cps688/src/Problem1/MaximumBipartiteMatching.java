/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem1;

/**
 *
 * author s364pate
 */
import java.util.*;
import java.io.*;

public class MaximumBipartiteMatching {

    // Implementation of the Ford-Fulkerson algorithm
    public static int fordFulkerson(int[][] graph, int source, int sink) {
        // Initialize the residual graph to be the same as the original graph
        int[][] residualGraph = graph;
        // Initialize the maximum flow to 0, and create an array to keep track of parents in the augmenting paths
        int maxFlow = 0;
        int[] parent = new int[graph.length];

        // Start the Ford-Fulkerson algorithm loop
        while (bfs(residualGraph, source, sink, parent)) {
            // Find the minimum residual capacity of the augmenting path using parent[] array
            int pathFlow = Integer.MAX_VALUE;
            for (int to = sink; to != source; to = parent[to]) {
                int from = parent[to];
                pathFlow = Math.min(pathFlow, residualGraph[from][to]);
            }

            // Update the residual capacities of the edges and reverse edges in the augmenting path
            for (int to = sink; to != source; to = parent[to]) {
                int from = parent[to];
                residualGraph[from][to] -= pathFlow;
                residualGraph[to][from] += pathFlow;
            }

            // Add the path flow to the maximum flow
            maxFlow += pathFlow;
        }

        // Return the maximum flow
        return maxFlow;
    }

    // Implementation of the Breadth-First Search algorithm
    private static boolean bfs(int[][] graph, int source, int sink, int[] parent) {
        // Create a boolean array to keep track of visited nodes, and a queue for BFS
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<Integer>();
        // Add the source node to the queue, mark it as visited, and set its parent to -1
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        // Start the BFS loop
        while (!queue.isEmpty()) {
            // Dequeue the next node from the queue and process its neighbors
            int from = queue.poll();
            for (int to = 0; to < graph.length; to++) {
                // If v is not already visited and there is an edge from u to v in the residual graph,
                // mark v as visited, set its parent to u, and add it to the queue
                if (visited[to] == false && graph[from][to] > 0) {
                    queue.add(to);
                    visited[to] = true;
                    parent[to] = from;
                }
            }
        }

        // Return true if the sink node was visited during BFS (i.e., there exists a path from source to sink in the residual graph)
        return (visited[sink] == true);
    }
    public static void main(String[] args) {
        int M = 6; // Applicants
        int N = 6; // Jobs

        int[][] graph = new int[M + N + 2][M + N + 2];
        int source = 0;
        int sink = M + N + 1;

        // Read the input from file and build the graph
        try {
            Scanner scan = new Scanner(new File("/home/student1/s364pate/CPS688/lab4/src/Problem1/input.txt"));
            for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= N; j++) {
                    graph[i][M + j] = scan.nextInt();
                }
            }
            // add an edge from source to all applicants
            for (int i = 1; i <= M; i++) {
                graph[source][i] = 1;
            }
            // add an edge from all jobs to sink
            for (int j = 1; j <= N; j++) {
                graph[M + j][sink] = 1;
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        int maxFlow = fordFulkerson(graph, source, sink);
        System.out.println("The maximum number of applicants matching for the jobs is: " + maxFlow);
    }
}