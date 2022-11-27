import sys

input = sys.stdin.readline
N, K = map(int, input().split())
level = [int(input()) for _ in range(N)]
level.sort()

left, right = level[0], level[-1] + K
while left <= right:
    cnt = 0
    mid = (left + right) // 2
    for l in level:
        if l < mid:
            cnt += mid - l
        else:
            break

    if cnt <= K:
        left = mid + 1
    else:
        right = mid - 1
print(right)