import collections
import sys


def isValid(next_x, next_y, check):
    return 0 <= next_x < N and 0 <= next_y < N and not check[next_x][next_y]


def BFS():
    q = collections.deque()
    q.append((0, 0, board[0][0]))
    check = [[False for _ in range(N)] for _ in range(N)]
    check[0][0] = True
    while q:
        x, y, distance = q.popleft()
        for i in range(2):
            next_x = x + dx[i] * distance
            next_y = y + dy[i] * distance

            if isValid(next_x, next_y, check):
                if board[next_x][next_y] == -1:
                    return True
                check[next_x][next_y] = True
                q.append((next_x, next_y, board[next_x][next_y]))
    return False


input = sys.stdin.readline
dx = [1, 0]
dy = [0, 1]
N = int(input())
board = [list(map(int, input().split())) for _ in range(N)]

if BFS():
    print("HaruHaru")
else:
    print("Hing")
