import sys

input = sys.stdin.readline
N = int(input()) % 1500000
dp = [0] * 1500001
dp[1], dp[2] = 1, 1

for i in range(3, N + 1):
    dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000
print(dp[N])