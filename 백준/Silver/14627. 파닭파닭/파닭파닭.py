import sys

input = sys.stdin.readline
S, C = map(int, input().split())
green_onions = [int(input()) for _ in range(S)]

left, right = 1, max(green_onions)
answer = 0
while left <= right:
    mid = (left + right) // 2
    cnt = 0
    for green_onion in green_onions:
        cnt += green_onion // mid

    if cnt >= C:
        left = mid + 1
        answer = mid
    else:
        right = mid - 1

print(sum(green_onions) - answer * C)