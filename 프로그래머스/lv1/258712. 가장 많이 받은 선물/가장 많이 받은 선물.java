import java.util.HashMap;

class Solution {
    //필요한 것

    /**
     * 1. 친구와 선물을 몇번 주고받았는지 여부
     * 2. 내가 선물을 총 몇번 받았는지 여부
     */

    public int solution(String[] friends, String[] gifts) {
        HashMap<String, Integer> characterIndex = new HashMap<>();
        int[][] giftHistory = new int[friends.length][friends.length];
        int[] giftScore = new int[friends.length];

        for (int i = 0; i < friends.length; i++) {
            characterIndex.put(friends[i], i);
        }

        for (String gift : gifts) {
            String[] senderAndReceiver = gift.split(" ");

            int senderIndex = characterIndex.get(senderAndReceiver[0]);
            int receiverIndex = characterIndex.get(senderAndReceiver[1]);

            giftScore[senderIndex]++;
            giftScore[receiverIndex]--;
            giftHistory[senderIndex][receiverIndex]++;
        }

        return findMaxGiftReceiver(friends.length, giftHistory, giftScore);
    }

    private int findMaxGiftReceiver(int numberOfFriend, int[][] giftHistory, int[] giftScore) {
        int[] receivedGift = new int[numberOfFriend];
        for (int i = 0; i < numberOfFriend; i++) {
            for (int j = i + 1; j < numberOfFriend; j++) {
                if (giftHistory[i][j] > giftHistory[j][i]) {
                    receivedGift[i]++;
                } else if (giftHistory[i][j] < giftHistory[j][i]) {
                    receivedGift[j]++;
                } else if (giftScore[i] > giftScore[j]) {
                    receivedGift[i]++;
                } else if (giftScore[j] > giftScore[i]) {
                    receivedGift[j]++;
                }
            }
        }

        int result = receivedGift[0];
        for (int i = 1; i < numberOfFriend; i++) {
            result = Math.max(result, receivedGift[i]);
        }
        return result;
    }
}
