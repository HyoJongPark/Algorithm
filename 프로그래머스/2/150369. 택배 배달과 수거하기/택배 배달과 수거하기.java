class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int pickupCount = 0, deliveryCount = 0;
        long distance = 0;

        for (int homeNumber = n - 1; homeNumber >= 0; homeNumber--) {
            deliveryCount += deliveries[homeNumber];
            pickupCount += pickups[homeNumber];

            //챙기거나 수거할 물건이 있는지
            while (hasBox(deliveryCount, pickupCount)) {
                deliveryCount -= cap;
                pickupCount -= cap;
                distance += (homeNumber + 1) * 2;
            }
        }
        return distance;
    }

    private boolean hasBox(int deliveryCount, int pickupCount) {
        return deliveryCount > 0 || pickupCount > 0;
    }
}
