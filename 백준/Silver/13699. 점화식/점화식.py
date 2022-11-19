import sys

input = sys.stdin.readline
N = int(input())

dp = [1] * (N + 1)
for i in range(1, N + 1):
    tmp = 0
    for j in range(i):
        tmp += dp[j] * dp[i - j - 1]
    dp[i] = tmp
print(dp[N])
