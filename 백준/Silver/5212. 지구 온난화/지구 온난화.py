import copy
import sys


def is_valid(next_x, next_y):
    if 0 <= next_x < R and 0 <= next_y < C and board[next_x][next_y] == 'X':
        return 0
    return 1


def is_submerge(x, y):
    cnt = 0
    for i in range(4):
        cnt += is_valid(x + dx[i], y + dy[i])
    return cnt >= 3

input = sys.stdin.readline
R, C = map(int, input().split())
board = [list(input()) for _ in range(R)]
new_board = copy.deepcopy(board)
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for i in range(R):
    for j in range(C):
        if board[i][j] == 'X' and is_submerge(i, j):
            new_board[i][j] = '.'

start_x, end_x = 0, R - 1
start_y, end_y = C - 1, 0
for i in range(R):
    if 'X' in new_board[i]:
        start_x = i
        break

for i in range(R - 1, -1, -1):
    if 'X' in new_board[i]:
        end_x = i
        break

for i in range(start_x, end_x + 1):
    y_indexes = [j for j, value in enumerate(new_board[i]) if value == 'X']
    if y_indexes:
        start_y = min(start_y, y_indexes[0])
        end_y = max(end_y, y_indexes[-1])

for i in range(start_x, end_x + 1):
    for j in range(start_y, end_y + 1):
        print(new_board[i][j], end='')
    print()