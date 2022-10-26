import sys

input = sys.stdin.readline
N, M, L = map(int, input().split())
rest_area = list(map(int, input().split())) + [0, L]
rest_area.sort()

left, right = 1, L - 1
answer = 0
while left <= right:
    mid = (left + right) // 2
    count = 0
    for i in range(1, N + 2):
        distance = rest_area[i] - rest_area[i - 1]
        if distance > mid:
            count += (distance - 1) // mid

    if count > M:
        left = mid + 1
    else:
        right = mid - 1
        answer = mid

print(answer)

