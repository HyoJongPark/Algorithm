import copy
import sys


def move_cloud(movement):
    global board
    visited = [[False] * N for _ in range(N)]
    next_dx = movement[1] * dx[movement[0] - 1]
    next_dy = movement[1] * dy[movement[0] - 1]
    for i, position in enumerate(cloud_position):
        next_x = (position[0] + next_dx) % N
        next_y = (position[1] + next_dy) % N
        board[next_x][next_y] += 1
        cloud_position[i] = (next_x, next_y)
        visited[next_x][next_y] = True
    return visited


def bibaragi():
    new_board = copy.deepcopy(board)
    for position in cloud_position:
        cnt = 0
        for i in range(1, 8, 2):
            next_x = position[0] + dx[i]
            next_y = position[1] + dy[i]
            if 0 <= next_x < N and 0 <= next_y < N and board[next_x][next_y] > 0:
                cnt += 1
        new_board[position[0]][position[1]] += cnt
    return new_board


def find_cloud():
    global board
    new_cloud = []
    for i in range(N):
        for j in range(N):
            if not visited[i][j] and board[i][j] >= 2:
                board[i][j] -= 2
                new_cloud.append((i, j))
    return new_cloud


input = sys.stdin.readline
N, M = map(int, input().split())
board, movements = [list(map(int, input().split())) for _ in range(N)], []
dx = [0, -1, -1, -1, 0, 1, 1, 1]
dy = [-1, -1, 0, 1, 1, 1, 0, -1]
for _ in range(M):
    direction, distance = map(int, input().split())
    movements.append((direction, distance))

cloud_position = [(N - 1, 0), (N - 1, 1), (N - 2, 0), (N - 2, 1)]
while movements:
    visited = move_cloud(movements.pop(0))
    board = bibaragi()
    cloud_position = find_cloud()

answer = 0
for i in range(N):
    answer += sum(board[i])
print(answer)
