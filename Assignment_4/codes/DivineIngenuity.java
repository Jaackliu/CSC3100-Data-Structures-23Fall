package codes;
import java.util.*;

// 原神，启动！
public class DivineIngenuity {

    static class Point {
        int x, y, change;
        char dir;

        Point(int x, int y, char dir, int change) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.change = change;
        }
    }

    public static void dijkstra(Point[][] domain, Point start, int rows, int cols) {
        PriorityQueue<Point> points = new PriorityQueue<>(Comparator.comparingInt(p -> p.change));
        boolean[][] visitedPoints = new boolean[rows][cols];

        points.add(start);
        visitedPoints[start.x][start.y] = true;
        domain[start.x][start.y].change = 0;

        while (!points.isEmpty()) {
            Point currentNode = points.poll();

            int nextPos_x = currentNode.x + 1;
            int nextPos_y = currentNode.y;

            if (nextPos_x>= 0 && nextPos_x < rows && nextPos_y >= 0 && nextPos_y < cols) {
                int isAddChange = ((currentNode.dir == 'i' || currentNode.dir == 's') ? 0 : 1);
                if (currentNode.change + isAddChange < domain[nextPos_x][nextPos_y].change) {
                    domain[nextPos_x][nextPos_y].change = currentNode.change + isAddChange;
                    points.add(domain[nextPos_x][nextPos_y]);
                    visitedPoints[nextPos_x][nextPos_y] = true;
                }
            }

            nextPos_x = currentNode.x - 1;
            nextPos_y = currentNode.y;

            if (nextPos_x>= 0 && nextPos_x < rows && nextPos_y >= 0 && nextPos_y < cols) {
                int isAddChange = ((currentNode.dir == 'i' || currentNode.dir == 'w') ? 0 : 1);
                if (currentNode.change + isAddChange < domain[nextPos_x][nextPos_y].change) {
                    domain[nextPos_x][nextPos_y].change = currentNode.change + isAddChange;
                    points.add(domain[nextPos_x][nextPos_y]);
                    visitedPoints[nextPos_x][nextPos_y] = true;
                }
            }

            nextPos_x = currentNode.x;
            nextPos_y = currentNode.y + 1;

            if (nextPos_x>= 0 && nextPos_x < rows && nextPos_y >= 0 && nextPos_y < cols) {
                int isAddChange = ((currentNode.dir == 'i' || currentNode.dir == 'd') ? 0 : 1);
                if (currentNode.change + isAddChange < domain[nextPos_x][nextPos_y].change) {
                    domain[nextPos_x][nextPos_y].change = currentNode.change + isAddChange;
                    points.add(domain[nextPos_x][nextPos_y]);
                    visitedPoints[nextPos_x][nextPos_y] = true;
                }
            }

            nextPos_x = currentNode.x;
            nextPos_y = currentNode.y - 1;

            if (nextPos_x>= 0 && nextPos_x < rows && nextPos_y >= 0 && nextPos_y < cols) {
                int isAddChange = ((currentNode.dir == 'i' || currentNode.dir == 'a') ? 0 : 1);
                if (currentNode.change + isAddChange < domain[nextPos_x][nextPos_y].change) {
                    domain[nextPos_x][nextPos_y].change = currentNode.change + isAddChange;
                    points.add(domain[nextPos_x][nextPos_y]);
                    visitedPoints[nextPos_x][nextPos_y] = true;
                }
            }
        }

    }

    public static void main(String[] args) {
        Point start = null, end = null;
        Point[][] domain;

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        domain = new Point[m][n];

        for (int i = 0; i < m; i++) {
            String rowInput = sc.next();

            for (int j = 0; j < n; j++) {
                char direction = rowInput.charAt(j);
                domain[i][j] = new Point(i, j, direction, Integer.MAX_VALUE);

                if (direction == 'i') {
                    start = domain[i][j];
                } else if (direction == 'j') {
                    end = domain[i][j];
                }
            }
        }

        dijkstra(domain, start, m, n);
        System.out.println(domain[end.x][end.y].change);
    }

}
