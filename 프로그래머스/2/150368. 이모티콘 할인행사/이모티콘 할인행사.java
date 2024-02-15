class Solution {

    int maxSubscriber = Integer.MIN_VALUE, maxPrice = Integer.MIN_VALUE;
    int[] saleRatio = {10, 20, 30, 40}, emotions;
    int[][] users;

    public static void main(String[] args) {
        new Solution().solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000});
    }

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emotions = emoticons;
        dfs(0, new int[emoticons.length]);
        return new int[]{maxSubscriber, maxPrice};
    }

    private void dfs(int level, int[] currentSaleRatio) {
        if (level == emotions.length) {
            calculateSubscriberAndPrice(currentSaleRatio);
            return;
        }

        for (int ratio : saleRatio) {
            currentSaleRatio[level] = ratio;
            dfs(level + 1, currentSaleRatio);
        }
    }

    private void calculateSubscriberAndPrice(int[] saleRatio) {
        int numberOfSubscriber = 0, totalPrice = 0;
        for (int[] user : users) {
            int currentPrice = 0;

            for (int i = 0; i < emotions.length; i++) {
                if (user[0] <= saleRatio[i]) {
                    currentPrice += emotions[i] * (100 - saleRatio[i]) / 100;
                }
            }

            if (user[1] > currentPrice) {
                totalPrice += currentPrice;
            } else {
                numberOfSubscriber++;
            }
        }

        if (maxSubscriber < numberOfSubscriber || (maxSubscriber == numberOfSubscriber && maxPrice < totalPrice)) {
            maxPrice = totalPrice;
            maxSubscriber = numberOfSubscriber;
        }
    }
}
