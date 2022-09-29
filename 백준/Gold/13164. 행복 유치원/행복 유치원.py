import sys


input = lambda: sys.stdin.readline().rstrip()

N, K = map(int, input().split())
heights = list(map(int, input().split()))

dp = []
for i in range(N-1):
    dp.append(heights[i + 1] - heights[i])
dp.sort()

print(sum(dp[:N - K]))
