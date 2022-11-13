import sys

input = sys.stdin.readline
N, S = map(int, input().split())
cows = [int(input()) for _ in range(N)]
cows.sort()

left, right = 0, N - 1
answer = 0
for left in range(N - 1):
    for right in range(N - 1, left, -1):
        sum_of_weight = cows[left] + cows[right]
        if sum_of_weight <= S:
            answer += right - left
            break

print(answer)
