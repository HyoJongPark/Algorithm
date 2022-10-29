import sys
from collections import deque


def bfs(S, X, Y):
    q = deque(virus)
    count = 0
    while q and count != S:
        for _ in range(len(q)):
            virus_number, x, y = q.popleft()
            for i in range(4):
                next_x = x + dx[i]
                next_y = y + dy[i]
                if is_valid(next_x, next_y):
                    board[next_x][next_y] = board[x][y]
                    q.append((board[next_x][next_y], next_x, next_y))
        count += 1
    return board[X - 1][Y - 1]


def is_valid(next_x, next_y):
    return 0 <= next_x < N and 0 <= next_y < N and board[next_x][next_y] == 0


input = sys.stdin.readline
board, virus = [], []
dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
N, K = map(int, input().split())
for i in range(N):
    board.append(list(map(int, input().split())))
    for j in range(N):
        if board[i][j] != 0:
            virus.append((board[i][j], i, j))
S, X, Y = map(int, input().split())

virus.sort()
print(bfs(S, X, Y))
