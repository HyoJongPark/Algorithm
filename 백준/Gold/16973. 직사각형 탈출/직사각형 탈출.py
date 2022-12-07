import sys


def square_not_contain_walls(x, y):
    for wall_x, wall_y in walls:
        if x <= wall_x < x + H and y <= wall_y < y + W:
            return False
    return True


def is_valid(next_x, next_y):
    return 0 <= next_x < (N - H + 1) and 0 <= next_y < (M - W + 1)


def BFS(start_x, start_y):
    queue = [(0, (start_x, start_y))]
    check[start_x][start_y] = True
    while queue:
        distance, square = queue.pop(0)
        for i in range(4):
            next_x = square[0] + dx[i]
            next_y = square[1] + dy[i]
            if next_x == end_x - 1 and next_y == end_y - 1:
                return distance + 1

            if is_valid(next_x, next_y) and square_not_contain_walls(next_x, next_y) and not check[next_x][next_y]:
                check[next_x][next_y] = True
                queue.append((distance + 1, (next_x, next_y)))
    return -1


input = sys.stdin.readline
N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
check = [[False] * M for _ in range(N)]
H, W, start_x, start_y, end_x, end_y = map(int, input().split())

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
walls = []
for i in range(N):
    for j in range(M):
        if board[i][j] == 1:
            walls.append((i, j))
print(BFS(start_x - 1, start_y - 1))
