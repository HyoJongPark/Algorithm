import sys

input = sys.stdin.readline
N, M = map(int, input().split())
balloons = list(map(int, input().split()))

left, right, answer = 1, max(balloons) * M, 0
while left <= right:
    mid = (left + right) // 2
    cnt = sum(map(lambda balloon: mid // balloon, balloons))

    if cnt >= M:
        right = mid - 1
        answer = mid
    else:
        left = mid + 1

print(answer)