import sys


def is_valid(next_x, next_y):
    return 0 <= next_x < N and 0 <= next_y < M and not check[next_x][next_y]


def DFS(x, y, sum):
    if y == M:
        x, y = x + 1, 0
    if x == N:
        global answer
        answer = max(answer, sum)
        return

    if not check[x][y]:
        for i in range(-1, 3):
            next_x1, next_x2 = x + dx[i], x + dx[i + 1]
            next_y1, next_y2 = y + dy[i], y + dy[i + 1]
            if is_valid(next_x1, next_y1) and is_valid(next_x2, next_y2):
                check[x][y] = check[next_x1][next_y1] = check[next_x2][next_y2] = True
                DFS(x, y + 1, sum + 2 * board[x][y] + board[next_x1][next_y1] + board[next_x2][next_y2])
                check[x][y] = check[next_x1][next_y1] = check[next_x2][next_y2] = False
    DFS(x, y + 1, sum)


input = sys.stdin.readline
N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
check = [[False for _ in range(M)] for _ in range(N)]
dx, dy = [-1, 0, 1, 0], [0, 1, 0, -1]

answer = 0
DFS(0, 0, 0)
print(answer)
