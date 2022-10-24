import sys

input = sys.stdin.readline
N, M = map(int, input().split())
immigration = [int(input()) for _ in range(N)]

left, right = min(immigration), max(immigration) * M
answer = sys.maxsize
while left <= right:
    current_cnt = 0
    mid = (left + right) // 2
    for time in immigration:
        current_cnt += mid // time

    if current_cnt < M:
        left = mid + 1
    else:
        right = mid - 1
        answer = min(answer, mid)

print(answer)
