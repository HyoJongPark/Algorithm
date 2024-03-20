import java.util.HashMap;

class Solution {

    private static final int NULL_PRODUCT = 123;

    public int solution(String[] want, int[] number, String[] discount) {
        HashMap<String, Integer> product = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            product.put(want[i], number[i]);
        }

        return findSubscriptionDate(product, discount);
    }

    //투 포인터
    private int findSubscriptionDate(HashMap<String, Integer> product, String[] discount) {
        int count = initCount(product, discount), result = count == product.size() ? 1 : 0;

        for (int i = 1; i < discount.length - 9; i++) {
            //첫 번째 과일 하나 증가
            String productName = discount[i - 1];
            Integer productCount = product.getOrDefault(productName, NULL_PRODUCT);

            if (productCount != NULL_PRODUCT) {
                if (productCount == 0) {
                    count--;
                }
                product.put(productName, productCount + 1);

            }

            //두 번째 과일 하나 감소
            productName = discount[i + 9];
            productCount = product.getOrDefault(productName, NULL_PRODUCT);

            if (productCount != NULL_PRODUCT) {
                if (productCount - 1 == 0) {
                    count++;
                }
                product.put(productName, productCount - 1);
            }

            if (count == product.size()) result++;
        }

        return result;
    }

    private int initCount(HashMap<String, Integer> product, String[] discount) {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            String productName = discount[i];
            Integer productCount = product.getOrDefault(productName, NULL_PRODUCT);

            if (productCount != NULL_PRODUCT) {
                if (productCount - 1 == 0) {
                    count++;
                }
                product.put(productName, productCount - 1);
            }
        }
        return count;
    }
}
