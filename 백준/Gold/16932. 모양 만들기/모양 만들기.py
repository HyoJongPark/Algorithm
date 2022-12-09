import sys


def is_valid(next_x, next_y):
    return 0 <= next_x < N and 0 <= next_y < M


def bfs_check_shape_size(x, y, shape_number):
    queue = [(x, y)]
    check_board[x][y] = shape_number
    cnt = 1
    while queue:
        point = queue.pop(0)
        for i in range(4):
            next_x = point[0] + dx[i]
            next_y = point[1] + dy[i]
            if is_valid(next_x, next_y) and not check_board[next_x][next_y] and board[next_x][next_y] == 1:
                check_board[next_x][next_y] = shape_number
                queue.append((next_x, next_y))
                cnt += 1
    return cnt


def make_shape(x, y):
    s = set()
    result = 1
    for i in range(4):
        next_x = x + dx[i]
        next_y = y + dy[i]
        if is_valid(next_x, next_y) and check_board[next_x][next_y] and check_board[next_x][next_y] not in s:
            s.add(check_board[next_x][next_y])
            result += shape_size[check_board[next_x][next_y] - 1]
    return result


input = sys.stdin.readline
N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
check_board = [[0] * M for _ in range(N)]
dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]

zero_points, shape_size, tmp = [], [], 1
for x in range(N):
    for y in range(M):
        if board[x][y] == 1 and not check_board[x][y]:
            shape_size.append(bfs_check_shape_size(x, y, tmp))
            tmp += 1

answer = 1
for x in range(N):
    for y in range(M):
        if board[x][y] == 0:
            answer = max(answer, make_shape(x, y))
print(answer)
