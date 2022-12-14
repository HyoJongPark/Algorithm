import sys
sys.setrecursionlimit(3000000)


def is_valid(next_x, next_y):
    return 0 <= next_x < N and 0 <= next_y < M


def dfs(x, y):
    if x == N - 1:
        print("YES")
        sys.exit(0)
    for dx, dy in d:
        next_x, next_y = x + dx, y + dy
        if is_valid(next_x, next_y) and not check[next_x][next_y] and board[next_x][next_y] == 0:
            check[next_x][next_y] = True
            dfs(next_x, next_y)


N, M = map(int, input().split())
board = [list(map(int, list(input()))) for _ in range(N)]
check = [[False] * M for _ in range(N)]
d = [(-1, 0), (1, 0), (0, -1), (0, 1)]
for i in range(M):
    if board[0][i] == 0:
        check[0][i] = True
        dfs(0, i)
print("NO")
