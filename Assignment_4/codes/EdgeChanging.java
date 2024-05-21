package codes;
import java.util.*;

public class EdgeChanging {
    Map<Integer, List<Integer>> graph;

    public EdgeChanging() {
        graph = new HashMap<>();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EdgeChanging graph = new EdgeChanging();

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int s = scanner.nextInt();

        constructGraph(graph, m, scanner);
        edgeChanging(graph, n, k);
        bfsResult(graph, s);
    }

    static void bfsResult(EdgeChanging graph, int startNode) {
        graph.bfs(startNode).forEach(node -> System.out.print(node + " "));
    }

    List<Integer> bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        queue.add(start);
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            if (visited.add(currentNode)) {
                result.add(currentNode);
                queue.addAll(graph.get(currentNode));
            }
        }
        return result;
    }

    static void constructGraph(EdgeChanging graph, int edgeCount, Scanner scanner) {
        for (int i = 0; i < edgeCount; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            graph.addEdge(start, end);
        }
    }

    void addNode(int node) {
        graph.putIfAbsent(node, new ArrayList<>());
    }

    void addEdge(int start, int end) {
        addNode(start);
        addNode(end);
        graph.get(start).add(end);
        graph.get(end).add(start);
    }

    static void edgeChanging(EdgeChanging graph, int n, int k) {
        for (int node = 1; node <= n; node++) {
            List<Integer> neighbors = graph.graph.get(node);
            if (neighbors != null) {
                addNeighborEdge(graph, neighbors, k);
            }
        }
        graph.graph.values().forEach(Collections::sort);
    }

    static void addNeighborEdge(EdgeChanging graph, List<Integer> neighbors, int k) {
        for (int mainNeighbor : neighbors) {
            for (int subNeighbor : neighbors) {
                if (mainNeighbor != subNeighbor && (mainNeighbor == subNeighbor * k || subNeighbor == mainNeighbor * k)) {
                    graph.addEdge(mainNeighbor, subNeighbor);
                }
            }
        }
    }

}
