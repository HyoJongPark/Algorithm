import sys

input = sys.stdin.readline
N, M = map(int, input().split())
area = [list(map(int, input().split())) for _ in range(N)]
dp = [[0] * (M + 1) for _ in range(N + 1)]

for i in range(1, N + 1):
    for j in range(1, M + 1):
        dp[i][j] = area[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1]

for _ in range(int(input())):
    x, y, i, j = map(int, input().split())
    print(dp[i][j] - dp[x - 1][j] - dp[i][y - 1] + dp[x - 1][y - 1])
