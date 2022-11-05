import collections
import sys


def is_valid(next_x, next_y):
    return 0 <= next_x < N and 0 <= next_y < N


def BFS():
    q = collections.deque()
    q.append((start_x, start_y, 0, HP, 0))
    check[start_x][start_y] = HP
    while q:
        x, y, distance, current_hp, current_d = q.popleft()
        for i in range(4):
            next_x = x + dx[i]
            next_y = y + dy[i]

            if is_valid(next_x, next_y):
                next_hp, next_d = current_hp, current_d
                if board[next_x][next_y] == 'E':
                    return distance + 1
                elif board[next_x][next_y] == 'U':
                    next_d = D

                if next_d == 0:
                    next_hp -= 1
                else:
                    next_d -= 1

                if next_hp == 0:
                    continue
                if check[next_x][next_y] < next_hp:
                    check[next_x][next_y] = next_hp
                    q.append((next_x, next_y, distance + 1, next_hp, next_d))

    return -1


input = sys.stdin.readline
N, HP, D = map(int, input().split())
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
board, check = [], [[0 for _ in range(N)] for _ in range(N)]
start_x, start_y = -1, -1
for x in range(N):
    board.append(list(input().strip()))
    if start_x == -1:
        for y in range(N):
            if board[x][y] == 'S':
                start_x, start_y = x, y

print(BFS())
