import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M, fuel;
    static Customer[] customers;
    static boolean[] visitCustomer;
    static int[] taxiPos;
    static int[][] board;

    static final int[][] d = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];
        customers = new Customer[M];
        visitCustomer = new boolean[M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        taxiPos = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            customers[i] = new Customer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            board[customers[i].startX][customers[i].startY] = -(i + 1);
        }

        playGame();
        if (fuel < 0) {
            System.out.println(-1);
        } else {
            System.out.println(fuel);
        }
    }

    private static void playGame() {
        int count = 0;
        while (fuel > 0 && count != M) {
            //move to customer
            Customer customer = findCustomer();
            if (customer == null) {
                fuel = -1;
                break;
            }

            int distance = calculateDistance(taxiPos, new int[]{customer.startX, customer.startY});
            if (distance == -1 || (fuel = fuel - distance) <= 0) {
                fuel = -1;
                break;
            }
            //move to target city
            distance = calculateDistance(new int[]{customer.startX, customer.startY}, new int[]{customer.targetX, customer.targetY});

            if (distance == -1) {
                fuel = -1;
                break;
            }
            if ((fuel = fuel - distance) >= 0) {
                fuel += distance * 2;
            }
            taxiPos = new int[]{customer.targetX, customer.targetY};
            count++;
        }
    }

    //TODO: 시작 점에서 BFS로 변경
    private static Customer findCustomer() {
        if (board[taxiPos[0]][taxiPos[1]] < 0) {
            int idx = Math.abs(board[taxiPos[0]][taxiPos[1]]) - 1;

            if (!visitCustomer[idx]) {
                visitCustomer[idx] = true;
                return customers[idx];
            }
        }
        Queue<int[]> q = new LinkedList<>();
        boolean[][] check = new boolean[N + 1][N + 1];

        q.offer(new int[]{taxiPos[0], taxiPos[1], 0});

        int targetDistance = Integer.MAX_VALUE, targetIndex = -1;
        Customer target = null;
        while (!q.isEmpty()) {
            int[] current = q.poll();

            if (current[2] > targetDistance) break;
            for (int i = 0; i < d.length; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && !check[nextX][nextY] && board[nextX][nextY] != 1) {
                    if (board[nextX][nextY] < 0) {
                        int idx = Math.abs(board[nextX][nextY]) - 1;

                        if (visitCustomer[idx]) {
                            check[nextX][nextY] = true;
                            q.offer(new int[]{nextX, nextY, current[2] + 1});
                            continue;
                        }
                        if (targetDistance > current[2] + 1 || (targetDistance == current[2] + 1 && (target.startX > customers[idx].startX || (target.startX == customers[idx].startX && target.startY > customers[idx].startY)))) {
                            targetDistance = current[2] + 1;
                            targetIndex = idx;
                            target = customers[idx];
                        }
                    }
                    check[nextX][nextY] = true;
                    q.offer(new int[]{nextX, nextY, current[2] + 1});
                }
            }
        }
        if (targetDistance != Integer.MAX_VALUE) visitCustomer[targetIndex] = true;
        return target;
    }

    private static int calculateDistance(int[] startPos, int[] targetPos) {
        if (startPos[0] == targetPos[0] && startPos[1] == targetPos[1]) return 0;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] check = new boolean[N + 1][N + 1];

        q.offer(new int[]{startPos[0], startPos[1], 0});
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < d.length; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && !check[nextX][nextY] && board[nextX][nextY] != 1) {
                    if (nextX == targetPos[0] && nextY == targetPos[1]) return current[2] + 1;

                    check[nextX][nextY] = true;
                    q.offer(new int[]{nextX, nextY, current[2] + 1});
                }
            }
        }
        return -1;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 < nextX && nextX <= N && 0 < nextY && nextY <= N;
    }

    static class Customer {
        int startX, startY, targetX, targetY;

        public Customer(int startX, int startY, int targetX, int targetY) {
            this.startX = startX;
            this.startY = startY;
            this.targetX = targetX;
            this.targetY = targetY;
        }
    }
}
