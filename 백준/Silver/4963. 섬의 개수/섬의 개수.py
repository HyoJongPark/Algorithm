import sys
from collections import deque


def BFS(x, y):
    q = deque()
    q.append((x, y))
    check[x][y] = True
    while q:
        x, y = q.popleft()

        for i in range(8):
            next_x = x + dx[i]
            next_y = y + dy[i]
            if is_valid(next_x, next_y):
                q.append((next_x, next_y))
                check[next_x][next_y] = True

    return 1


def is_valid(next_x, next_y):
    return 0 <= next_x < h and 0 <= next_y < w and not check[next_x][next_y] and board[next_x][next_y] == 1


input = sys.stdin.readline
dx = [-1, -1, -1, 0, 1, 1, 1, 0]
dy = [-1, 0, 1, 1, -1, 0, 1, -1]
w, h = map(int, input().split())
while w != 0 and h != 0:
    board = [list(map(int, input().split())) for _ in range(h)]
    check = [[False for _ in range(w)] for _ in range(h)]
    count = 0
    for x in range(h):
        for y in range(w):
            if not check[x][y] and board[x][y] != 0:
                count += BFS(x, y)

    print(count)
    w, h = map(int, input().split())