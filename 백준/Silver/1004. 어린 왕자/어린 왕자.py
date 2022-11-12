import sys

input = sys.stdin.readline
T = int(input())
for _ in range(T):
    x1, y1, x2, y2 = map(int, input().split())
    N = int(input())

    answer = 0
    for _ in range(N):
        cx, cy, r = map(int, input().split())
        start = (((x1 - cx) ** 2) + ((y1 - cy) ** 2)) ** 0.5
        end = (((cx - x2) ** 2) + ((cy - y2) ** 2)) ** 0.5

        if start < r and end < r:
            pass
        elif start < r or end < r:
            answer += 1

    print(answer)
