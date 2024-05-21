package codes;
import java.util.Scanner;
import java.util.TreeSet;

public class PriceSequence {

    private static Long getMinDiff(TreeSet<Long> treeSet, Long element) {
        Long high = treeSet.higher(element);
        Long low = treeSet.lower(element);

        Long highDiff;
        if (high != null) {
            highDiff = Math.abs(element - high);
        } else {
            highDiff = Long.MAX_VALUE;
        }

        Long lowDiff;
        if (low != null) {
            lowDiff = Math.abs(element - low);
        } else {
            lowDiff = Long.MAX_VALUE;
        }

        return Math.min(lowDiff, highDiff);
    }


    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int numberOfPrices = sc.nextInt();
        int numberOfOperations = sc.nextInt();

        Long minAdjacentDiff = Long.MAX_VALUE;
        Long minDiff = Long.MAX_VALUE;
        Long previousPrice = -1L;
        TreeSet<Long> prices = new TreeSet<>();

        for (int i = 0; i < numberOfPrices; i++) {
            Long currentPrice = sc.nextLong();

            if (previousPrice != -1L) {
                minAdjacentDiff = Math.min(minAdjacentDiff, Math.abs(currentPrice - previousPrice));
            }
            previousPrice = currentPrice;

            if (prices.contains(currentPrice)) {
                minDiff = 0L;
            } else {
                prices.add(currentPrice);
                minDiff = Math.min(minDiff, getMinDiff(prices, currentPrice));
            }
        }

        for (int i = 0; i < numberOfOperations; i++) {
            String operation = sc.next();

            switch (operation) {
                case "BUY":
                    Long currentPrice = sc.nextLong();
                    if (previousPrice != -1L) {
                        minAdjacentDiff = Math.min(minAdjacentDiff, Math.abs(currentPrice - previousPrice));
                    }
                    previousPrice = currentPrice;

                    if (prices.contains(currentPrice)) {
                        minDiff = 0L;
                    } else {
                        prices.add(currentPrice);
                        minDiff = Math.min(minDiff, getMinDiff(prices, currentPrice));
                    }
                    break;

                case "CLOSEST_ADJ_PRICE":
                    System.out.println(minAdjacentDiff);
                    break;

                case "CLOSEST_PRICE":
                    System.out.println(minDiff);
                    break;
            }
        }
    }
}
