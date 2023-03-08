import java.util.HashMap;
import java.util.Map;

class Solution {

    static Map<String, SellerInfo> tree = new HashMap<>();
    static int[] result;
    private static final int PRICE = 100;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for (int i = 0; i < enroll.length; i++) {
            tree.put(enroll[i], new SellerInfo(referral[i], i));
        }

        result = new int[enroll.length];
        for (int i = 0; i < seller.length; i++) {
            SellerInfo sellerInfo = tree.get(seller[i]);
            DFS(sellerInfo, amount[i] * PRICE);
        }

        return result;
    }

    private void DFS(SellerInfo sellerInfo, int price) {
        int nextPrice = price / 10;
        result[sellerInfo.index] += price - nextPrice;

        if (!sellerInfo.recommendName.equals("-") && nextPrice != 0) {
            DFS(tree.get(sellerInfo.recommendName), nextPrice);
        }
    }

    static class SellerInfo {
        String recommendName;
        int index;

        public SellerInfo(String name, int childIndex) {
            this.recommendName = name;
            this.index = childIndex;
        }
    }
}