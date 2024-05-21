package codes;
import java.util.Scanner;

class Node {
    int order;
    int colour;
    public Node(int order, int colour) {
        this.order = order;
        this.colour = colour;
    }
}

// Report说一下这里from和to不表示实际方向，只是Node_1, Node_2太怪了
class Edge {
    long length;
    Node fromNode;
    Node toNode;
    Edge besideEdge;

    public Edge(long length, Node fromNode, Node toNode) {
        this.length = length;
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.besideEdge = null;
    }

    public void addBesideEdge(Edge besideEdge) {
        this.besideEdge = besideEdge;
    }
}


public class BlackNodeDistance {
    static Scanner sc = new Scanner(System.in);
    static Edge[] edges;
    static Edge[] ends;
    static Node[] nodes;
    static long totalDistance = 0;
    static int totalBlackNode = 0;

    public static void main(String[] args) {
        int totalNode;

        totalNode = sc.nextInt();
        edges = new Edge[totalNode];
        nodes = new Node[totalNode];
        ends = new Edge[totalNode];
        long[] deeperBlackNode = new long[totalNode];

        for (int i = 0; i < totalNode; i++) {
            int colour = sc.nextInt();
            if (colour == 1) {
                totalBlackNode += 1;
            }
//            order = index + 1
            nodes[i] = new Node(i + 1, colour);
        }

        constructEdgeTree(totalNode, edges, ends);

        dfs(nodes[0], edges[0], deeperBlackNode);

        System.out.println(totalDistance);
    }


    //    Report 写一下这里为什么用Edge建树有优势
    private static void constructEdgeTree(int totalNode, Edge[] edges, Edge[] ends) {
        for (int p = 1; p <= totalNode - 1; p++) {
            Node fromNode = nodes[sc.nextInt() - 1];
            long length = sc.nextLong();
            Edge newEdge = new Edge(length, fromNode, nodes[p]);

            int fromNodeIndex = fromNode.order - 1;
            if (edges[fromNodeIndex] == null) {
                // If no edge exists for this index, initialize with newEdge
                edges[fromNodeIndex] = newEdge;
                ends[fromNodeIndex] = newEdge;
            } else if (edges[fromNodeIndex].fromNode == newEdge.fromNode) {
                // If an edge exists and is from the same node, update besideEdge
                ends[fromNodeIndex].addBesideEdge(newEdge);
                ends[fromNodeIndex] = newEdge;
            } else {
                // If an edge exists but from a different node
                if (ends[fromNodeIndex] != null) {
                    ends[fromNodeIndex].besideEdge = newEdge;
                } else {
                    edges[fromNodeIndex].besideEdge = newEdge;
                }
                ends[fromNodeIndex] = newEdge;
            }
        }
    }



    //    recursion看同链, next看分支
    private static long dfs(Node root, Edge currentEdge, long[] blackNodeCounts) {

        long deeperBlackCount = root.colour;


        while (currentEdge != null) {
            Node deeperNode = currentEdge.toNode;
            int deeperIndex = deeperNode.order - 1;
            deeperBlackCount += dfs(deeperNode, edges[deeperIndex], blackNodeCounts);

            totalDistance += (totalBlackNode - blackNodeCounts[deeperIndex]) * blackNodeCounts[deeperIndex] * currentEdge.length;

            currentEdge = currentEdge.besideEdge;
        }

        int rootIndex = root.order - 1;
        blackNodeCounts[rootIndex] = deeperBlackCount;
        return deeperBlackCount;
    }
}
