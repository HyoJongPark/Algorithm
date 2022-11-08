import sys


def is_valid(next_x, next_y):
    return 0 <= next_x < N and 0 <= next_y < N and not check[next_x][next_y]


def make_board(N, target):
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    count, dir = 1, 0
    x, y = N // 2, N // 2
    position = (x, y)
    board[x][y] = count
    check[x][y] = True
    while count < N ** 2:
        x += dx[dir]
        y += dy[dir]
        count += 1
        board[x][y], check[x][y] = count, True
        if count == target:
            position = (x, y)

        next_dir = (dir + 1) % 4
        if is_valid(x + dx[next_dir], y + dy[next_dir]):
            dir = next_dir

    return position


input = sys.stdin.readline
N, target = int(input()), int(input())
board = [[0 for _ in range(N)] for _ in range(N)]
check = [[False for _ in range(N)] for _ in range(N)]

target_position = make_board(N, target)
for b in board:
    for num in b:
        print(num, end=' ')
    print()

print(target_position[0] + 1, target_position[1] + 1)
