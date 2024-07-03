import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N;
    static double M;
    static int[] dp;
    static Candy[] candy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        while ((N = Integer.parseInt(st.nextToken())) != 0 && (M = Double.parseDouble(st.nextToken())) != 0) {
            int money = (int) (M * 100 + 0.5);

            candy = new Candy[N];
            dp = new int[money + 1];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                candy[i] = new Candy(Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()));
            }

            for (int i = 0; i < N; i++) {
                for (int j = candy[i].price; j <= money; j++) {
                    dp[j] = Math.max(dp[j], dp[j - candy[i].price] + candy[i].calorie);
                }
            }

            sb.append(dp[money]).append("\n");
            st = new StringTokenizer(br.readLine());
        }
        System.out.println(sb);
    }

    private static class Candy {

        int calorie, price;

        public Candy(int calorie, double price) {
            this.calorie = calorie;
            this.price = (int) (price * 100 + 0.5);
        }
    }
}
