import sys

input = sys.stdin.readline
N, S = map(int, input().split())
cows = [int(input()) for _ in range(N)]
cows.sort()

left, right = 0, N - 1
answer = 0
while left < right:
    sum_of_weight = cows[left] + cows[right]
    if sum_of_weight <= S:
        answer += right - left
        left += 1
    else:
        right -= 1
print(answer)
