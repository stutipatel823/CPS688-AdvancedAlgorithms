package chatGPT;

import java.util.*;

public class GraphCycleDetector {

    public boolean hasCycle(int n, List<List<Integer>> edges) {
        // create adjacency list to represent the graph
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (List<Integer> edge : edges) {
            int u = edge.get(0), v = edge.get(1);
            adjacencyList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adjacencyList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        // set up visited set and parent map
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();

        // perform DFS on each unvisited node
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i) && dfs(i, visited, parent, adjacencyList)) {
                return true;
            }
        }

        return false;
    }

    private boolean dfs(int u, Set<Integer> visited, Map<Integer, Integer> parent,
                        Map<Integer, List<Integer>> adjacencyList) {
        visited.add(u);

        for (int v : adjacencyList.getOrDefault(u, new ArrayList<>())) {
            if (!visited.contains(v)) {
                parent.put(v, u);
                if (dfs(v, visited, parent, adjacencyList)) {
                    return true;
                }
            } else if (v != parent.get(u)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            int n = scanner.nextInt();
            int e = scanner.nextInt();
            scanner.nextLine(); // consume the rest of the line

            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < e; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                scanner.nextLine(); // consume the rest of the line
                edges.add(Arrays.asList(u, v));
            }

            GraphCycleDetector detector = new GraphCycleDetector();
            if (detector.hasCycle(n, edges)) {
                System.out.println("no");
            } else {
                System.out.println("yes");
            }
        }
    }
}
 // public boolean isCyclic(int node, int parent, boolean visited[]){
    //     visited[node] = true;
    //     for(int i : adjList[node]){
    //         if(!visited[i]){//not visited
    //             if(isCyclic(i, node, visited)){
    //                 return true;
    //             }
    //         } 
    //         else if(i != parent){//visited and not parent
    //             return true;
    //         }
    //     }
    //     return false;
    // }