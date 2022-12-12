import sys
from collections import deque

input = sys.stdin.readline
N, M = map(int, input().split())
board = [list(map(int, input().strip())) for _ in range(N)]
check = [[False] * M for _ in range(N)]
dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]


def bfs(x, y):
    q = deque()
    q.append((x, y))
    check[x][y] += 1
    while q:
        x, y = q.popleft()
        for i in range(4):
            next_x = x + dx[i]
            next_y = y + dy[i]
            if is_valid(next_x, next_y) and board[next_x][next_y] != 1:
                q.append((next_x, next_y))
                check[next_x][next_y] = check[x][y] + 1
                if board[next_x][next_y] in (3, 4, 5):
                    print('TAK')
                    print(check[x][y])
                    sys.exit(0)
    print('NIE')


def is_valid(next_x, next_y):
    return 0 <= next_x < N and 0 <= next_y < M and not check[next_x][next_y]


for i in range(N):
    for j in range(M):
        if board[i][j] == 2:
            bfs(i, j)
