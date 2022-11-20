import sys

input = sys.stdin.readline
N = int(input())

dp = [1] * (N + 1)
dp[0] = 0
for current_num in range(1, N + 1):
    j, cnt = 1, 4
    while (j ** 2) <= current_num:
        cnt = min(cnt, dp[current_num - (j ** 2)])
        j += 1
    dp[current_num] += cnt
print(dp[N])