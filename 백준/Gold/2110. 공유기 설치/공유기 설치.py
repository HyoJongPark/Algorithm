import sys

input = sys.stdin.readline
N, C = map(int, input().split())
position = [int(input()) for _ in range(N)]
position.sort()

left, right = 1, position[-1] - position[0]
answer = sys.maxsize
while left <= right:
    cnt, current = 1, position[0]
    mid = (left + right) // 2
    for i in range(1, N):
        if position[i] - current >= mid:
            current = position[i]
            cnt += 1

    if cnt >= C:
        answer = mid
        left = mid + 1
    else:
        right = mid - 1

print(answer)