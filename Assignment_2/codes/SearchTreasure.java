package codes;
import java.util.Scanner;
import java.util.Stack;


public class SearchTreasure {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int Widths = sc.nextInt();
            sc.nextLine();

            int[] Depths = new int[Widths];
            for (int i = 0; i < Widths; i++) {
                Depths[i] = sc.nextInt();
            }
            sc.nextLine();

            long areaTreasure = TrenchSearch(Widths, Depths);
            System.out.println(areaTreasure);
        }
        sc.close();
    }

    public static long TrenchSearch(int Widths, int[] Depths) {

        long areaTreasure = 0;
        int LeftBoundary;
        Stack<Integer> depthStack = new Stack<Integer>();

        for (int curColumn = 0; curColumn < Widths; curColumn++) {

            while(! depthStack.isEmpty() && Depths[depthStack.peek()]>Depths[curColumn]) {
                int curDepth = Depths[depthStack.pop()];

                if (depthStack.isEmpty()) {
                    LeftBoundary = 0;
                } else {
                    LeftBoundary = depthStack.peek() + 1;
                }
                areaTreasure = Math.max(areaTreasure, (long)(curColumn - LeftBoundary) * curDepth);
            }

            depthStack.push(curColumn);
        }

        while(! depthStack.isEmpty()) {
            int curDepth = Depths[depthStack.pop()];

            if (depthStack.isEmpty()) {
                LeftBoundary = 0;
            } else {
                LeftBoundary = depthStack.peek() + 1;
            }
            areaTreasure = Math.max(areaTreasure,(long)(Widths - LeftBoundary) * curDepth);
        }

        return areaTreasure;
        }
}
