package chatGPT;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PrimMST {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/stutipatel/Desktop/backtrack cps688_lab2/chatGPT/sample.txt"));

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int e = scanner.nextInt();

            // Create adjacency list and edge list representations of the graph
            List<List<Edge>> adj = new ArrayList<>(n);
            List<Edge> edges = new ArrayList<>(e);

            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < e; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                edges.add(new Edge(u, v, w));
                adj.get(u).add(new Edge(u, v, w));
                adj.get(v).add(new Edge(v, u, w));
            }

            // Use Prim's algorithm to find the minimum spanning tree
            boolean[] visited = new boolean[n];
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e1 -> e1.weight));
            pq.offer(new Edge(-1, 0, 0)); // Start with vertex 0
            List<Edge> mst = new ArrayList<>();

            while (!pq.isEmpty()) {
                Edge e1 = pq.poll();

                if (visited[e1.v]) {
                    continue;
                }

                visited[e1.v] = true;

                if (e1.u != -1) {
                    mst.add(e1);
                }

                for (Edge e2 : adj.get(e1.v)) {
                    if (!visited[e2.v]) {
                        pq.offer(e2);
                    }
                }
            }

            // Output the minimum spanning tree
            int mstWeight = 0;
            System.out.println("MST:");
            for (Edge e1 : mst) {
                System.out.println("Edge " + e1.u + "-" + e1.v + " has a weight of " + e1.weight);
                mstWeight += e1.weight;
            }
            System.out.println("MST weight = " + mstWeight);
            System.out.println();
        }
    }

    static class Edge {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
}
